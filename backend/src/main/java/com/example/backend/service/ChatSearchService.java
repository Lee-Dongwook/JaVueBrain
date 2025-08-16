package com.example.backend.service;

import com.example.backend.domain.chat.ChatMessageDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.data.elasticsearch.client.elc.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.DocumentAdapters;
import org.springframework.data.elasticsearch.core.query.HighlightBuilder;
import org.springframework.stereotype.Service;
import org.springframework.data.elasticsearch.search.opearator.Operartor.AND;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import javax.naming.directory.SearchResult;

@Service
@RequiredArgsConstructor
public class ChatSearchService {

    private final ElasticsearchOperations operations;

    public Page<SearchResult> search(
        String keyword,
        String roomId,
        Instant from, Instant to,
        int page, int size,
        String sort
    ) {
        var bool = QueryBuilders.bool();
        if (roomId != null && !roomId.isBlank()) {
            bool.filter(QueryBuilders.term("roomId", roomId));
        }
        if (from != null || to != null) {
            var range = QueryBuilders.range("createdAt");
            if (from != null)
                range.gte(from.toString());
            if (to != null)
                range.lte(to.toString());
            bool.filter(range);
        }

        if (keyword != null && !keyword.isBlank()) {
            bool.must(
                    QueryBuilders.multiMatch()
                            .fields("content", "sender")
                            .query(keyword)
                            .fuzziness("AUTO")
                            .operator(AND));
        } else {
            bool.must(QueryBuilders.matchAll());
        }

        var highlight = new HighlightBuilder()
                .withPreTags("<mark>").withPostTags("</mark>")
                .withField("content");

        Sort springSort = Sort.by("createdAt");
        springSort = "old".equalsIgnoreCase(sort) ? springSort.ascending() : springSort.descending();

        var pageable = PageRequest.of(page, size, springSort);
        NativeQuery query = new NativeQueryBuilder()
                .withQuery(bool.build()._toQuery())
                .withPageable(pageable)
                .withHighlightQuery(
                        new HighlightQuery(highlight, ChatMessageDocument.class))
                .build();

        SearchHits<ChatMessageDocument> hits = operations.search(query, ChatMessageDocument.class);
        List<SearchResult> content = hits.stream().map(ChatSearchService::toResult).toList();
        return new PageImpl<>(content, pageable, hits.getTotalHits());
    }
    
    private static SearchResult toResult(SearchHit<ChatMessageDocument> hit) {
        ChatMessageDocument d = hit.getContent();

        Map<String, List<String>> hf = hit.getHighlightFields();
        String highlighted = hf != null && hf.get("content") != null && !hf.get("content").isEmpty()
                ? String.join(" ... ", hf.get("content"))
                : d.getContent();
        return new SearchResult(d.getId(), d.getRoomId(), d.getSender(), highlighted, d.getCreatedAt());
    }

    public record SearchResult(String id, String roomId, String sender, String highlightedContent, Instant createdAt) {}

}

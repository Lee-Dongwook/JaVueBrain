package com.example.backend.batch;

import com.example.backend.domain.chat.ChatMessage;
import com.example.backend.domain.chat.ChatArchive;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChatLogArchiveJobConfig {

    @Bean
    public Job chatLogArchiveJob(JobRepository jobRepository, Step chatLogArchiveStep) {
        return new JobBuilder("chatLogArchiveJob", jobRepository)
                .start(chatLogArchiveStep)
                .build();
    }

    @Bean
    public Step chatLogArchiveStep(
        JobRepository jobRepository,
        PlatformTransactionManager transactionManager,
        JpaPagingItemReader<ChatMessage> reader,
        RepositoryItemWriter<ChatArchive> writer 
    ) {
        return new StepBuilder("chatLogArchiveStep", jobRepository)
                   .<ChatMessage, ChatArchive>chunk(100, transactionManager)
                   .reader(reader)
                   .processor(message -> {
                    ChatArchive archive = new ChatArchive();
                    archive.setRoomId(message.getRoomId());
                    archive.setSender(message.getSender());
                    archive.setContent(message.getContent());
                    archive.setCreatedAt(message.getCreatedAt());
                    return archive;
                   })
                   .writer(writer)
                .build();
    }
    
    @Bean 
    public JpaPagingItemReader<ChatMessage> reader(EntityManagerFactory emf) {
        JpaPagingItemReader<ChatMessage> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(emf);
        reader.setQueryString("SELECT c FROM ChatMessage c WHERE c.createdAt < :today");
        reader.setPageSize(100);
        return reader;
    }
}

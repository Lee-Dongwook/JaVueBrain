package com.example.backend.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class MemberAgreementId implements Serializable {
    @Column(name = "member_uuid", columnDefinition = "uuid", nullable = false)
    private UUID memberUuid;
    
    @Column(name = "created_at", columnDefinition = "timestamptz", nullable = false)
    private OffsetDateTime createdAt;

    protected MemberAgreementId() {}
    
    public MemberAgreementId(UUID memberUuid, OffsetDateTime createdAt) {
        this.memberUuid = memberUuid;
        this.createdAt = createdAt;
    }

    public UUID getMemberUuid() {
        return memberUuid;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MemberAgreementId that))
            return false;
        return Objects.equals(memberUuid, that.memberUuid) &&
                Objects.equals(createdAt, that.createdAt);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(memberUuid, createdAt);
    }
}

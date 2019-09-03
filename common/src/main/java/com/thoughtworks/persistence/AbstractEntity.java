package com.thoughtworks.persistence;

import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@ToString(callSuper = true)
public abstract class AbstractEntity extends Identifiable {

    @Setter
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Setter
    private LocalDateTime lastModifiedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }
}

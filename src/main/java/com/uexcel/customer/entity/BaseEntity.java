package com.uexcel.customer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter @Getter @ToString
public class BaseEntity {
    @JsonIgnore
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @JsonIgnore
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    @JsonIgnore
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;
    @JsonIgnore
    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;

}

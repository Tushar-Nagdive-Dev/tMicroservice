package com.example.accountmswithpostgresql.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	
	@Basic
	@Column(name="created_time")
	@CreatedDate
	private LocalDateTime createdTime;
	
	@Column(name="modified_time")
	@LastModifiedDate
	private LocalDateTime modifiedTime;
	
	@Column(name = "created_by")
	@CreatedBy
	private String creator;
	
	@Column(name = "modified_by")
	@LastModifiedBy
	private String modifier;
}

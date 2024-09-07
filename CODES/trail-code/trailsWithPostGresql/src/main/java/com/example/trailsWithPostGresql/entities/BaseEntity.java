package com.example.trailsWithPostGresql.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity {
	
	@Basic
	@Column(name="created_time")
	private LocalDateTime createdTime;
	
	@Column(name="modified_time")
	private LocalDateTime modifiedTime;
}

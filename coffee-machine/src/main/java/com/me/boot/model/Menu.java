package com.me.boot.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*@Entity
@Table(name="menu")
@EntityListeners(AuditingEntityListener.class)*/
@Getter @Setter 
@ToString
public class Menu {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pid")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="cost")
	@ColumnDefault(value = "0")
	private Double cost;
	
//	Dated for auditing
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
//	private LocalDateTime createdAt;
	private Date createdAt;

	
	@LastModifiedDate
//	private LocalDateTime lastModifiedAt;
	private Date lastModifiedAt;
		
	

}

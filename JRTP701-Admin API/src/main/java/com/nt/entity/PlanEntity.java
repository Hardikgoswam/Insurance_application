package com.nt.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="PLAN")
@Data
public class PlanEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Integer planId;
	@Column(length = 30)
	private  String planName;
	private  LocalDate   startDate;
	private   LocalDate   endDate;
	@Column(length = 50)
	private   String  planDescr;
	private    String   activeSw;
	private Integer caseNo;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createdDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updatedDate;
	@Column(length = 30)
	private  String  createdBy;
	@Column(length = 30)
	private   String  updatedBy;
}

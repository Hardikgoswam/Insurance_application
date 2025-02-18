package com.nt.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="ELIGIBILITY_DETERMINATION")
@Data
@Entity
public class ElgibilityDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer edTraceId;
	private Integer caseNo;
	private String holderName;
	private Long holderSSN;
	@Column(length = 30)
	private String planName;
	@Column(length = 30)
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benifitAmt;
	@Column(length = 40)
	private String DenialReason;
	private   Long  accountNumber;
	private   String  bankName;
	

}

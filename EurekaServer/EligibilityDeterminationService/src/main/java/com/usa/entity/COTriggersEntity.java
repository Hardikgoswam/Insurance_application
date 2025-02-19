package com.usa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CO_TRIGGER")
@Data
public class COTriggersEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer triggerId;

	private Integer caseNo;

	@Lob
	private byte[] coNoticePdf;

	@Column(length = 30)
	private String triggerStatus = "pending";
}

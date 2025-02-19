package com.usa.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "DC_CHILDREN")
@Data
@Entity
public class DcChildrenEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer childId;

	private Long childSSN;

	private Integer caseNo;

	private LocalDate childDOB;

}

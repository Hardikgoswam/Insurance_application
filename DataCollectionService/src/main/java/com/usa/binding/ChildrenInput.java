package com.usa.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ChildrenInput {
	private Integer childId;
	private Long childSSN;
	private Integer caseNo;
	private LocalDate childDOB;
}

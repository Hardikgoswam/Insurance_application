package com.usa.binding;

import java.util.List;

import lombok.Data;

@Data
public class DcSummaryReport {
	
	private EducationInput Edudetails;
	
	private IncomeInput IncomeDetails;
	
	private List<ChildrenInput> ChildDetails;
	
	private CitizenAppRegistrationInput CitizenDetails;
	
	private String planName;
}

package com.usa.service;

import java.util.List;

import com.usa.binding.ChildrenInput;
import com.usa.binding.DcSummaryReport;
import com.usa.binding.EducationInput;
import com.usa.binding.IncomeInput;
import com.usa.binding.PlanInput;

public interface IDcMgmtService {
	public Integer generateCaseNo(Integer appId);

	public List<String> showAllPlansNames();
	
	public Integer savePlanSelection(PlanInput pi);
	
	public Integer saveChildDetails(List<ChildrenInput> children);
	
	public Integer saveEducationDetails(EducationInput ei);
	
	public Integer saveIncomeDetails(IncomeInput ii);
	
	public DcSummaryReport showDcSummaryReport(Integer caseNo);
}

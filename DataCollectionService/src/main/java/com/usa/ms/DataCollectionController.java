package com.usa.ms;

import java.util.List;

import org.slf4j.helpers.Reporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usa.binding.ChildrenInput;
import com.usa.binding.DcSummaryReport;
import com.usa.binding.EducationInput;
import com.usa.binding.IncomeInput;
import com.usa.binding.PlanInput;
import com.usa.service.IDcMgmtService;
@RestController()
@RequestMapping("/dc-api")
public class DataCollectionController {
	@Autowired
	private IDcMgmtService dcMgmtService;

	@GetMapping("/planNames")
	public ResponseEntity<List<String>> diplayPlans(){
		List<String> ls = dcMgmtService.showAllPlansNames();
		return new ResponseEntity<List<String>>(ls,HttpStatus.OK);
	}
	@PostMapping("/generateCaseNo/{appId}")
	public ResponseEntity<Integer> generateCaseNo(@PathVariable Integer appId){
		Integer caseNo = dcMgmtService.generateCaseNo(appId);
		return new ResponseEntity<Integer>(caseNo,HttpStatus.CREATED);
	}
	
	@PostMapping("/Save-Plan")
	public ResponseEntity<Integer> savedSelectedPlans(@RequestBody PlanInput plan){
		Integer caseNo = dcMgmtService.savePlanSelection(plan);
		return new ResponseEntity<Integer>(caseNo,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/Update-Plan")
	public ResponseEntity<Integer> updateSelectedPlans(@RequestBody PlanInput plan){
		Integer caseNo = dcMgmtService.savePlanSelection(plan);
		return new ResponseEntity<Integer>(caseNo,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/Save-Edu")
	public ResponseEntity<Integer> savedEducation(@RequestBody EducationInput edu){
		Integer caseNo = dcMgmtService.saveEducationDetails(edu);
		return new ResponseEntity<Integer>(caseNo,HttpStatus.CREATED);
		
	}
	@PostMapping("/Save-children-list")
	public ResponseEntity<Integer> savedChildrenList(@RequestBody List<ChildrenInput> childlist){
		Integer caseNo = dcMgmtService. saveChildDetails(childlist);
		return new ResponseEntity<Integer>(caseNo,HttpStatus.CREATED);
		
	}
	@PostMapping("/Save-Income")
	public ResponseEntity<Integer> savedChildrenList(@RequestBody IncomeInput income){
		Integer caseNo = dcMgmtService.saveIncomeDetails(income);
		return new ResponseEntity<Integer>(caseNo,HttpStatus.CREATED);
	}
	
	@GetMapping("/Summary/{caseNo}")
	public ResponseEntity<DcSummaryReport> showSummary(@PathVariable Integer caseNo){
		DcSummaryReport report =  dcMgmtService.showDcSummaryReport(caseNo);
		return new ResponseEntity<DcSummaryReport>(report,HttpStatus.OK);
	}
}

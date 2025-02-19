package com.usa.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usa.binding.EligibilityDetailsOutput;
import com.usa.service.EligibilityDeternimationMgmtService;

@RestController
@RequestMapping("eg-api")
public class EligibilitiyDerminationController {
	
	@Autowired
	private EligibilityDeternimationMgmtService deternimationMgmtService;
	
	@PostMapping("/determine/{caseNo}")
	public ResponseEntity<EligibilityDetailsOutput> checkPlanEligibility (Integer caseNo){
		EligibilityDetailsOutput detailsOutput = deternimationMgmtService.determineEligibility(caseNo);
		return new ResponseEntity<EligibilityDetailsOutput>(detailsOutput,HttpStatus.CREATED);
	} 
}

package com.usa.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.usa.binding.EligibilityDetailsOutput;
import com.usa.entity.COTriggersEntity;
import com.usa.entity.CitizenAppRegisterEntity;
import com.usa.entity.DcCaseEntity;
import com.usa.entity.EligibilityDetailsEntity;
import com.usa.entity.PlanEntity;
import com.usa.repo.IApplicationRegistrationRepository;
import com.usa.repo.ICOTriggerRepo;
import com.usa.repo.IDcCaseRepository;
import com.usa.repo.IEligibilityDetermineRepository;
import com.usa.repo.IPlanRepository;

@Service
public class EligibilityDeternimationMgmtServiceImpl implements EligibilityDeternimationMgmtService {

	@Autowired
	private IDcCaseRepository caseRepository;

	@Autowired
	private IPlanRepository iPlanRepository;

	@Autowired
	private IApplicationRegistrationRepository applicationRegistrationRepository;

	@Value("${plan.name}")
	private String plan1;

	@Autowired
	private IEligibilityDetermineRepository determineRepository;

	@Autowired
	private ICOTriggerRepo icoTriggerRepo;

	@Override
	public EligibilityDetailsOutput determineEligibility(Integer caseNo) {

		Integer appId = null;
		Integer planId = null;
		// get planId and appId based on caseNo
		Optional<DcCaseEntity> optCaseEntity = caseRepository.findById(caseNo);
		if (optCaseEntity.isPresent()) {
			DcCaseEntity caseEntity = optCaseEntity.get();
			planId = caseEntity.getPlanId();
			appId = caseEntity.getAppId();
		}
		// get plan Name
		String planName = null;
		Optional<PlanEntity> optPlanEntity = iPlanRepository.findById(planId);
		if (optPlanEntity.isPresent()) {
			PlanEntity planEntity = optPlanEntity.get();
			planName = planEntity.getPlanName();
		}
		// calculate citizen age by getting citizen DOB through appId
		Optional<CitizenAppRegisterEntity> optCitizenEntity = applicationRegistrationRepository.findById(appId);

		int citizenAge = 0;
		String citizenName = null;
		long citizenSSN = 0L;
		if (optCitizenEntity.isPresent()) {
			CitizenAppRegisterEntity citizenEntity = optCitizenEntity.get();
			LocalDate citizenDOB = citizenEntity.getDob();
			citizenName = citizenEntity.getFullName();
			LocalDate sysDate = LocalDate.now();
			citizenAge = Period.between(citizenDOB, sysDate).getYears();
			citizenSSN = citizenEntity.getSsn();
		}
		// get citizen age;
		EligibilityDetailsOutput elgiOutput = applyPlanConditions(caseNo, planName, citizenAge);
		elgiOutput.setHolderName(citizenName);

		// save Egibility entity object
		EligibilityDetailsEntity elgiEntity = new EligibilityDetailsEntity();
		BeanUtils.copyProperties(elgiOutput, elgiEntity);
		elgiEntity.setCaseNo(caseNo);
		elgiEntity.setHolderSSN(citizenSSN);
		determineRepository.save(elgiEntity);

		// save CoTriggers object
		COTriggersEntity triggersEntity = new COTriggersEntity();
		triggersEntity.setCaseNo(caseNo);
		triggersEntity.setTriggerStatus("pending");
		icoTriggerRepo.save(triggersEntity);

		return elgiOutput;
	}

	private EligibilityDetailsOutput applyPlanConditions(Integer caseNo, String planName, int age) {
		EligibilityDetailsOutput output = new EligibilityDetailsOutput();
		if (planName.equalsIgnoreCase(plan1)) {
			if (age >= 22) {
				output.setPlanStatus("Approved");
				output.setBenifitAmt(1500.0);
				output.setDenialReason("Not Applicable");
			} else {
				output.setPlanStatus("Denied");
				output.setDenialReason(plan1 + "not satisfied all condition");
			}
		}
		return output;
	}

}

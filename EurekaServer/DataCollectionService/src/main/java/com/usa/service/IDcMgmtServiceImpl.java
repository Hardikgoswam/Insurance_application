package com.usa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usa.binding.ChildrenInput;
import com.usa.binding.CitizenAppRegistrationInput;
import com.usa.binding.DcSummaryReport;
import com.usa.binding.EducationInput;
import com.usa.binding.IncomeInput;
import com.usa.binding.PlanInput;
import com.usa.entity.CitizenAppRegisterEntity;
import com.usa.entity.DcCaseEntity;
import com.usa.entity.DcChildrenEntity;
import com.usa.entity.DcEducationEntity;
import com.usa.entity.DcIncomeEntity;
import com.usa.entity.PlanEntity;
import com.usa.repo.IApplicationRegistrationRepository;
import com.usa.repo.IDcCaseRepository;
import com.usa.repo.IDcChildrenRepository;
import com.usa.repo.IDcEducationRepository;
import com.usa.repo.IDcIncomeRepository;
import com.usa.repo.IPlanRepository;

@Service
public class IDcMgmtServiceImpl implements IDcMgmtService {

	@Autowired
	private IApplicationRegistrationRepository applicationRegistrationRepository;
	@Autowired
	private IPlanRepository iPlanRepository;
	@Autowired
	private IDcChildrenRepository childrenRepository;
	@Autowired
	private IDcEducationRepository dcEducationRepository;
	@Autowired
	private IDcIncomeRepository dcIncomeRepository;
	@Autowired
	private IDcCaseRepository caseRepository;

	@Override // set app id into new created dc_case
	public Integer generateCaseNo(Integer appId) {
		Optional<CitizenAppRegisterEntity> appReg = applicationRegistrationRepository.findById(appId);
		if (appReg.isPresent()) {
			DcCaseEntity dcCase = new DcCaseEntity();
			dcCase.setAppId(appId);
			return caseRepository.save(dcCase).getCaseNo();
		}
		return 0;
	}

	@Override // show all current plan only show Plan Names;
	public List<String> showAllPlansNames() {
		List<PlanEntity> planList = iPlanRepository.findAll();
		return planList.stream().map(plan -> plan.getPlanName()).toList();
	}

	@Override // at time save slected plan store plan case no into dc_case
	public Integer savePlanSelection(PlanInput pi) {
		Optional<DcCaseEntity> opt = caseRepository.findById(pi.getCaseNo());
		if (opt.isPresent()) {
			DcCaseEntity de = opt.get();
			de.setPlanId(pi.getCaseNo());
			caseRepository.save(de);
			return de.getCaseNo();
		}
		return 0;
	}

	@Override // save list of children details
	public Integer saveChildDetails(List<ChildrenInput> children) {
		children.forEach(child -> {
			DcChildrenEntity childEntity = new DcChildrenEntity();
			BeanUtils.copyProperties(child, childEntity);
			childrenRepository.save(childEntity);
		});
		return children.get(0).getCaseNo();
	}

	@Override // saves Education input
	public Integer saveEducationDetails(EducationInput ei) {
		DcEducationEntity education = new DcEducationEntity();
		BeanUtils.copyProperties(ei, education);
		dcEducationRepository.save(education);
		return education.getCaseNo();
	}

	@Override // saves Income input
	public Integer saveIncomeDetails(IncomeInput ii) {
		DcIncomeEntity income = new DcIncomeEntity();
		BeanUtils.copyProperties(ii, income);
		dcIncomeRepository.save(income);
		return income.getCaseNo();
	}

	@Override // show summaryReport
	public DcSummaryReport showDcSummaryReport(Integer caseNo) {
		DcIncomeEntity incomeEntity = dcIncomeRepository.findByCaseNo(caseNo);
		List<DcChildrenEntity> childList = childrenRepository.findByCaseNo(caseNo);
		DcEducationEntity educationEntity = dcEducationRepository.findByCaseNo(caseNo);
		Optional<DcCaseEntity> caseEnity = caseRepository.findById(caseNo);
		String planName = null;
		Integer appid = null;
		if (caseEnity.isPresent()) {
			DcCaseEntity opt = caseEnity.get();
			Integer planid = opt.getPlanId();
			appid = opt.getAppId();
			Optional<PlanEntity> planEntity = iPlanRepository.findById(planid);
			if (planEntity.isPresent()) {
				planName = planEntity.get().getPlanName();

			}
		}
		IncomeInput income = new IncomeInput();
		BeanUtils.copyProperties(incomeEntity, income);
		EducationInput education = new EducationInput();
		BeanUtils.copyProperties(educationEntity, education);
		List<ChildrenInput> childInput = new ArrayList<ChildrenInput>();
		childList.forEach(childEntity -> {
			ChildrenInput child = new ChildrenInput();
			BeanUtils.copyProperties(childEntity, child);
			childInput.add(child);
		});

		Optional<CitizenAppRegisterEntity> citiz = applicationRegistrationRepository.findById(appid);
		CitizenAppRegisterEntity appEntity = citiz.get();
		CitizenAppRegistrationInput appInput = new CitizenAppRegistrationInput();
		BeanUtils.copyProperties(appEntity, appInput);

		DcSummaryReport report = new DcSummaryReport();
		report.setChildDetails(childInput);
		report.setCitizenDetails(appInput);
		report.setEdudetails(education);
		report.setIncomeDetails(income);
		report.setPlanName(planName);

		return report;
	}

}

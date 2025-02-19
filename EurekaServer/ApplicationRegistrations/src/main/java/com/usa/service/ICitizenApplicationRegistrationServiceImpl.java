package com.usa.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.usa.binding.CitizenAppRegistrationInput;
import com.usa.entity.CitizenAppRegisterEntity;
import com.usa.exception.InvalidSSNException;
import com.usa.repository.IApplicationRegistrationRepository;

import reactor.core.publisher.Mono;

@Service
public class ICitizenApplicationRegistrationServiceImpl implements ICitizenApplicationRegistrationService {

	@Autowired
	private IApplicationRegistrationRepository Citizenrepo;

	@Autowired
	private WebClient client;

	@Value("${ar.ssa-web.url}")
	private String endpointUrl;
//
	@Value("${ar.state}")
	private String targetState;

//	@Override
//	public Integer registerCitizenApplication(CitizenAppRegistrationInput input) {
//		ResponseEntity<String> repsonse = restTemplate.exchange(endpointUrl, HttpMethod.GET, null, String.class,
//				input.getSsn());
//		String stateName = repsonse.getBody();
//		if (targetState.equalsIgnoreCase(stateName)) {
//			CitizenAppRegisterEntity entity = new CitizenAppRegisterEntity();
//			BeanUtils.copyProperties(input, entity);
//			entity.setStateName(stateName);
//			int id = Citizenrepo.save(entity).getAppId();
//			return id;
//		}
//		return 0;
//	

	@Override
	public Integer registerCitizenApplication(CitizenAppRegistrationInput input) throws InvalidSSNException {
		Mono<String> res = client.get().uri(endpointUrl, input.getSsn()).retrieve()
				.onStatus(HttpStatus.BAD_REQUEST::equals,
						ex->ex.bodyToMono(String.class).map(body->new InvalidSSNException("Invalid SSN")))
				.bodyToMono(String.class);
		String stateName = res.block();
		if (targetState.equalsIgnoreCase(stateName)) {
			CitizenAppRegisterEntity entity = new CitizenAppRegisterEntity();
			BeanUtils.copyProperties(input, entity);
			entity.setStateName(stateName);
			int id = Citizenrepo.save(entity).getAppId();
			return id;
		}
		throw new InvalidSSNException("Invalid SSN");
	}

}

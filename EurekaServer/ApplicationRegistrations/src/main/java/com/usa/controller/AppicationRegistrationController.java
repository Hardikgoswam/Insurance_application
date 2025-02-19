package com.usa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usa.binding.CitizenAppRegistrationInput;
import com.usa.service.ICitizenApplicationRegistrationServiceImpl;

@RestController
@RequestMapping("/CitizenAR-api")
public class AppicationRegistrationController {

	@Autowired
	private ICitizenApplicationRegistrationServiceImpl applicationRegistrationService;

	@PostMapping("/save")
	public ResponseEntity<String> saveCitizenApplication(
			@RequestBody CitizenAppRegistrationInput appRegistrationInput) throws Exception{
		Integer id = applicationRegistrationService.registerCitizenApplication(appRegistrationInput);
		return new ResponseEntity<String>("Citizen Application is registration with id : " + id,
				HttpStatus.CREATED);
	}

}

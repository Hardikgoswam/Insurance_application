package com.usa.service;

import com.usa.binding.CitizenAppRegistrationInput;
import com.usa.exception.InvalidSSNException;

public interface ICitizenApplicationRegistrationService {
	public Integer registerCitizenApplication(CitizenAppRegistrationInput input) throws InvalidSSNException;
}

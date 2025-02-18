package com.usa.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CitizenAppRegistrationInput {
	private String fullName;
	private String email;
	private String gender;
	private Long Phoneno;
	private Long ssn;
	private String stateName;
	private LocalDate dob;
}

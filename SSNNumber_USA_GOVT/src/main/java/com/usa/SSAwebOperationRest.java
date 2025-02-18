package com.usa;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
@RequestMapping("/ssa-web-api")
public class SSAwebOperationRest {
	@GetExchange("/find/{ssn}")
	public ResponseEntity<String> getStatebyId(@PathVariable Integer ssn) {
		if (String.valueOf(ssn).length() != 9) {
			System.out.println("inside if");
			return new ResponseEntity<String>("Invalid ssn", HttpStatus.BAD_REQUEST);
		}
		Integer i = ssn % 10;
		String stateName = null;
		switch (i) {
		case 1:
			stateName = "California";
			break;
		case 2:
			stateName = "Washington DC";
			break;
		case 3:
			stateName = "Texas";
			break;
		case 4:
			stateName = "Florida";
			break;
		case 5:
			stateName = "Ohio";
			break;
		default:
			stateName = "Invalid ssn";
		}
		return new ResponseEntity<String>(stateName, HttpStatus.OK);

	}
}

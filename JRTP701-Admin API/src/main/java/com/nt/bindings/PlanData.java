package com.nt.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PlanData {
	private  Integer planId;
	private  String planName;
	private  LocalDate   startDate;
	private   LocalDate   endDate;
	private   String  planDescr;
	private    String   activeSw;

}

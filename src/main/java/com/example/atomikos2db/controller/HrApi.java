package com.example.atomikos2db.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/rest/atomikos2db")
public interface HrApi {

	@PostMapping("/process/{empId}")
	ResponseEntity<HttpStatus> process(@PathVariable("empId") long empId);

	@PostMapping("/unprocess/{empId}")
	ResponseEntity<HttpStatus> unprocess(@PathVariable("empId") long empId);

}

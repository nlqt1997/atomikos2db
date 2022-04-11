package com.example.atomikos2db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.atomikos2db.service.HrService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HrController implements HrApi {
	
	@Autowired
	private HrService service;

	@Override
	public ResponseEntity<HttpStatus> process(long empId) {
		try {
			service.process(empId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error processing employee: " + empId, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<HttpStatus> unprocess(long empId) {
		try {
			service.unprocess(empId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error unprocessing employee: " + empId, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

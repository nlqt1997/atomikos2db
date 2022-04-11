package com.example.atomikos2db.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.atomikos2db.repository.db1.EmployeeRepo;
import com.example.atomikos2db.repository.db2.JobRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HrService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private JobRepo jobRepo;
	
	@Transactional()
	public void process(Long empId) {
		log.info("Processing employee with id: " + empId);
		var employeeOpt = employeeRepo.findById(empId);
		if (employeeOpt.isPresent()) {
			var employee = employeeOpt.get();
			var jobId = employee.getJobId();
			var jobOpt = jobRepo.findById(jobId);
			if (jobOpt.isPresent()) {
				var job = jobOpt.get();
				job.setJobTitle(job.getJobTitle() + "/");
				employee.setFirstName(employee.getFirstName() + "/");
			} else {
				throw new NullPointerException("No job with id: " + empId);
			}
		} else {
			throw new NullPointerException("No employee with id: " + empId);
		}
	}
	
	@Transactional
	public void unprocess(Long empId) {
		log.info("Unprocessing employee with id: " + empId);
		var employeeOpt = employeeRepo.findById(empId);
		if (employeeOpt.isPresent()) {
			var employee = employeeOpt.get();
			var jobId = employee.getJobId();
			var jobOpt = jobRepo.findById(jobId);
			if (jobOpt.isPresent()) {
				var job = jobOpt.get();
				employee.setFirstName(StringUtils.trimTrailingCharacter(employee.getFirstName(), '/'));
				job.setJobTitle(StringUtils.trimTrailingCharacter(job.getJobTitle(), '/'));
			} else {
				throw new NullPointerException("No job with id: " + empId);
			}
		} else {
			throw new NullPointerException("No employee with id: " + empId);
		}
	}
}

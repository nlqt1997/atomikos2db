package com.example.atomikos2db.entity.db1;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(schema = "HR", name = "EMPLOYEES")
public class EmployeeEntity {

	@Id
	@Column(name = "EMPLOYEE_ID", nullable = false)
	private Long employeeId;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name = "HIRE_DATE", nullable = false)
	private Date hireDate;
	
	@Column(name = "JOB_ID", nullable = false)
	private String jobId;
	
	@Column(name = "SALARY")
	private Double salary;
	
	@Column(name = "COMMISSION_PCT")
	private Double commissionPct;
	
}

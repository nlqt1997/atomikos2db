package com.example.atomikos2db.entity.db2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(schema = "HR", name = "JOBS")
public class JobEntity {
	
	@Id
	@Column(name = "JOB_ID", nullable = false)
	private String jobId;
	
	@Column(name = "JOB_TITLE", nullable = false)
	private String jobTitle;
	
	@Column(name = "MIN_SALARY")
	private String minSalary;
	
	@Column(name = "MAX_SALARY")
	private String maxSalary;
	
}

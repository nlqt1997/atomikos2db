package com.example.atomikos2db.repository.db2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.atomikos2db.entity.db2.JobEntity;

@Repository
public interface JobRepo extends JpaRepository<JobEntity, String> {
	
}

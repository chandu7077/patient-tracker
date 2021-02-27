package com.tracker.patienttracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.tracker.patienttracker.model.Doctor;
import com.tracker.patienttracker.model.Patient;
import com.tracker.patienttracker.model.PatientRecord;
import com.tracker.patienttracker.model.Test;
import com.tracker.patienttracker.model.TestReport;

@Repository
public interface TestReportRepository extends JpaRepository<TestReport, Integer> {
	// Update Test Results Home Page
	//Select * From test_report where testResult = null;
	@Query(value = "Select t From TestReport t where t.testResult = null")
	public List<TestReport> getPendingUpdateTestReports(); // Test pending
}

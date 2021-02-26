package com.tracker.patienttracker.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tracker.patienttracker.model.Treatment;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

	@Query("select dietExcerciseDescription from treatment t where t.treatmentId=:treatmentId")
	      public String getDietDetails(@Param(value = "treatmentId") int treatmentId);

	
}

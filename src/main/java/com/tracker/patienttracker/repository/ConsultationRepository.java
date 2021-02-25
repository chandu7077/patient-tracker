package com.tracker.patienttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tracker.patienttracker.model.Consultation;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer>{

}

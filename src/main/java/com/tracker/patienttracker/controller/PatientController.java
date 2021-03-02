package com.tracker.patienttracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.patienttracker.model.Patient;
import com.tracker.patienttracker.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	PatientService patientService;
	
	@GetMapping("/details/{patientId}")
	public Patient getPatientDetails(@PathVariable int patientId) 
	{
		return patientService.getPatientDetails(patientId);
	}
	
//	@PostMapping("/a")
//	public void addPatient(@RequestBody Patient patient)
//	{ 
//		patientService.addPatient(patient);
//	}
	
	@GetMapping("/getallpatients")
	public List<Patient> getAllPatientList()
	{
		return patientService.getPatientList();
	}
	
	@PutMapping("/updatepatient/{patientId}")
	public void updatePatient(@RequestBody Patient patient, @PathVariable int patientId)
	{
		patientService.updatePatient(patient, patientId);
	}
}

package com.tracker.patienttracker.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.patienttracker.dto.PatientRecordDTO;
import com.tracker.patienttracker.exception.PatientNotFoundException;
import com.tracker.patienttracker.model.Doctor;
import com.tracker.patienttracker.model.MedicineQuantity;
import com.tracker.patienttracker.model.Patient;
import com.tracker.patienttracker.model.PatientRecord;
import com.tracker.patienttracker.model.Prescription;
import com.tracker.patienttracker.model.TestReport;
import com.tracker.patienttracker.model.Treatment;
import com.tracker.patienttracker.repository.PatientRecordRepository;
import com.tracker.patienttracker.repository.PatientRepository;

@Service
public class PatientRecordService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientRecordRepository patientRecordRepository;
	
	public Set<Prescription> prescriptions(int patientId, int doctorId) {
		Doctor doctor = doctorService.getDoctor(doctorId);

	private PatientService patientService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Transactional
	public Set<Prescription> prescriptions(int patientId) {
		
		Patient patient = patientService.getPatient(patientId);
		//getpatientRecord
		Optional<PatientRecord> optional = patientRecordRepository.findByPatientAndDoctor(patient, doctor);
		if(!optional.isPresent()) {
			throw new PatientNotFoundException();
		}
		PatientRecord patientRecord=optional.get();
		return patientRecord.getPrescriptions();
	}
	
	public Set<TestReport> testReports(int patientId, int doctorId) {
		Doctor doctor = doctorService.getDoctor(doctorId);
		Patient patient = patientService.getPatient(patientId);
		//getpatientRecord
		Optional<PatientRecord> optional = patientRecordRepository.findByPatientAndDoctor(patient, doctor);
		
		if(!optional.isPresent()) {
			throw new PatientNotFoundException();
		}
		PatientRecord patientRecord=optional.get();
		return patientRecord.getTestreports();
	}
	
	public Set<Treatment> treatments(int patientRecordId, int doctorId) {
		Doctor doctor = doctorService.getDoctor(doctorId);
		//Patient patient = patientService.getPatient(patientId);
		Optional<PatientRecord> optional = patientRecordRepository.findByrecordIdAndDoctor(patientRecordId, doctor);
		if(!optional.isPresent()) {
			throw new PatientNotFoundException();
		}
		
		PatientRecord patientRecord=optional.get();
		return patientRecord.getTreatments();
	}
	
	public String addPrescription(PatientRecordDTO dto) {
		int patientRecordId = dto.getRecordId();
		int doctorId = dto.getDoctorId();
		Doctor doctor =  doctorService.getDoctor(doctorId);
		
		Optional<PatientRecord> optional = patientRecordRepository.findByrecordIdAndDoctor(patientRecordId, doctor);
		if(!optional.isPresent()) {
			throw new PatientNotFoundException();
		}
		
		Prescription prescription = dto.getPrescription();
		double prescriptionCost = 0;
		for(MedicineQuantity mq : prescription.getMedicineQuantities()) {
			prescriptionCost = prescriptionCost + mq.getQuantity()*mq.getMedicine().getMedicineCost();
		}
		
		PatientRecord patientRecord=optional.get();
		prescription.setPrescriptionCost(prescriptionCost);
		patientRecord.getPrescriptions().add(prescription);
		PatientRecord record = patientRecordRepository.save(patientRecord);
		
		return "Added Successfully";
		
	}

	public String addTreatment(PatientRecordDTO dto) {
		int patientRecordId = dto.getRecordId();
		int doctorId = dto.getDoctorId();
		Doctor doctor =  doctorService.getDoctor(doctorId);
		
		Optional<PatientRecord> optional = patientRecordRepository.findByrecordIdAndDoctor(patientRecordId, doctor);
		if(!optional.isPresent()) {
			throw new PatientNotFoundException();
		}
		
		Treatment treatment = dto.getTreatment();
		PatientRecord patientRecord=optional.get();
		treatment.setPatientRecord(patientRecord);
		patientRecord.getTreatments().add(treatment);
		
		PatientRecord record = patientRecordRepository.save(patientRecord);
		
		return "Added Successfully";
	}
	
	
	
	@Transactional
	public Set<Patient> getAllPatientsForDoctor(int doctorId){
		Set<Integer> patientids =  patientRecordRepository.findPatientByDoctor(doctorId);
		Set<Patient> patients = new HashSet<Patient>();
		for(Integer i : patientids) {
			patients.add(patientService.getPatient(i));
		}
		return patients;
	}
	
	@Transactional
	public PatientRecord getPatientRecordForPatientId(int patientId) {
		Patient patient = patientService.getPatient(patientId);
		Optional<PatientRecord> optionalPatientRecord = patientRecordRepository.findByPatient(patient);
		if(optionalPatientRecord.isEmpty()) {
			throw new PatientNotFoundException();
		}
		PatientRecord patientRecord = optionalPatientRecord.get();
		return patientRecord;
	}
	
	@Transactional
	public PatientRecord addPatientRecord(PatientRecord patientRecord, String token) {
//		AuthResponse response = userService.getValidity(token);
//		if(!response.isValid)
//			throw new InvalidTokenException("Token is not valid");
		
		return patientRecordRepository.save(patientRecord);
	}
	
	@Transactional
	public PatientRecord updatePatientRecord(PatientRecord patientRecord, String token) {
//		AuthResponse response = userService.getValidity(token);
//		if(!response.isValid)
//			throw new InvalidTokenException("Token is not valid");
		
		return patientRecordRepository.save(patientRecord);
	}
}

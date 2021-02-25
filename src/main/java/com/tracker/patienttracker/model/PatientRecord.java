package com.tracker.patienttracker.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patientrecord")
public class PatientRecord {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int recordId;
	
	@ManyToMany
	@JoinColumn(name= "doctorId")
//	@JoinTable(name="patientrecord_doctor",joinColumns = @JoinColumn(name = "record_doctorId"),
//	inverseJoinColumns = @JoinColumn(name = "doctor_recordId"))
	private Set<Doctor> doctors;
	
	@OneToMany(targetEntity = Treatment.class)	
	@JoinColumn(name = "treatmentId")
	private Set<Treatment> treatments;
	
	@OneToMany(targetEntity = TestReport.class)
	@JoinColumn(name = "testResultId")
	private Set<TestReport> testreports;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patient;
	
	@NotNull
	@OneToMany(targetEntity = Prescription.class)
	@JoinColumn(name = "prescriptionId")
	private Set<Prescription> prescriptionId;
	
	@PastOrPresent
	@NotNull
	private Date date;
}



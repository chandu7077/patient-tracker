package com.tracker.patienttracker.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Repository
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "prescription")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","patientRecord"})
public class Prescription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "Prescription Id is required")
	private int prescriptionId;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="prescription_medicines", joinColumns = @JoinColumn(name ="prescriptionId"),inverseJoinColumns = @JoinColumn(name="medicineQuantityId") )
	private Set<MedicineQuantity> medicineQuantities;
	
	@NotNull(message = "Prescription Cost is required")
	@Digits(integer = 32, fraction = 2, message = "Please Enter a valid cost")
	private double prescriptionCost;
	
	@NotNull(message = "Paid is required")
	private boolean paid;
	
	@NotNull(message = "Billed is required")
	private boolean billed;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="recordId")
	private PatientRecord patientRecord;
	
}

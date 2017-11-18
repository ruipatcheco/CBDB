package com.tenico.sirs.CommonTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Clinician {
	private UUID id;
	private Speciality speciality;
	private String name;
	private List<Patient_Clinician> patients_clinician;

	public Clinician(Speciality speciality, String name) {

		this.id = UUID.randomUUID();
		this.setSpeciality(speciality);
		this.setName(name);
		this.patients_clinician = new ArrayList<Patient_Clinician>();
	}
	
	public void Add_Patient(Patient_Clinician patient_clinician) {
		this.patients_clinician.add(patient_clinician);
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id == ((Clinician)obj).id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}
}

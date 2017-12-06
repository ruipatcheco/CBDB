package com.tenico.sirs.CommonTypes;

import java.util.ArrayList;
import java.util.List;

public class Clinician {
	private int id;
	private Specialty specialty;
	private String username;
	private List<Patient_Clinician> patients_clinician;
	private String name;

	public Clinician(int id , Specialty specialty, String username, String name) {

		this.id = id;
		this.setSpecialty(specialty);
		this.setUsername(username);
		this.name = name;
		this.patients_clinician = new ArrayList<Patient_Clinician>();
	}
	public String getName() { return this.name; }
	
	public void Add_Patient(Patient_Clinician patient_clinician) {
		this.patients_clinician.add(patient_clinician);
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id == ((Clinician)obj).id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public int getID() { return id; }
}

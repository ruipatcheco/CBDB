package com.tenico.sirs.CommonTypes;

import java.util.ArrayList;
import java.util.List;

public class Medical_Record {
	private int id;
	private int patient_id;
	private int clinician_id;
	private List<Specialty> specialities;
	private String hash;
	private String info;
	
	
	public Medical_Record(int rid, int patient_id, int clinician_id, String hash, String info) {
		this.id = rid;
		this.patient_id = patient_id;
		this.clinician_id = clinician_id;
		this.specialities = new ArrayList<Specialty>();
		this.hash = hash;
		this.info = info;
	}
	
	public void addSpeciality(Specialty specialty) {
		this.specialities.add(specialty);
	}
	public List<Specialty> getSpecialities() {
		return specialities;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public int getClinician_id() {
		return clinician_id;
	}
	public void setClinician_id(int clinician_id) {
		this.clinician_id = clinician_id;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}

    public String getInfo() {
        return this.info;
    }
}

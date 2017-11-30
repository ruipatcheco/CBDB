package com.tenico.sirs.CommonTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Medical_Record {
	private UUID id;
	private UUID patient_id;
	private UUID clinician_id;
	private List<Specialty> specialities;
	private int hash;
	private String recordInfo;
	
	
	public Medical_Record(UUID patient_id, UUID clinician_id, int hash, String recordInfo) {
		this.id = UUID.randomUUID();
		this.patient_id = patient_id;
		this.clinician_id = clinician_id;
		this.specialities = new ArrayList<Specialty>();
		this.hash = hash;
		this.recordInfo = recordInfo;
	}
	
	public void addSpeciality(Specialty specialty) {
		this.specialities.add(specialty);
	}
	public List<Specialty> getSpecialities() {
		return specialities;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(UUID patient_id) {
		this.patient_id = patient_id;
	}
	public UUID getClinician_id() {
		return clinician_id;
	}
	public void setClinician_id(UUID clinician_id) {
		this.clinician_id = clinician_id;
	}
	public String getRecordInfo() {
		return this.recordInfo;
	}
	public void setRecordInfo(String recordInfo) {
		this.recordInfo = recordInfo;
	}
	public int getHash() {
		return hash;
	}
	public void setHash(int hash) {
		this.hash = hash;
	}

}

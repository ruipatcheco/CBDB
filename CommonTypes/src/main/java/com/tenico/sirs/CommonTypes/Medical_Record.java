package com.tenico.sirs.CommonTypes;

import java.util.ArrayList;
import java.util.List;

public class Medical_Record {
	private int id;
	private int patient_id;
	private int clinician_id;
	private String hash;
	private String info;
	
	
	public Medical_Record(int rid, int patient_id, int clinician_id, String hash, String info) {
		this.id = rid;
		this.patient_id = patient_id;
		this.clinician_id = clinician_id;
		this.hash = hash;
		this.info = info;
	}
	public int getId() {
		return id;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public int getClinician_id() {
		return clinician_id;
	}
	public String getHash() {
		return hash;
	}
    public String getInfo() { return this.info; }
}

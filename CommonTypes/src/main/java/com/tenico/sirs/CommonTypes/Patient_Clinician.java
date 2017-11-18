package com.tenico.sirs.CommonTypes;

import java.util.Date;
import java.util.UUID;

public class Patient_Clinician {
	private UUID patient_id;
	private UUID clinician_id;
	private Date attendance;
	
	public Patient_Clinician(UUID patient_id, UUID clinician_id, Date attendance) {
		this.setPatient_id(patient_id);
		this.setClinician_id(clinician_id);
		this.setAttendance(attendance);
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
	public Date getAttendance() {
		return attendance;
	}
	public void setAttendance(Date attendance) {
		this.attendance = attendance;
	}
}

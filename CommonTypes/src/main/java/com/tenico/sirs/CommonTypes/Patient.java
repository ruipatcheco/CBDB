package com.tenico.sirs.CommonTypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Patient {
	private UUID id;
	private String name;
	private Date date_of_birth;
	private List<Medical_Record> records;
	
	public Patient(String name, Date date_of_brith) {
		this.id = UUID.randomUUID();
		this.setName(name);
		this.setDate_of_birth(date_of_brith);
		this.records = new ArrayList<Medical_Record>();
	}
	
	public void addRecord(Medical_Record record) {
		this.records.add(record);
	}
	public List<Medical_Record> getRecords() {
		return this.records;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
}

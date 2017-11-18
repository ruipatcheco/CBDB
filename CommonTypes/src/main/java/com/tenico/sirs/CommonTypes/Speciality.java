package com.tenico.sirs.CommonTypes;

public class Speciality {
	private String name;
	private SpecialityGroup group;
	
	public Speciality(String name, SpecialityGroup group) {
		
		this.setGroup(group);
		this.setName(name);
	}
	
	public Speciality(String name) {
		
		//TODO get specialty group from DB
		
		//this.setGroup(group);
		this.setName(name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SpecialityGroup getGroup() {
		return group;
	}
	public void setGroup(SpecialityGroup group) {
		this.group = group;
	}

}

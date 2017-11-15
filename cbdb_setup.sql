CREATE DATABASE IF NOT EXISTS CBDB;
/*
CREATE USER IF NOT EXISTS cbdb_su@localhost IDENTIFIED BY 'password'
GRANT ALL ON CBDB.* TO cbdb_su@localhost;
*/USE CBDB;
CREATE TABLE IF NOT EXISTS PATIENTS (
	PatientID INT NOT NULL,
	PatientName VARCHAR(255) NOT NULL,
	DateOfBirth TIME NOT NULL,
PRIMARY KEY(PatientID)
);
CREATE TABLE IF NOT EXISTS CLINICIAN(
	ClinicianID INT NOT NULL,
	SpecialtyID INT NOT NULL,
	CLinicianName VARCHAR(255) NOT NULL,
PRIMARY KEY(ClinicianID),
FOREIGN KEY(SpecialtyID) REFERENCES SPECIALTY(SpecialtyID)
);
CREATE TABLE IF NOT EXISTS SPECIALTY_GROUP(
	SpecialtyGroupID VARCHAR(255) NOT NULL,
PRIMARY KEY(SpecialtyGroupID)
);
CREATE TABLE IF NOT EXISTS SPECIALTY(
	SpecialtyID INT NOT NULL,
	SpecialtyGroupID VARCHAR(255) NOT NULL,
PRIMARY KEY(SpecialtyID),
FOREIGN KEY(SpecialtyGroupID) REFERENCES SPECIALTY_GROUP(SpecialtyGroupID)
);
CREATE TABLE IF NOT EXISTS PATIENTS_CLINITIAN(
	PDID INT NOT NULL,
	PatientID INT NOT NULL,
	ClinicianID INT NOT NULL,
PRIMARY KEY(PDID),
FOREIGN KEY(PatientID) REFERENCES PATIENTS(PatientID),
FOREIGN KEY(ClinicianID) REFERENCES CLINICIAN(ClinicianID)
);
CREATE TABLE IF NOT EXISTS MEDICAL_RECORDS(
	RID INT NOT NULL,
	PatientID INT NOT NULL,
	ClinicianID INT NOT NULL,
	RSpecialtiesID INT NOT NULL,
	RecordHash INT NOT NULL,
	RecordInfo VARBINARY(1024),  
PRIMARY KEY(RID),
FOREIGN KEY(PatientID) REFERENCES PATIENTS(PatientID),
FOREIGN KEY(ClinicianID) REFERENCES CLINICIAN(ClinicianID)
);
CREATE TABLE IF NOT EXISTS RECORD_SPECIALTIES(
	RSID INT NOT NULL,
	RSpecialtiesID INT NOT NULL,
	SpecialtyID INT NOT NULL,
PRIMARY KEY(RSID),
FOREIGN KEY(SpecialtyID) REFERENCES SPECIALTY(SpecialtyID)
);

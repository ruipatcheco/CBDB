USE CBDB;

INSERT INTO SPECIALTY_GROUP (SpecialtyGroupID) VALUES ('General Surgery');
INSERT INTO SPECIALTY_GROUP (SpecialtyGroupID) VALUES ('Internal Medicine');
INSERT INTO SPECIALTY_GROUP (SpecialtyGroupID) VALUES ('Paediatrics');
INSERT INTO SPECIALTY_GROUP (SpecialtyGroupID) VALUES ('Surgery');
INSERT INTO SPECIALTY_GROUP (SpecialtyGroupID) VALUES ('Anaesthetics');
INSERT INTO SPECIALTY_GROUP (SpecialtyGroupID) VALUES ('Radiology');
INSERT INTO SPECIALTY_GROUP (SpecialtyGroupID) VALUES ('Family Medicine');

INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('General Surgery','General Surgery');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Cardiothoratic Surgery','General Surgery');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Colon and Rectal Surgery','General Surgery');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Orthopaedics','General Surgery');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Paediatric Surgery','General Surgery');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Plastic, Reconstructive and Aesthetic Surgery','General Surgery');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Urology','General Surgery');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Vascular Surgery','General Surgery');

INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Internal Medicine','Internal Medicine');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Cardiology','Internal Medicine');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Endocrinology','Internal Medicine');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Gastroenterology','Internal Medicine');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Geriatrics','Internal Medicine');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Infectious Disease','Internal Medicine');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Vascular Medicine','Internal Medicine');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Nephrology','Internal Medicine');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Neurology','Internal Medicine');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Pulomonology','Internal Medicine');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Psychiatry','Internal Medicine');

INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Paediatrics','Paediatrics');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Allergy and Immunology','Paediatrics');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Adolescent Medicinie','Paediatrics');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Paediatric Allergology','Paediatrics');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Paediatric Cardiology','Paediatrics');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Paediatric Endocrinology and Diabetes','Paediatrics');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Paediatric Gastroenterology, Hepatology and Nutrition','Paediatrics');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Paediatric Haematology and Oncology','Paediatrics');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Paediatric Infectious Diseases','Paediatrics');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Paediatric Neonatology','Paediatrics');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Paediatric Nephrology','Paediatrics');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Paediatric Respiratory Medicine','Paediatrics');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Paediatric Rheumatology','Paediatrics');

INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Neurosurgery','Surgery');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Oral and Maxillofacial Surgery','Surgery');

INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Emergency Medicine','Anaesthetics');

INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Radiology','Radiology');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Interventional Radiology','Radiology');
INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Neural Radiology','Radiology');

INSERT INTO SPECIALTY (SpecialtyID, SpecialtyGroupID) VALUES ('Sports Medicine','Family Medicine');

INSERT INTO PATIENTS (PatientID, PatientName, DateOfBirth) VALUES (1,'Ana Maria', '1960-01-01' );
INSERT INTO PATIENTS (PatientID, PatientName, DateOfBirth) VALUES (2,'Beatriz Manuela', '2010-02-02' );
INSERT INTO PATIENTS (PatientID, PatientName, DateOfBirth) VALUES (3,'Carlos José', '2000-05-17');

INSERT INTO CLINICIAN (ClinicianID, SpecialtyID, ClinicianUsername, ClinicianName) VALUES (1, 'Cardiology', 'AndreAnao', 'André Anão');
INSERT INTO CLINICIAN (ClinicianID, SpecialtyID, ClinicianUsername, ClinicianName) VALUES (2, 'Interventional Radiology', 'BernardoBernardino', 'Bernardo Bernardino');
INSERT INTO CLINICIAN (ClinicianID, SpecialtyID, ClinicianUsername, ClinicianName) VALUES (3, 'Neurosurgery', 'CarlosCarreira', 'Carlos Carreira');

INSERT INTO PATIENTS_CLINITIAN (PDID, PatientID, ClinicianID) VALUES (1, 1, 1);
INSERT INTO PATIENTS_CLINITIAN (PDID, PatientID, ClinicianID) VALUES (2, 2, 2);
INSERT INTO PATIENTS_CLINITIAN (PDID, PatientID, ClinicianID) VALUES (3, 3, 3);

/*MEDICAL RECORDS*/

INSERT INTO MEDICAL_RECORDS (RID, PatientID, ClinicianID, RecordHash, RecordInfo) VALUES (1, 1, 1, '49466ABBB680EF4115B4B97728D17C3BDD500F34EA16F128B6CCFDF29D6193BD024E9D3D4A7F1792BC291E4623362D8CE54890FBD56B8267BD6B4EB9EB2813AA', 'Heart Surgery' );
INSERT INTO MEDICAL_RECORDS (RID, PatientID, ClinicianID, RecordHash, RecordInfo) VALUES (2, 1, 2, '51EAF81CD7ACEBC60EF9DFB05F7CDCD4F9D253917E1CA762FD9A4A5A140C31D19089A16AEC99B37CF2C4EEC5AF9F5A56D50DFB3D0018AD0A2864669931875DC9', 'Radiology Analisys' );

INSERT INTO MEDICAL_RECORDS (RID, PatientID, ClinicianID, RecordHash, RecordInfo) VALUES (3, 2, 2, '1390432D2D19B81C65DBEDDF27ED83C1107CF3841356FF20BD854462D146B3F883A7CE1D8E9BD404A53FDB04DAFE96F3B1B4A19CDC588152C3D22B95E66620DB', 'Left Leg X-Ray');
INSERT INTO MEDICAL_RECORDS (RID, PatientID, ClinicianID, RecordHash, RecordInfo) VALUES (4, 2, 3, 'F2F63358A8BDF512510AC56E9D144579818D3731E1F22C717044044B399177BBD6693F34034196E92AAB3FF315EEE557DEF93AF381A91E8B52E235338DBD4CB8', 'Brain Surgery' );

/* ONE MEDICAL RECORD THAT BELONGS TO 3 SPECIALTY GROUPS*/
INSERT INTO RECORD_SPECIALTIES (RSID, RID, SpecialtyGroupID) VALUES (1, 1, 'Internal Medicine');
INSERT INTO RECORD_SPECIALTIES (RSID, RID, SpecialtyGroupID) VALUES (2, 1, 'Paediatrics');
INSERT INTO RECORD_SPECIALTIES (RSID, RID, SpecialtyGroupID) VALUES (3, 1, 'General Surgery');

INSERT INTO RECORD_SPECIALTIES (RSID, RID, SpecialtyGroupID) VALUES (4, 2, 'Radiology');
INSERT INTO RECORD_SPECIALTIES (RSID, RID, SpecialtyGroupID) VALUES (5, 2, 'General Surgery');

INSERT INTO RECORD_SPECIALTIES (RSID, RID, SpecialtyGroupID) VALUES (6, 3, 'Radiology');
INSERT INTO RECORD_SPECIALTIES (RSID, RID, SpecialtyGroupID) VALUES (7, 4, 'Surgery');

/*PASSWORDS EQUAL TO USERNAMES*/
/*HASH = SHA-512( SHA-512(password) + salt ) */
INSERT INTO LOGIN (Username, Salt, Hash, ClinicianID) VALUES ('AndreAnao', 'saltAndre', '403FF3B7DA39B0576B95B5C73E038AB7D6C046AB41091451FA09A7C19BE1246427578F81AA9DA27B1036A5C7FA1FA431C10A93D348D5129539093EA77050B310',1);
INSERT INTO LOGIN (Username, Salt, Hash, ClinicianID) VALUES ('BernardoBernardino','saltBernardo', '314ED880C220D1C85BB90A16B77EA3FEE4E68D2690C942536D472CB12D17D5B55EC3AF1BFA2D1A5BDB0A44CF32B5366ED434CB5CC69D08D9796679E98716F142',2);
INSERT INTO LOGIN (Username, Salt, Hash, ClinicianID) VALUES ('CarlosCarreira','saltCarlos', 'A164A1868C36B18AAF4DA81458B3F9943B073BFABFFC6A25849475DC0E2334778F6547F944EA34A4E43B0CBCC77FEA35BD95F41F5E75DAA3BC2FB42A4B441ADA', 3);

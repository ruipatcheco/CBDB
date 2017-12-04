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

INSERT INTO MEDICAL_RECORDS (RecordID, PatientID, ClinicianID, RecordHash) VALUES (1, 1, 1, 'insert record hash here' );

INSERT INTO RECORD_SPECIALTIES (RSID, RecordID, SpecialtyID) VALUES (1, 1, 1);
INSERT INTO RECORD_SPECIALTIES (RSID, RecordID, SpecialtyID) VALUES (2, 1, 2);
INSERT INTO RECORD_SPECIALTIES (RSID, RecordID, SpecialtyID) VALUES (3, 1, 3);

/*PASSWORDS EQUAL TO USERNAMES*/
/*HASH = SHA-512( SHA-512(password) + salt ) */
INSERT INTO LOGIN (Username, Salt, Hash, ClinicianID) VALUES ('AndreAnao', 'saltAndre', '403FF3B7DA39B0576B95B5C73E038AB7D6C046AB41091451FA09A7C19BE1246427578F81AA9DA27B1036A5C7FA1FA431C10A93D348D5129539093EA77050B310',1);
INSERT INTO LOGIN (Username, Salt, Hash, ClinicianID) VALUES ('BernardoBernardino','saltBernardo', '314ED880C220D1C85BB90A16B77EA3FEE4E68D2690C942536D472CB12D17D5B55EC3AF1BFA2D1A5BDB0A44CF32B5366ED434CB5CC69D08D9796679E98716F142',2);
INSERT INTO LOGIN (Username, Salt, Hash, ClinicianID) VALUES ('CarlosCarreira','saltCarlos', 'A164A1868C36B18AAF4DA81458B3F9943B073BFABFFC6A25849475DC0E2334778F6547F944EA34A4E43B0CBCC77FEA35BD95F41F5E75DAA3BC2FB42A4B441ADA', 3);

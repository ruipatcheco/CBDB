Open mysql connection on terminal
Create database - source cbdb_setup.sql
Populate database - populate.sql
exit
move to git folder
mvn clean install - commontypes
mvn clean install - MedicalRecordsRBAC
mvn exec:java -Dexec.mainClass="com.tenico.sirs.MedicalRecordsRBAC.FrontEndImpl"
mvn clean install - MedicalRecordsRBAC_Client
mvn exec:java -Dexec.mainClass="com.tenico.sirs.MedicalRecordsRBAC_Client.AppClientSSL"

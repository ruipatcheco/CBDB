package com.tenico.sirs.CommonTypes;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//Remote App Pattern

public interface App extends Remote
{
    Patient RegisterPatient(String name, Date birth) throws RemoteException;

    Clinician RegisterClinician(int id, String specialty, String name) throws RemoteException;

    Date addAppointment(Clinician cl, Patient pt, Date date) throws RemoteException;

    void addMedicalRecord(Patient patient, Medical_Record mr) throws RemoteException;

    void EmergencyButton() throws RemoteException;

    String getLoggedClinicianName() throws RemoteException;
    void logout() throws RemoteException;
    Map<UUID, String> listPatients() throws RemoteException;
    Medical_Record viewMedicalRecord(UUID record_id) throws RemoteException;
    List<Medical_Record> viewPatientRecords(UUID patient_id) throws RemoteException;
}

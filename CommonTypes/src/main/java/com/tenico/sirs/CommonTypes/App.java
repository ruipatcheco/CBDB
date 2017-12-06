package com.tenico.sirs.CommonTypes;

import javax.swing.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

//Remote App Pattern

public interface App extends Remote
{

    Clinician RegisterClinician(int id, String specialty, String name) throws RemoteException;
    void addMedicalRecord(Patient patient, Medical_Record mr) throws RemoteException;
    void EmergencyButton() throws RemoteException;
    String getLoggedClinicianName() throws RemoteException;
    void logout() throws RemoteException;
    Map<Integer, String> listPatients() throws RemoteException;
    String viewMedicalRecord(int record_id) throws RemoteException;
    JTable viewPatientRecords(int patient_id) throws RemoteException;
}

package com.tenico.sirs.CommonTypes;

import javax.swing.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

//Remote App Pattern

public interface App extends Remote
{
    void addMedicalRecord(Patient patient, Medical_Record mr) throws RemoteException;
    void EmergencyButton(int pt) throws RemoteException;
    void logout() throws RemoteException;
    String getLoggedClinicianName() throws RemoteException;
    Map<Integer, String> listPatients() throws RemoteException;
    Map<String, String> viewMedicalRecord(int record_id) throws RemoteException;
    JTable viewPatientRecords(int patient_id) throws RemoteException;
}

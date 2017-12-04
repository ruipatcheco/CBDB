package com.tenico.sirs.CommonTypes;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//Remote App Pattern

public interface App extends Remote
{
	String getLoggedClinicianName() throws RemoteException;
    void logout() throws RemoteException;
    Map<UUID, String> listPatients() throws RemoteException;
    Medical_Record viewMedicalRecord(UUID record_id) throws RemoteException;
    List<Medical_Record> viewPatientRecords(UUID patient_id) throws RemoteException;
}

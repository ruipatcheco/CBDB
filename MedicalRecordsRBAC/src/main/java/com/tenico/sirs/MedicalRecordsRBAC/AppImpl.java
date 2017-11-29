package com.tenico.sirs.MedicalRecordsRBAC;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.List;
import java.util.UUID;

import com.tenico.sirs.CommonTypes.*;

public class AppImpl extends UnicastRemoteObject implements App, Unreferenced {

	/**
	 * 
	 * https://stackoverflow.com/questions/13632442/how-to-organize-rmi-client-server-architecture
	 */

	//Commands: lp (listPatients), vmr (viewMedicalRecord), vpr (viewPatientRecords), whoami, help, exit");

    private static final long serialVersionUID = 1L;
	
	private Clinician loggedClinician;

	public AppImpl(String name) throws RemoteException{
		//construct Clinician from name;


		this.loggedClinician = new Clinician(new Speciality("Osteopatia", null), name);
	}


    @Override
	public void logout() throws RemoteException {
		// TODO Auto-generated method stub
		this.loggedClinician = null;
		unexportObject(this, true);
	}

    @Override
    public List<Patient> listPatients() throws RemoteException {
        return null;
    }

    @Override
    public Medical_Record viewMedicalRecord(UUID record_id) throws RemoteException {
        return null;
    }

    @Override
    public List<Medical_Record> viewPatientRecords(UUID patient_id) throws RemoteException {
        return null;
    }


    public void unreferenced()
    {
        try {
			unexportObject(this, true);
		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // needs to be in a try/catch block of course
    }

	public String getLoggedClinicianName() throws RemoteException {
		return this.loggedClinician.getName();
	}

}

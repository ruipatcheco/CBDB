package com.tenico.sirs.MedicalRecordsRBAC;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.UUID;

import com.tenico.sirs.CommonTypes.Clinician;
import com.tenico.sirs.CommonTypes.App;
import com.tenico.sirs.CommonTypes.Speciality;

public class AppImpl extends UnicastRemoteObject implements App, Unreferenced {

	/**
	 * 
	 * https://stackoverflow.com/questions/13632442/how-to-organize-rmi-client-server-architecture
	 */
	private static final long serialVersionUID = 1L;
	
	private Clinician loggedClinician;

	public AppImpl(String name) throws RemoteException{
		//construct Clinician from name;


		this.loggedClinician = new Clinician(new Speciality("Osteopatia", null), name);
	}



	public void logout() throws RemoteException {
		// TODO Auto-generated method stub
		this.loggedClinician = null;
		unexportObject(this, true);
	}

	public void listAppointments() throws RemoteException {
		// TODO Auto-generated method stub

	}

	public void listRecords(UUID patient_id) throws RemoteException {
		// TODO Auto-generated method stub

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

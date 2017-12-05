package com.tenico.sirs.MedicalRecordsRBAC;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.*;

import com.tenico.sirs.CommonTypes.*;

public class AppImpl extends UnicastRemoteObject implements App, Unreferenced {

	/**
	 * 
	 * https://stackoverflow.com/questions/13632442/how-to-organize-rmi-client-server-architecture
	 */

	//Commands: lp (listPatients), vmr (viewMedicalRecord), vpr (viewPatientRecords), whoami, help, exit");

    private static final long serialVersionUID = 1L;
	
	private Clinician loggedClinician = null;
	private DecisionPointApp dp;

	public AppImpl(String username) throws RemoteException{
		//construct Clinician from username;
		this.dp = new DecisionPointApp();

		this.loggedClinician = this.dp.getClinician(username);


	}


    @Override
	public void logout() throws RemoteException {
		// TODO Auto-generated method stub
		this.loggedClinician = null;
		unexportObject(this, true);
	}

    @Override
    public Map<UUID, String> listPatients() throws RemoteException {
        List<Patient> lst = this.dp.getListPatients(this.loggedClinician);
        Map<UUID, String> result = new HashMap<>();
        for(Patient pt : lst)
		{
			result.put(pt.getId(),pt.getName());
		}
        return result;
    }

    @Override
    public Medical_Record viewMedicalRecord(UUID record_id) throws RemoteException {
		//TODO
        return null;
    }

    @Override
    public List<Medical_Record> viewPatientRecords(UUID patient_id) throws RemoteException {
		//TODO
        return null;
    }

	@Override
    public Patient RegisterPatient(String name, Date birth) throws RemoteException
	{
		//TODO Patient is not Serializable so we must return something else
		return null;
	}

	@Override
	public Clinician RegisterClinician(int id, String specialty, String name) throws RemoteException
	{
		//TODO Who can register a clinician ? Clinician is not Serializable so we must return something else
		return null;
	}

	@Override
	public Date addAppointment(Clinician cl , Patient pt, Date date) throws RemoteException
	{
		//TODO Who can add time ?
		return null;
	}

	@Override
	public void addMedicalRecord(Patient patient, Medical_Record mr) throws RemoteException
	{
		//TODO Clinician this adds Medical Record mr to Patient patient
	}

	@Override
	public void EmergencyButton() throws RemoteException
	{
		//TODO
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
		return this.loggedClinician.getUsername();
	}

}

package com.tenico.sirs.MedicalRecordsRBAC;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.*;

import com.tenico.sirs.CommonTypes.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AppImpl extends UnicastRemoteObject implements App, Unreferenced {

	/**
	 * 
	 * https://stackoverflow.com/questions/13632442/how-to-organize-rmi-client-server-architecture
	 */

	//Commands: lp (listPatients), vmr (viewMedicalRecord), vpr (viewPatientRecords), whoami, help, exit");

    private static final long serialVersionUID = 1L;
	
	private Clinician loggedClinician = null;
	private DecisionPointApp dp;
	private List<Patient> lstPatients;

	public AppImpl(String username) throws RemoteException{
		//construct Clinician from username;
		this.dp = new DecisionPointApp();
		this.loggedClinician = this.dp.getClinician(username);
		this.lstPatients = this.dp.getListPatients(this.loggedClinician);
		if(this.lstPatients.size() > 0) {
			for (Patient patient : this.lstPatients) {
				this.loggedClinician.Add_Patient(new Patient_Clinician(patient.getId(), this.loggedClinician.getID()));
				List<Medical_Record> lst = this.dp.viewPatientRecords(this.loggedClinician, patient);
				if (lst.size() > 0) {
					for (Medical_Record mr : lst) {
						patient.addRecord(mr);
					}
				}
			}
		}
	}

    @Override
	public void logout() throws RemoteException {
		// TODO Auto-generated method stub
		this.loggedClinician = null;
		unexportObject(this, true);
	}

    @Override
    public Map<Integer, String> listPatients() throws RemoteException {
        Map<Integer, String> result = new HashMap<>();
        for(Patient pt : this.lstPatients) {
			result.put(pt.getId(),pt.getName());
		}
        return result;
    }

    @Override
    public String viewMedicalRecord(int record_id) throws RemoteException {
		for(Patient pt : this.lstPatients)
		{
			for(Medical_Record mr : pt.getRecords())
			{
				if(mr.getId() == record_id)
				{
					return mr.getInfo();
				}
			}
		}
        return null;
    }

    @Override
    public JTable viewPatientRecords(int patient_id) throws RemoteException {
		JTable table = new JTable(new DefaultTableModel(new Object[]{"ID", "Clinician"}, 0));

		for(Patient patient : this.lstPatients)
		{
			if(patient.getId() == patient_id) {
				for (Medical_Record mr : patient.getRecords()) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.addRow(new Object[]{mr.getId(), this.loggedClinician.getName()});
				}
			}
		}
        return table;
    }


	@Override
	public Clinician RegisterClinician(int id, String specialty, String name) throws RemoteException
	{
		//TODO Who can register a clinician ? Clinician is not Serializable so we must return something else
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

package com.tenico.sirs.MedicalRecordsRBAC;

import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.*;

import com.tenico.sirs.CommonTypes.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.io.FileWriter;

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

	private File log = null;
	private FileWriter fw = null;
	private String logFileName = null;


	public AppImpl(String username) throws RemoteException{
		//construct Clinician from username;
		this.dp = new DecisionPointApp();
		this.loggedClinician = this.dp.getClinician(username);
		this.logFileName = System.getProperty("user.dir") + "/MedicalRecordsRBAC/src/logs/" +
				this.loggedClinician.getUsername() + ".txt";
		this.log = new File(logFileName);

		try {
			log.createNewFile();
			this.fw = new FileWriter(logFileName, true);
			Date date = new Date();
			this.fw.append( date.toString() + ": Logged In\n" );
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

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

		this.loggedClinician = null;
		unexportObject(this, true);
		try {
			this.fw = new FileWriter(logFileName, true);
			Date date = new Date();
			this.fw.write(date.toString() + ": Logged Out\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

    @Override
    public Map<Integer, String> listPatients() throws RemoteException {
        Map<Integer, String> result = new HashMap<>();
        for(Patient pt : this.lstPatients) {
			result.put(pt.getId(),pt.getName());
		}

		try {
			this.fw = new FileWriter(logFileName, true);
			Date date = new Date();
			this.fw.write(date.toString() + ": Listed Patients\n");

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
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
					try {
						this.fw = new FileWriter(logFileName, true);
						Date date = new Date();
						this.fw.write(date.toString() + ": Viewed Medical Record " + record_id +  "\n");
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

					return "Medical Record Here";
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

		try {
			this.fw = new FileWriter(logFileName, true);
			Date date = new Date();
			this.fw.write(date.toString() + ": View Patient Medical Records of " + patient_id + "\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

        return table;
    }


	@Override
	public void addMedicalRecord(Patient patient, Medical_Record mr) throws RemoteException
	{
		//TODO Clinician this adds Medical Record mr to Patient patient
	}

	@Override
	public void EmergencyButton(int pt) throws RemoteException
	{
		Patient patient = null;
		for(Patient p : lstPatients)
		{
			if(p.getId() == pt)
				patient = p;
		}
		if(patient != null) {
			this.dp.emergency(this.loggedClinician, patient);

			try {
				this.fw = new FileWriter(logFileName, true);
				Date date = new Date();
				this.fw.write(date.toString() + ": Emergency button used on " + pt + "\n");
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}


    public void unreferenced()
    {
        try {
			unexportObject(this, true);
		} catch (NoSuchObjectException e) {

			e.printStackTrace();
		} // needs to be in a try/catch block of course
    }

	public String getLoggedClinicianName() throws RemoteException {
		return this.loggedClinician.getUsername();
	}

}

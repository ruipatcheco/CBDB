package com.tenico.sirs.MedicalRecordsRBAC;

import com.mysql.jdbc.PreparedStatement;
import com.tenico.sirs.CommonTypes.Clinician;
import com.tenico.sirs.CommonTypes.Patient;
import com.tenico.sirs.CommonTypes.SpecialityGroup;
import com.tenico.sirs.CommonTypes.Specialty;
import sun.util.calendar.Gregorian;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DecisionPointApp extends DecisionPointBase {

    public DecisionPointApp() {
        super();
    }

    public Clinician getClinician(String username){
        Clinician c = null;

        String query = "SELECT ClinicianID, SpecialtyID, ClinicianUsername FROM CLINICIAN WHERE ClinicianUsername LIKE ?";
        try
        {
            // create the preparedstatement and add the criteria
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,username);

            // process the results
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ClinicianID");
                Specialty specialty = getSpecialty(rs.getString("SpecialtyID"));

                c = new Clinician(id, specialty, username);
            }

            rs.close();
            ps.close();
        }
        catch (SQLException se)
        {
            // log exception;
            se.printStackTrace();
        }

        return c;
    }

    public Specialty getSpecialty (String specialty){
        Specialty s = null;

        String query = "SELECT SpecialtyID, SpecialtyGroupID FROM SPECIALTY WHERE SpecialtyID LIKE ?";
        try
        {
            // create the prepared statement and add the criteria
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,specialty);

            // process the results
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String name = rs.getString("SpecialtyID");
                SpecialityGroup group = new SpecialityGroup(rs.getString("SpecialtyGroupID"));
                s = new Specialty(name, group);
            }

            rs.close();
            ps.close();
        }
        catch (SQLException se)
        {
            // log exception;
            se.printStackTrace();
        }

        return s;

    }

    public List<Patient> getListPatients(Clinician cl)
    {
        List<Patient> lst = new ArrayList<>();

        String query = "SELECT PatientID FROM PATIENTS_CLINICIAN WHERE ClinicianID LIKE ?";

        try
        {
            // create the prepared statement and add the criteria
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "" + cl.getID());

            // process the results
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String patientID = rs.getString("PatientID");
                Patient patient = new Patient(patientID, new Date(this.getPatientBirth(patientID)));
                lst.add(patient);
            }

            rs.close();
            ps.close();

        }
        catch (SQLException se)
        {
            // log exception;
            se.printStackTrace();
        }

        return lst;
    }


    public long getPatientBirth(String patientID) {
        long date = 000000;

        String query = "SELECT DateOfBirth FROM CBDB.PATIENTS WHERE PatientID LIKE ?";

        try
        {
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, patientID);

            // process the results
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                date = rs.getLong("DateOfBirth");
            }

            rs.close();
            ps.close();

        }
        catch (SQLException se)
        {
            // log exception;
            se.printStackTrace();
        }

        return date;

    }
}

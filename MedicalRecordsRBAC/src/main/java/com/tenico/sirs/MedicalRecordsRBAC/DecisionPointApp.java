package com.tenico.sirs.MedicalRecordsRBAC;

import com.mysql.jdbc.PreparedStatement;
import com.tenico.sirs.CommonTypes.*;
import sun.util.calendar.Gregorian;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DecisionPointApp extends DecisionPointBase {

    public DecisionPointApp() {
        super();
    }

    public Clinician getClinician(String username){
        Clinician c = null;

        String query = "SELECT ClinicianID, SpecialtyID, ClinicianName FROM CLINICIAN WHERE ClinicianUsername LIKE ?";
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
                String name = rs.getString("ClinicianName");

                c = new Clinician(id, specialty, username, name);
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

        String query = "SELECT PatientID FROM PATIENTS_CLINITIAN WHERE ClinicianID LIKE ?";

        try
        {
            // create the prepared statement and add the criteria
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, cl.getID());

            // process the results
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int patientID = rs.getInt("PatientID");
                Patient patient = new Patient(patientID, this.getPatientNameFromID(patientID), new Date(this.getPatientBirth(patientID)));
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

    public String getPatientNameFromID(int id)
    {
        String name = null;

        String query = "SELECT PatientName FROM CBDB.PATIENTS WHERE PatientID LIKE ?";

        try
        {
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            // process the results
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                name = rs.getString("PatientName");
            }

            rs.close();
            ps.close();

        }
        catch (SQLException se)
        {
            // log exception;
            se.printStackTrace();
        }

        return name;
    }


    public long getPatientBirth(int patientID) {
        long date = 000000;

        String query = "SELECT DateOfBirth FROM CBDB.PATIENTS WHERE PatientID LIKE ?";

        try
        {
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, patientID);

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

    public Medical_Record viewMedicalRecord(int record_id, Clinician loggedClinician) {

        return null;
    }

    public List<Medical_Record> viewPatientRecords(Clinician cl, Patient patient) {
        List<Medical_Record> result = null;

        List<Integer> RIDS = new ArrayList<>();

        String query = "SELECT RID FROM CBDB.MEDICAL_RECORDS WHERE PatientID LIKE ?";

        try
        {
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, patient.getId());

            // process the results
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("RID");
                RIDS.add(id);
            }

            rs.close();
            ps.close();

        }
        catch (SQLException se)
        {
            // log exception;
            se.printStackTrace();
        }

        if(RIDS.size() > 0)
        {
            query = "SELECT RID FROM RECORD_SPECIALTIES WHERE RECORD_SPECIALTIES.RID LIKE ? " +
                    "AND RECORD_SPECIALTIES.SpecialtyGroupID LIKE ?";

            List<Integer> RIDSFinal = new ArrayList<>();

            for(Integer rid : RIDS)
            {
                try
                {
                    java.sql.PreparedStatement ps = con.prepareStatement(query);
                    ps.setInt(1, rid);
                    ps.setString(2, cl.getSpecialty().getGroup().getName());

                    // process the results
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        int rid1 = rs.getInt("RID");
                        RIDSFinal.add(rid1);
                    }

                    rs.close();
                    ps.close();

                }
                catch (SQLException se)
                {
                    // log exception;
                    se.printStackTrace();
                }
            }

            if(RIDSFinal.size() > 0)
            {
                result = new ArrayList<>();

                query = "SELECT RecordHash, RecordInfo FROM CBDB.MEDICAL_RECORDS WHERE MEDICAL_RECORDS.RID LIKE ?";

                for(Integer rid : RIDSFinal) {

                    try {
                        java.sql.PreparedStatement ps = con.prepareStatement(query);
                        ps.setInt(1, rid);

                        // process the results
                        ResultSet rs = ps.executeQuery();

                        while (rs.next()) {
                            String hash = rs.getString("RecordHash");
                            String info = rs.getString("RecordInfo");
                            result.add(new Medical_Record(rid, patient.getId(), cl.getID(), hash, info));
                        }

                        rs.close();
                        ps.close();

                    } catch (SQLException se) {
                        // log exception;
                        se.printStackTrace();
                    }
                }
            }
        }


        return result;
    }


    public void emergency(Clinician cl, Patient pt) {

        List<Integer> RIDS = new ArrayList<>();

        String query = "SELECT RID FROM CBDB.MEDICAL_RECORDS WHERE PatientID LIKE ?";

        try
        {
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, pt.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("RID");
                RIDS.add(id);
            }

            rs.close();
            ps.close();

        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }

        if (RIDS.size() > 0) {
            for (int mr : RIDS) {

                boolean exists = false;

                for(Medical_Record mrCl : pt.getRecords())
                {
                    if(mrCl.getId() == mr)
                    {
                        exists = true;
                    }
                }

                if(!exists)
                {
                    try {
                        query = "SELECT RecordHash, RecordInfo FROM CBDB.MEDICAL_RECORDS WHERE MEDICAL_RECORDS.RID LIKE ?";

                        java.sql.PreparedStatement ps = con.prepareStatement(query);
                        ps.setInt(1, mr);

                        // process the results
                        ResultSet rs = ps.executeQuery();

                        while (rs.next()) {
                            String hash = rs.getString("RecordHash");
                            String info = rs.getString("RecordInfo");
                            pt.addRecord(new Medical_Record(mr, pt.getId(), cl.getID(), hash, info));
                        }

                        rs.close();
                        ps.close();

                    } catch (SQLException se) {
                        // log exception;
                        se.printStackTrace();
                    }
                }
            }
        }
    }
}

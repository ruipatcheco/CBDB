package com.tenico.sirs.MedicalRecordsRBAC;

import com.mysql.jdbc.PreparedStatement;
import com.tenico.sirs.CommonTypes.Clinician;
import com.tenico.sirs.CommonTypes.SpecialityGroup;
import com.tenico.sirs.CommonTypes.Specialty;

import java.sql.ResultSet;
import java.sql.SQLException;

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
            int id = rs.getInt("ClinicianID");
            String specialty = rs.getString("SpecialtyID");

            c = new Clinician(id, specialty, username);

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

    public Specialty getSpecialty (){
        Specialty s = null;

        String query = "SELECT SpecialtyID, SpecialtyGroupID FROM SPECIALTY WHERE ClinicianUsername LIKE ?";
        try
        {
            // create the preparedstatement and add the criteria
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,username);

            // process the results
            ResultSet rs = ps.executeQuery();
            int id = rs.getInt("ClinicianID");
            String specialty = rs.getString("SpecialtyID");

            c = new Clinician(id, specialty, username);

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


}

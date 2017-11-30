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


}

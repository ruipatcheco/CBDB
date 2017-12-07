package com.tenico.sirs.MedicalRecordsRBAC;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DecisionPointAuthenticator extends DecisionPointBase {

    public DecisionPointAuthenticator() {
        super();
    }

    /*
       Translate passwordHash from bytes to String in client -> String(passwordHash)
    * */
    public boolean VerifyLogin(String username, String passwordHash) {
        //check username
        //sanitize username
        //if not found -> login failed
        //not bad username info

        //get salt + stored Hash
        String salt = null;
        String storedHash = null;
        Statement stmt = null;
        String query = "SELECT Salt, Hash FROM CBDB.LOGIN WHERE Username LIKE ?";

        /*
        * Should be using PreparedStatements to protect from sql injections
        *
        * PreparedStatement pstmt = con.prepareStatement(""SELECT Salt FROM CBDB.Login WHERE Username = ?")
        * pstmt.setString(1, username);
        *
        * */

        try {
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                salt = rs.getString("Salt");
                storedHash = rs.getString("Hash");
            }
            rs.close();
            ps.close();

        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }

        //hash(passwordhash+salt)

        String input = passwordHash + salt;


        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("SHA-512 not found");
            e.printStackTrace();
        }

        byte[] result = messageDigest.digest(input.getBytes());

        String finalResult = tohex(result);

        return finalResult.equals(storedHash);
    }

    public static String tohex(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (int i = 0; i < data.length; i++) {
            sb.append(String.format("%02X", data[i] & 0xFF));
        }
        return sb.toString();
    }

}

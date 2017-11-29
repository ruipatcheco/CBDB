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
        String query = "SELECT Salt FROM CBDB.Login WHERE Username = '" + username + "'";

        /*
        * Should be using PreparedStatements to protect from sql injections
        *
        * PreparedStatement pstmt = con.prepareStatement(""SELECT Salt FROM CBDB.Login WHERE Username = ?")
        * pstmt.setString(1, username);
        *
        * */

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                salt = rs.getString("Salt");
                storedHash = rs.getString("Hash");
            }
        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
            e.printStackTrace();
            System.out.println("Bad Login information");
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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

        String finalResult = new String(result);

        return finalResult.equals(storedHash);
    }

}

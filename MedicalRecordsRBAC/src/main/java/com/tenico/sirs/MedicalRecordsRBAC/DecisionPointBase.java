package com.tenico.sirs.MedicalRecordsRBAC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DecisionPointBase {

    protected Connection con;

    public DecisionPointBase(){
        this.con = connect2Database();
    }

    private Connection connect2Database(){
        String url = "jdbc:mysql://localhost:3306/CBDB";
        String username = "cbdb_su";
        String password = "password";

        System.out.println("Connecting to database...");
        System.out.println("Loading driver...");


        try {
            Connection mysqlConnection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
            return mysqlConnection;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void disconnectDatabase(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

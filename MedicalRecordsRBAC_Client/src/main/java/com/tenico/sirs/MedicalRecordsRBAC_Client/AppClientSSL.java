package com.tenico.sirs.MedicalRecordsRBAC_Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.rmi.ssl.SslRMIClientSocketFactory;

import com.tenico.sirs.CommonTypes.Login;
import com.tenico.sirs.CommonTypes.ServerInterface;
import com.tenico.sirs.CommonTypes.Session;


public class AppClientSSL {

    private AppClientSSL() {}

    public static void main(String[] args) {        

        try {

        	int port = 1099;
        	String boundingName = "Login";
        	
            setSettings();  

            Registry registry = LocateRegistry.getRegistry("localhost", port, new SslRMIClientSocketFactory());

            Login login = (Login) registry.lookup(boundingName);

            Session s = login.login("username", "password".toCharArray());
            Session s2 = login.login("username1", "password".toCharArray());
            
            String name = s.getLoggedClinicianName();
            String name2 = s2.getLoggedClinicianName();
            
            System.out.println(name);
            System.out.println(name2);

            name = s.getLoggedClinicianName();

            System.out.println(name);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

private static void setSettings() {

        String pass = "password";
        System.setProperty("javax.net.ssl.debug", "all");
        //System.setProperty("javax.net.ssl.keyStore", "C:\\ssl\\clientkeystore.jks");
	    //System.setProperty("javax.net.ssl.keyStorePassword", pass);
        System.setProperty("javax.net.ssl.trustStore", "/home/basilio/MEGAsync/SIRS/projeto/SSL/client/client.truststore.jks");
	    System.setProperty("javax.net.ssl.trustStorePassword", pass);
    }

}
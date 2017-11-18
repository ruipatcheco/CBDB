package com.tenico.sirs.MedicalRecordsRBAC_Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.rmi.ssl.SslRMIClientSocketFactory;

import com.tenico.sirs.CommonTypes.ServerInterface;


public class ClientSSL {

    private ClientSSL() {}

    public static void main(String[] args) {        

        try {

        	int port = 1099;
        	
            setSettings();  

            Registry registry = LocateRegistry.getRegistry("localhost", port, new SslRMIClientSocketFactory());

            ServerInterface hello = (ServerInterface) registry.lookup("Hello");

            String message = hello.helloTo("clientssl");

            System.out.println(message);            

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
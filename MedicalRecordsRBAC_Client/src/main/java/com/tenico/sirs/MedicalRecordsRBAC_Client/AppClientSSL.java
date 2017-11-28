package com.tenico.sirs.MedicalRecordsRBAC_Client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.security.auth.login.LoginException;

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

            Login l = (Login) registry.lookup(boundingName);
            
            System.out.println("Application started succesfully");
        	Scanner inputScanner = new Scanner(System.in);
            
            Session s = loginManager(l,inputScanner);
            
            sessionManager(s,inputScanner);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
    private static void sessionManager(Session s, Scanner inputScanner) {
    	String input;
    	Boolean logout = false;
        System.out.println("Write help for help");
    	
    	while(true) {
    		try {
				if(logout) {return;}
		        input = inputScanner.next();
		        
		        if (!input.isEmpty()) {
		            logout = parseCommand(input, s);
		        }
    		}
	        catch(RemoteException e){
	            System.out.println("An error occured");
	            e.printStackTrace();
	        }

	    }
	}
    
    private enum Command {
        lp, la, vmr, vpr, whoami, help, exit;
    }
    
    private static Boolean parseCommand(String input, Session s) throws RemoteException {
    	Command c = Command.valueOf(input);
    	
    	switch(c) {
    		case help:
            	System.out.println("Commands: lp (listPatients), la (listAppointments), vmr (viewMedicalRecord), vpr (viewPatientRecords), whoami, help, exit");
            	break;
    		case exit:
    			s.logout();
    			return true;
    		case whoami:
    			System.out.println(s.getLoggedClinicianName());
    	}
    	
    	return false;
	}

	private static Session loginManager(Login l, Scanner inputScanner) {
    	
    	while(true) {
	        System.out.print("Enter your username: ");
	        String username = inputScanner.next();
	    	
	        System.out.print("Enter your password: ");
	        String password = inputScanner.next();
	        
	        try {
	            Session s = l.login(username, password.toCharArray());
	            return s;
	        }
	        catch(LoginException|RemoteException e){
	            System.out.println("Login failed, try again");
	            e.printStackTrace();
	        }
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

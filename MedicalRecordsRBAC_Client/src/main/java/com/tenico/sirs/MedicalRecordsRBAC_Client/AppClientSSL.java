package com.tenico.sirs.MedicalRecordsRBAC_Client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.UUID;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.security.auth.login.LoginException;

import com.tenico.sirs.CommonTypes.App;
import com.tenico.sirs.CommonTypes.FrontEnd;


public class AppClientSSL {

    private AppClientSSL() {}

    public static void main(String[] args) {        

        try {
        	int port = 1099;
        	String boundingName = "FrontEnd";
        	
            setSettings();  

            Registry registry = LocateRegistry.getRegistry("localhost", port, new SslRMIClientSocketFactory());

            FrontEnd l = (FrontEnd) registry.lookup(boundingName);
            
            System.out.println("Application started succesfully");
        	Scanner inputScanner = new Scanner(System.in);
            
            App s = loginManager(l,inputScanner);
            
            sessionManager(s,inputScanner);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
    private static void sessionManager(App s, Scanner inputScanner) {
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
        lp, vmr, vpr, whoami, help, exit;
    }
    
    private static Boolean parseCommand(String input, App s) throws RemoteException {
		String str = input;
		String[] splitStr = str.split("\\s+");

    	Command c = Command.valueOf(splitStr[0]);
    	
    	switch(c) {
    		case help:
            	System.out.println("Commands: lp (listPatients), la (listAppointments), vmr (viewMedicalRecord), vpr (viewPatientRecords), whoami, help, exit");
            	break;
    		case exit:
    			s.logout();
    			return true;
    		case whoami:
    			System.out.println(s.getLoggedClinicianName());
				break;

			case vpr:
				UUID patient_id = UUID.fromString(splitStr[1]);
				System.out.println(s.viewPatientRecords(patient_id));
				break;

			case vmr:
				UUID record_id = UUID.fromString(splitStr[1]);
				System.out.println(s.viewMedicalRecord(record_id));
				break;

			case lp:
				System.out.println(s.listPatients());
				break;

			default:
				System.out.println("Commands: lp (listPatients), la (listAppointments), vmr (viewMedicalRecord), vpr (viewPatientRecords), whoami, help, exit");
				break;
    	}
    	
    	return false;
	}

	private static App loginManager(FrontEnd l, Scanner inputScanner) {
    	
    	while(true) {
	        System.out.print("Enter your username: ");
	        String username = inputScanner.next();
	    	
	        System.out.print("Enter your password: ");
	        String password = inputScanner.next();
	        
	        try {
	            App s = l.login(username, password.toCharArray());
	            return s;
	        }
	        catch(LoginException|RemoteException e){
	            System.out.println("FrontEnd failed, try again");
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


package com.tenico.sirs.MedicalRecordsRBAC_Client;

import java.awt.print.PrinterException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.security.auth.login.LoginException;
import javax.swing.*;

import com.sun.xml.internal.ws.api.message.Message;
import com.tenico.sirs.CommonTypes.App;
import com.tenico.sirs.CommonTypes.FrontEnd;

import java.security.MessageDigest;


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
		        input = inputScanner.nextLine();
		        
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
        lp, vmr, vpr, whoami, help, exit, emergency, amr;
    }
    
    private static Boolean parseCommand(String input, App s) throws RemoteException {
		String str = input;
		String[] splitStr = str.split("\\s+");


		try
		{
			Command c = Command.valueOf(splitStr[0]);

			switch(c) {
				case amr:
					if(splitStr.length >= 3) {
						MessageDigest messageDigest = null;

						String mr = "";

						for(int i = 2; i<splitStr.length; i++)
						{
							mr = mr + ' ' + splitStr[i];
						}

						try {
							messageDigest = MessageDigest.getInstance("SHA-512");
						} catch (NoSuchAlgorithmException e) {
							System.out.println("SHA-512 not found");
							e.printStackTrace();
						}

						byte[] RecordHashInClientBytes = messageDigest.digest(mr.getBytes());

						String RecordHashInClient = tohex(RecordHashInClientBytes);

						int patient_id = Integer.parseInt(splitStr[1]);
						System.out.println(s.addMedicalRecord(patient_id,mr,RecordHashInClient));
					}
					break;
				case emergency:
					if(splitStr.length == 2) {
						int patient_id = Integer.parseInt(splitStr[1]);
						s.EmergencyButton(patient_id);
					}
					break;
				case help:
					System.out.println("Commands: lp (listPatients), vmr (viewMedicalRecord RecordId(int)), vpr (viewPatientRecords PatientId(int)), amr (addMedicalRecord PatientID(int) Content(String)), whoami, help, exit");
					break;
				case exit:
					s.logout();
					return true;
				case whoami:
					System.out.println(s.getLoggedClinicianName());
					break;

				case vpr:
					if(splitStr.length == 2) {
						int patient_id = Integer.parseInt(splitStr[1]);
						JTable tabl = s.viewPatientRecords(patient_id);
						int i = 0;
						while (i < tabl.getRowCount()) {
							System.out.println("Medical Record ID: " + tabl.getModel().getValueAt(i, 0) + '\n'
									+ '\t' + " Clinician Name: " +
									tabl.getModel().getValueAt(i, 1));
							i++;
						}
					}
					else
						System.out.println("Write help for help");
					break;

				case vmr:
					if(splitStr.length == 2) {
						int record_id = Integer.parseInt(splitStr[1]);
						Map<String, String> mr= s.viewMedicalRecord(record_id);
						if(mr == null)
							System.out.println("You don't have access to this record");
						else {
							String RecordHashInServer = null;
							String RecordInfo = null;

							for (Map.Entry<String, String> entry : mr.entrySet())
							{
								RecordHashInServer = entry.getKey();
								RecordInfo = entry.getValue();
							}

							MessageDigest messageDigest = null;

							try {
								messageDigest = MessageDigest.getInstance("SHA-512");
							} catch (NoSuchAlgorithmException e) {
								System.out.println("SHA-512 not found");
								e.printStackTrace();
							}

							byte[] RecordHashInClientBytes = messageDigest.digest(RecordInfo.getBytes());

							String RecordHashInClient = tohex(RecordHashInClientBytes);
							
							if(RecordHashInClient.equals(RecordHashInServer)){
								System.out.println(mr.get(RecordHashInServer));
							}
							else {
								System.out.println("Information not Trustworthy. Please Try Again.");
							}
						}
					}
					else
						System.out.println("Write help for help");
					break;

				case lp:
					Map<Integer,String> result = s.listPatients();
					for (Map.Entry<Integer, String> entry : result.entrySet())
					{
						System.out.println("Patient ID: " + entry.getKey() + '\n' + '\t'
								+ "Patient Name: " + entry.getValue());
					}
					break;

				default:
					System.out.println("Commands: lp (listPatients), vmr (viewMedicalRecord RecordId(int)), vpr (viewPatientRecords PatientId(int)), amr (addMedicalRecord PatientID(int) Content(String)), whoami, help, exit");
					break;
			}
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Commands: lp (listPatients), la (listAppointments), vmr (viewMedicalRecord), vpr (viewPatientRecords), amr (addMedicalRecord), whoami, help, exit");
		}

		return false;
	}

	private static App loginManager(FrontEnd l, Scanner inputScanner) {

    	int counter = 0;
    	
    	while(true) {
	        System.out.print("Enter your username: ");
	        String username = inputScanner.nextLine();
	    	
	        System.out.print("Enter your password: ");
	        String password = inputScanner.nextLine();

			//Hash The Password
			MessageDigest messageDigest = null;

			try {
				messageDigest = MessageDigest.getInstance("SHA-512");
			} catch (NoSuchAlgorithmException e) {
				System.out.println("SHA-512 not found");
				e.printStackTrace();
			}

			byte[] passwordHashBytes = messageDigest.digest(password.getBytes());

			String passwordHash = tohex(passwordHashBytes);


			if(counter < 2) {
				try {
					App s = l.login(username, passwordHash);
					return s;
				} catch (LoginException | RemoteException e) {
					counter++;
					System.out.println("Wrong Credentials");
					System.out.println(3 - counter + " tentatives remaining");
				}
			}
			else
			{
				try {
					System.out.println("Failed to authenticate 3 times, waiting 60 seconds before you" +
							" can try again");
					Thread.sleep(60000);
					counter = 0;
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
    	}

    }

	public static String tohex(byte[] data) {
		StringBuilder sb = new StringBuilder(data.length * 2);
		for (int i = 0; i < data.length; i++) {
			sb.append(String.format("%02X", data[i] & 0xFF));
		}
		return sb.toString();
	}

    private static void setSettings() {

        String pass = "password";
        System.setProperty("javax.net.ssl.debug", "all");
        //System.setProperty("javax.net.ssl.keyStore", "C:\\ssl\\clientkeystore.jks");
	    //System.setProperty("javax.net.ssl.keyStorePassword", pass);
		System.setProperty("javax.net.ssl.trustStore", System.getProperty("user.dir") + "/SSL/client/client.truststore.jks");
	    System.setProperty("javax.net.ssl.trustStorePassword", pass);
    }

}


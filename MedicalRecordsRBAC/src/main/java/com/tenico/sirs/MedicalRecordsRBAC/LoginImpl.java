package com.tenico.sirs.MedicalRecordsRBAC;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.security.auth.login.LoginException;

import com.tenico.sirs.CommonTypes.Login;
import com.tenico.sirs.CommonTypes.Session;

public class LoginImpl extends UnicastRemoteObject implements Login
{
    /**
	 * 
	 * https://stackoverflow.com/questions/13632442/how-to-organize-rmi-client-server-architecture
	 */
	
	private static final long serialVersionUID = 1L;

	protected LoginImpl(int port) throws RemoteException {
        super(port, new SslRMIClientSocketFactory(), new SslRMIServerSocketFactory(null, null, false));      
	}

	public Session login(String name, char[] password) throws LoginException, RemoteException{
        // name/password check; if it fails throw a LoginException
    	Session s = new SessionImpl(name); 
    	
        return s;
    }
	
	public static void main(String[] args) throws RemoteException, IllegalArgumentException {

        try {          
        	int port = 1099;
        	String boundingName = "Login";

            setSettings();

            LoginImpl login = new LoginImpl(port);
            
            Registry registry = null;
            try {
                registry = LocateRegistry.getRegistry("localhost", port, new SslRMIClientSocketFactory());;//use any no. less than 55000
                registry.list(); // This call will throw an exception if the registry does not already exist

            }
            catch (RemoteException e) { 
                registry = LocateRegistry.createRegistry(port, new SslRMIClientSocketFactory(), new SslRMIServerSocketFactory(null, null, false));
            }

            System.out.println("RMI registry running on port " + port);             
            registry.rebind(boundingName,  login);
            

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

    }

    private static void setSettings() {

        String pass = "password";

        System.setProperty("javax.net.ssl.debug", "all");

	    System.setProperty("javax.net.ssl.keyStore", "/home/basilio/MEGAsync/SIRS/projeto/SSL/server.keystore.jks");
	    System.setProperty("javax.net.ssl.keyStorePassword", pass);
	    //System.setProperty("javax.net.ssl.trustStore", "C:\\ssl\\servertruststore.jks");
	    //System.setProperty("javax.net.ssl.trustStorePassword", pass);
    }
}
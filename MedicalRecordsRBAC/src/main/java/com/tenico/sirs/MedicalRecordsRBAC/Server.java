package com.tenico.sirs.MedicalRecordsRBAC;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import com.tenico.sirs.CommonTypes.ServerInterface;



public class Server extends UnicastRemoteObject implements ServerInterface{

	private static final long serialVersionUID = 1L;

	protected Server() throws RemoteException {

        super();

    }

    public String helloTo(String name) throws RemoteException{

        System.err.println(name + " is trying to contact!");
        return "Server says hello to " + name;

    }

    public static void main(String[] args){

        try {
        	Server s = new Server();
        	
        	LocateRegistry.createRegistry(1099);
            Naming.rebind("//localhost:1099/MyServer", s);
            System.err.println("Server ready");

        } catch (Exception e) {

            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();

        }

    }

}
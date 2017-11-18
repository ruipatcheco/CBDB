package com.tenico.sirs.MedicalRecordsRBAC_Client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.tenico.sirs.CommonTypes.ServerInterface;

public class Client {

	private static ServerInterface server;

	public static void main(String[] args)
		throws NotBoundException, IOException {

		server = (ServerInterface) Naming.lookup("//localhost:1099/MyServer");

		String response = server.helloTo("joaquim");
		System.out.println(response);

		System.in.read();
	}

}
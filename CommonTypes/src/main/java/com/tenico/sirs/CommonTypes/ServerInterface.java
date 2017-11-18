package com.tenico.sirs.CommonTypes;

import java.rmi.RemoteException;
import java.rmi.Remote;

public interface ServerInterface extends Remote{

	public String helloTo(String name) throws RemoteException;
}

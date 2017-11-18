package com.tenico.sirs.CommonTypes;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.security.auth.login.LoginException;

//Remote Session Pattern

public interface Login extends Remote
{
    Session login(String username, char[] password /* or whatever */)
        throws LoginException, RemoteException;
}
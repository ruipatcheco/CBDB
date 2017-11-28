package com.tenico.sirs.CommonTypes;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.security.auth.login.LoginException;

//Remote App Pattern

public interface FrontEnd extends Remote
{
    App login(String username, char[] password /* or whatever */)
        throws LoginException, RemoteException;
}
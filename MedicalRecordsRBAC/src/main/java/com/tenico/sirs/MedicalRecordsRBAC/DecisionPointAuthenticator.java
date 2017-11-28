package com.tenico.sirs.MedicalRecordsRBAC;

import java.sql.Connection;

public class DecisionPointAuthenticator extends DecisionPointBase {

    public DecisionPointAuthenticator() {
        super();
    }

    public boolean VerifyLogin(String username, char[] password){
        //TODO verify login
        return true;
    }
}

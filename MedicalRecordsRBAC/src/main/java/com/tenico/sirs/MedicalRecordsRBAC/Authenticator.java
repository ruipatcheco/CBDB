package com.tenico.sirs.MedicalRecordsRBAC;

public class Authenticator {
    private DecisionPointAuthenticator dp;

    public Authenticator() {
        this.dp = new DecisionPointAuthenticator();
    }

    public boolean verifyLogin(String username, char[] password){
        //TODO verificar cartao cidadao

        return dp.VerifyLogin(username,password);
    }


}

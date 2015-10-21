package jed.authlab.security;

/**
 * Created by Jeppe Dickow on 10/21/2015.
 */
public class CredentialsManager {
    private static CredentialsManager ourInstance = new CredentialsManager();

    public static CredentialsManager getInstance() {
        return ourInstance;
    }

    private CredentialsManager() {
    }

    public boolean authenticate(String securePass){
        return true;
    }

}

package jed.authlab.security;

/**
 * Created by Jeppe Dickow on 11/14/2015.
 */
public class RoleBaseAccessReader {
    private static RoleBaseAccessReader ourInstance = new RoleBaseAccessReader();

    public static RoleBaseAccessReader getInstance() {
        return ourInstance;
    }

    private RoleBaseAccessReader() {
    }
}

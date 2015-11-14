package jed.authlab.security;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by Jeppe Dickow on 11/14/2015.
 */
public class RoleBasedAccessReader {
    private Hashtable<Role,String[]> roleMatrix;
    private static RoleBasedAccessReader ourInstance = new RoleBasedAccessReader();

    public static RoleBasedAccessReader getInstance() {
        return ourInstance;
    }

    private RoleBasedAccessReader() {
    }

    /***
     * Read the access policy at the specified location
     * @param filePath
     * @return
     */
    private void readAccessFile(String filePath){
        roleMatrix = new Hashtable<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            while(line != null){
                Role role = Role.valueOf(line.substring(0, line.indexOf(":")));
                String[] splitString = line.split(":");
                String[] access = splitString[1].split(",");
                roleMatrix.put(role, access);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("Could not read file");
            e.printStackTrace();
        }
    }

    public boolean hasAccess(Role role, String accessRequest){
        if(roleMatrix == null){
            readAccessFile("AccessRoles.txt");
        }
        if(roleMatrix.containsKey(role)){
            return match(roleMatrix.get(role), accessRequest);
        }
        return false;
    }

    private boolean match(String[] accesses, String accessRequest){
        for(int i = 0; i < accesses.length; i++){
            if(accesses[i].equalsIgnoreCase(accessRequest)){
                return true;
            }
        }
        return false;
    }
}

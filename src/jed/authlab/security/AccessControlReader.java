package jed.authlab.security;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jeppe Dickow on 11/5/2015.
 */
public class AccessControlReader {
    private ArrayList<Access> accessLevels;
    private static AccessControlReader ourInstance = new AccessControlReader();

    public static AccessControlReader getInstance() {
        return ourInstance;
    }

    private AccessControlReader() {
    }

    /***
     * Read the access policy at the specified location
     * @param filePath
     * @return
     */
    public ArrayList<Access> readAccessFile(String filePath){
        ArrayList<Access> accesses = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            while(line != null){
                String name = line.substring(0, line.indexOf(":"));
                String[] splitString = line.split(":");
                String[] access = splitString[1].split(",");
                accesses.add(new Access(name, access));
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("Could not read file");
            e.printStackTrace();
        }
        return accesses;
    }

    public Access getAccessLevel(String userName){
        if(accessLevels == null){
            accessLevels = readAccessFile("AccessControls.txt");
        }
        for(Access ac : accessLevels){
            if(ac.getUserName().equalsIgnoreCase(userName)){
                return ac;
            }
        }
        return null;
    }

    public boolean hasAccess(Access accessLevel, String accessRequired){
        for(String access : accessLevel.getAccessLevels()){
            if(access.equalsIgnoreCase(accessRequired)){
                return true;
            }
        }
        return false;
    }
}

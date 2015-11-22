package NF.authlab.printer;

import NF.authlab.DBandAcces.UserInfo;
import NF.authlab.security.Access;
import NF.authlab.security.AccessControlReader;
import NF.authlab.security.RoleAccessControlReader;
import NF.authlab.security.UserInfoManager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;


public class Printer implements Runnable, IPrintCompute{

    private boolean access = false;
    private UserInfo loggedInUser;
    @Override
    public void run() {
        // register the Printer in the RMI Register
        String name = "Printer";
        IPrintCompute printer = new Printer();
        try {
            IPrintCompute stub = (IPrintCompute) UnicastRemoteObject.exportObject(printer, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind(name, stub);

        }catch(Exception e){
            System.out.println("Could not bind the printer to the register");
            e.printStackTrace();
        }
    }

    @Override
    public void print(String filename, String printer) throws RemoteException {
        String accessRequired = "print";
        if(access && RoleAccessControlReader.getIstance().hasAccess(loggedInUser.getRole(), accessRequired)) {
            System.out.println(String.format("filename = %s printer = %s", filename, printer));
            access = false;
            return;
        }
    }

    @Override
    public String queue() throws RemoteException {
        String accessRequired = "queue";
        if(access && RoleAccessControlReader.getIstance().hasAccess(loggedInUser.getRole(), accessRequired)){
            System.out.println("Queue printed");
            access = false;
            return "queue printed";
        }
        return "Not authorized to print queue.";
    }

    @Override
    public void topQueue(int job) throws RemoteException {
        String accessRequired = "topQueue";
        if(access && RoleAccessControlReader.getIstance().hasAccess(loggedInUser.getRole(), accessRequired)){
            System.out.println("Job number: " + job + "moved to top of the queue");
            access = false;
            return;
        }

    }

    @Override
    public boolean start() throws RemoteException {
        String accessRequired = "start";
        if(access && RoleAccessControlReader.getIstance().hasAccess(loggedInUser.getRole(), accessRequired)){
            System.out.println("Starting printer");
            access = false;
            return true;
        }
        System.out.println("Not authorized to start");
        return false;
    }

    @Override
    public boolean stop() throws RemoteException {
        String accessRequired = "stop";
        if(access && RoleAccessControlReader.getIstance().hasAccess(loggedInUser.getRole(), accessRequired)){
            System.out.println("Stopping printer");
            access = false;
            return true;
        }
        System.out.println("Not authorized to stop");
        return false;
    }

    @Override
    public boolean restart() throws RemoteException {
        String accessRequired = "restart";
        if(access && RoleAccessControlReader.getIstance().hasAccess(loggedInUser.getRole(), accessRequired)){
            System.out.println("Restarting printer");
            access = false;
            return true;
        }
        System.out.println("Not authorized to restart");
        return false;
    }

    @Override
    public String status() throws RemoteException {
        String accessRequired = "status";
        if(access && RoleAccessControlReader.getIstance().hasAccess(loggedInUser.getRole(), accessRequired)){
            System.out.println("status on the printer: ");
            access = false;
            return "status";
        }
        return "Not authorized to get status";
    }

    @Override
    public String readConfig(String parameter) throws RemoteException {
        String accessRequired = "readConfig";
        if(access && RoleAccessControlReader.getIstance().hasAccess(loggedInUser.getRole(), accessRequired)){
            System.out.println(String.format("ReadCongi called with the parameter %s", parameter));
            access = false;
            return "readConfig";
        }
        System.out.println("readConfig not authorized");
        return "readConfig not authorized";
    }

    @Override
    public void setConfig(String parameter, String value) throws RemoteException {
        String accessRequired = "setConfig";
        if(access && RoleAccessControlReader.getIstance().hasAccess(loggedInUser.getRole(), accessRequired)){
            System.out.println("setConfig called with parameters: " + parameter + " and " + value);
            access = false;
        }
        System.out.println("setConfig not authorized");
    }

    public boolean authenticate(String hashValue, String userName){
        try{
            loggedInUser = UserInfoManager.getInstance().authenticate(hashValue, userName);
            System.out.println(loggedInUser.toString());
            access = loggedInUser != null;
            return access;
        }catch(SQLException e){
            loggedInUser = null;
            e.printStackTrace();
        }
        //Just in case!
        loggedInUser = null;
        return false;
    }
}

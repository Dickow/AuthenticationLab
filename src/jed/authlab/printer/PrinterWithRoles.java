package jed.authlab.printer;

import jed.authlab.DataAccess.Credential;
import jed.authlab.security.Access;
import jed.authlab.security.AccessControlReader;
import jed.authlab.security.CredentialsManager;
import jed.authlab.security.RoleBasedAccessReader;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

/**
 * Created by Jeppe Dickow
 */
public class PrinterWithRoles implements Runnable, IPrintCompute{
    private boolean authenticated = false;
    private Credential loggedInUser;
    @Override
    public void run() {
        // register the Printer in the RMI Register
        String name = "PrinterWithRoles";
        IPrintCompute printer = new PrinterWithRoles();
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
        if (authenticated && RoleBasedAccessReader.getInstance().hasAccess(loggedInUser.getRole(), accessRequired)) {
            System.out.println(String.format("filename = %s printer = %s", filename, printer));
            authenticated = false;
            return;
        }
        System.out.println("print invoked unauthorized");
    }

    @Override
    public String queue() throws RemoteException {
        String accessRequired = "queue";
        if (authenticated && RoleBasedAccessReader.getInstance().hasAccess(loggedInUser.getRole(), accessRequired)) {
            System.out.println("queue invoked");
            authenticated = false;
            return "queue invoked";
        }
        return "queue invoked unauthorized";
    }

    @Override
    public void topQueue(int job) throws RemoteException {
        String accessRequired = "topQueue";
        if (authenticated && RoleBasedAccessReader.getInstance().hasAccess(loggedInUser.getRole(), accessRequired)) {
            System.out.println(String.format("topQueue invoked with job :%d", job));
            authenticated = false;
            return;
        }
        System.out.println("topQueue invoked unauthorized");
    }

    @Override
    public boolean start() throws RemoteException {
        String accessRequired = "start";
        if (authenticated && RoleBasedAccessReader.getInstance().hasAccess(loggedInUser.getRole(), accessRequired)) {
            System.out.println("start invoked");
            authenticated = false;
            return true;
        }
        System.out.println("start invoked unauthorized");
        return false;
    }

    @Override
    public boolean stop() throws RemoteException {
        String accessRequired = "stop";
        if (authenticated && RoleBasedAccessReader.getInstance().hasAccess(loggedInUser.getRole(), accessRequired)) {
            System.out.println("stop invoked");
            authenticated = false;
            return true;
        }
        System.out.println("stop invoked unauthorized");
        return false;
    }

    @Override
    public boolean restart() throws RemoteException {
        String accessRequired = "restart";
        if (authenticated && RoleBasedAccessReader.getInstance().hasAccess(loggedInUser.getRole(), accessRequired)) {
            System.out.println("restart invoked");
            authenticated = false;
            return true;
        }
        System.out.println("restart invoked unauthorized");
        return false;
    }

    @Override
    public String status() throws RemoteException {
        String accessRequired = "status";
        if (authenticated && RoleBasedAccessReader.getInstance().hasAccess(loggedInUser.getRole(), accessRequired)) {
            System.out.println("status invoked");
            authenticated = false;
            return "status";
        }
        System.out.println("status invoked unauthorized");
        return "status unauthorized";
    }

    @Override
    public String readConfig(String parameter) throws RemoteException {
        String accessRequired = "readConfig";
        if (authenticated && RoleBasedAccessReader.getInstance().hasAccess(loggedInUser.getRole(), accessRequired)) {
            System.out.println(String.format("readConfig invoked with parameter: %s", parameter));
            authenticated = false;
            return "readConfig";
        }
        System.out.println("readConfig invoked unauthorized");
        return "readConfig unauthorized";
    }

    @Override
    public void setConfig(String parameter, String value) throws RemoteException {
        String accessRequired = "setConfig";
        if (authenticated && RoleBasedAccessReader.getInstance().hasAccess(loggedInUser.getRole(), accessRequired)) {
            System.out.println(String.format("setConfig invoked with parameter: %s and value: %s", parameter, value));
            authenticated = false;
        }
        System.out.println("setConfig invoked unauthorized");
    }

    @Override
    public boolean authenticate(String hashValue, String userName) throws RemoteException {
        try {
            loggedInUser = CredentialsManager.getInstance().authenticate(hashValue, userName);
            System.out.println(loggedInUser.toString());
            authenticated = loggedInUser != null;
            return authenticated;
        } catch (SQLException e) {
            loggedInUser = null;
            e.printStackTrace();
        }
        // just to be on the safe side
        loggedInUser = null;
        return false;
    }
}

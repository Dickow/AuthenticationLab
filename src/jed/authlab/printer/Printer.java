package jed.authlab.printer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Jeppe Dickow
 */
public class Printer implements Runnable, IPrintCompute{

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
        System.out.println(String.format("filename = %s printer = %s", filename, printer));
    }

    @Override
    public String queue() throws RemoteException {
        return null;
    }

    @Override
    public void topQueue(int job) throws RemoteException {

    }

    @Override
    public boolean start() throws RemoteException {
        return false;
    }

    @Override
    public boolean stop() throws RemoteException {
        return false;
    }

    @Override
    public boolean restart() throws RemoteException {
        return false;
    }

    @Override
    public String status() throws RemoteException {
        return null;
    }

    @Override
    public String readConfig(String parameter) throws RemoteException {
        return null;
    }

    @Override
    public boolean authenticate(String hashValue, String userName) throws RemoteException {
        return false;
    }

    @Override
    public void setConfig(String parameter, String value) throws RemoteException {

    }
}

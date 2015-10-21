package jed.authlab.printer;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Jeppe Dickow
 */
public interface IPrintCompute extends Remote{

    void print(String filename, String printer) throws RemoteException;

    String queue() throws RemoteException;

    void topQueue(int job) throws RemoteException;

    boolean start() throws RemoteException;

    boolean stop() throws RemoteException;

    boolean restart() throws RemoteException;

    String status() throws RemoteException;

    String readConfig(String parameter) throws RemoteException;

    boolean authenticate(String hashValue, String userName) throws RemoteException;

    void setConfig(String parameter, String value) throws RemoteException;

}

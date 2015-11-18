package jed.authlab.Test;

import jed.authlab.printer.IPrintCompute;
import jed.authlab.security.Encrypter;
import jed.authlab.security.SaltManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Jeppe Dickow.
 */
public class PrinterTestRoleAccessControl {

    // save the names of the users and their salts in tables
    private static String[] names = {"Alice", "Ida", "Cecilia", "David", "Erica", "Fred", "George", "Henry"};
    Encrypter crypto;
    IPrintCompute printer;
    Registry registry;
    boolean setupIsDone = false;
    // not safe to use the same password for all, but for the sake of testing it works fine
    private String password = "Password1234";

    @Before
    public void setUp(){
        if(setupIsDone){
            return;
        }
        String name = "PrinterWithRoles";
        try {
            registry = LocateRegistry.getRegistry(1099);
            crypto = new Encrypter();
        }catch (RemoteException e){
            System.out.println("Could not locate registry");
            return;
        }
        try {
            printer = (IPrintCompute)registry.lookup(name);
            setupIsDone = true;
        } catch (AccessException e) {
            System.out.println("Could not access the registry");
            e.printStackTrace();
        } catch (RemoteException e) {
            System.out.println("Remote exception occured");
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.out.println("Registry not bound");
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println("Crypto caused troubles");
            e.printStackTrace();
        }
    }

    @Test
    public void testPrint() throws Exception {
        for(int i = 0; i < names.length; i++){
            printer.authenticate(crypto.byteToBase64(crypto.getHash(password, SaltManager.getInstance().getSalt(i).getBytes())), names[i]);
            printer.print("testprinter.txt", "cloudPrinter");
        }

    }

    @Test
    public void testAuthenticate() throws Exception{
        for(int i = 0; i < names.length; i++){
            boolean auth = printer.authenticate(crypto.byteToBase64(crypto.getHash(password,
                    SaltManager.getInstance().getSalt(i).getBytes())), names[i]);
            Assert.assertEquals(true, auth);
        }
    }

    @Test
    public void testQueue() throws Exception {
        for(int i = 0; i < names.length; i++){
            printer.authenticate(crypto.byteToBase64(crypto.getHash(password, SaltManager.getInstance().getSalt(i).getBytes())), names[i]);
            printer.queue();
        }
    }

    @Test
    public void testTopQueue() throws Exception {
        for(int i = 0; i < names.length; i++){
            printer.authenticate(crypto.byteToBase64(crypto.getHash(password, SaltManager.getInstance().getSalt(i).getBytes())), names[i]);
            printer.topQueue(10);
        }
    }

    @Test
    public void testStart() throws Exception {
        for(int i = 0; i < names.length; i++){
            printer.authenticate(crypto.byteToBase64(crypto.getHash(password, SaltManager.getInstance().getSalt(i).getBytes())), names[i]);
            printer.start();
        }
    }

    @Test
    public void testStop() throws Exception {
        for(int i = 0; i < names.length; i++){
            printer.authenticate(crypto.byteToBase64(crypto.getHash(password, SaltManager.getInstance().getSalt(i).getBytes())), names[i]);
            printer.stop();
        }
    }

    @Test
    public void testRestart() throws Exception {
        for(int i = 0; i < names.length; i++){
            printer.authenticate(crypto.byteToBase64(crypto.getHash(password, SaltManager.getInstance().getSalt(i).getBytes())), names[i]);
            printer.restart();
        }
    }

    @Test
    public void testStatus() throws Exception {
        for(int i = 0; i < names.length; i++){
            printer.authenticate(crypto.byteToBase64(crypto.getHash(password, SaltManager.getInstance().getSalt(i).getBytes())), names[i]);
            printer.status();
        }
    }

    @Test
    public void testReadConfig() throws Exception {
        for(int i = 0; i < names.length; i++){
            printer.authenticate(crypto.byteToBase64(crypto.getHash(password, SaltManager.getInstance().getSalt(i).getBytes())), names[i]);
            printer.readConfig("config parameter");
        }
    }

    @Test
    public void testSetConfig() throws Exception {
        for(int i = 0; i < names.length; i++){
            printer.authenticate(crypto.byteToBase64(crypto.getHash(password, SaltManager.getInstance().getSalt(i).getBytes())), names[i]);
            printer.setConfig("config parameter", "test value");
        }

    }
}
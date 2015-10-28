package jed.authlab.Test;

import jed.authlab.printer.IPrintCompute;
import jed.authlab.security.CredentialsManager;
import jed.authlab.security.Encrypter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

/**
 * Created by Jeppe Dickow.
 */
public class PrinterTest {
    String name, hashedPass, salt;
    IPrintCompute printer;
    Registry registry;


    @Before
    public void setUp(){
        name = "Printer";
        try {
            registry = LocateRegistry.getRegistry(1099);
        }catch (RemoteException e){
            System.out.println("Could not locate registry");
            return;
        }
        try {
            printer = (IPrintCompute)registry.lookup(name);
            Encrypter crypto = new Encrypter();
            salt = crypto.generateSalt();
            hashedPass = crypto.byteToBase64(crypto.getHash("password1234", salt.getBytes()));
            CredentialsManager.getInstance().registerUser("Jeppe Dickow", hashedPass);
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
        printer.print("testprinter.txt", "cloudPrinter");
    }

    @Test
    public void testAuthenticate() throws Exception{
        boolean auth = printer.authenticate(hashedPass, "Jeppe Dickow");
        Assert.assertEquals(auth, true);
    }

    @Test
    public void testQueue() throws Exception {
        printer.authenticate(hashedPass, "Jeppe Dickow");
        printer.queue();
    }

    @Test
    public void testTopQueue() throws Exception {
        printer.authenticate(hashedPass, "Jeppe Dickow");
        printer.topQueue(10);
    }

    @Test
    public void testStart() throws Exception {
        printer.authenticate(hashedPass, "Jeppe Dickow");
        printer.start();
    }

    @Test
    public void testStop() throws Exception {
        printer.authenticate(hashedPass, "Jeppe Dickow");
        printer.stop();
    }

    @Test
    public void testRestart() throws Exception {
        printer.authenticate(hashedPass, "Jeppe Dickow");
        printer.restart();
    }

    @Test
    public void testStatus() throws Exception {
        printer.authenticate(hashedPass, "Jeppe Dickow");
        printer.status();
    }

    @Test
    public void testReadConfig() throws Exception {
        printer.authenticate(hashedPass, "Jeppe Dickow");
        printer.readConfig("config parameter");
    }

    @Test
    public void testSetConfig() throws Exception {
        printer.authenticate(hashedPass, "Jeppe Dickow");
        printer.setConfig("config parameter", "test value");
    }
}
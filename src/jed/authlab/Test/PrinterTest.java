package jed.authlab.Test;

import jed.authlab.printer.IPrintCompute;
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
public class PrinterTest {
    String name;
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
    }

    @Test
    public void testRun() throws Exception {

    }

    @Test
    public void testPrint() throws Exception {
        printer.print("testprinter.txt", "cloudPrinter");
    }

    @Test
    public void testAuthenticate() throws Exception{
        String hashedPass = String.format("%d","password1234".hashCode());
        boolean auth = printer.authenticate(hashedPass, "Jeppe Dickow");
        Assert.assertEquals(auth, true);
    }

    @Test
    public void testQueue() throws Exception {

    }

    @Test
    public void testTopQueue() throws Exception {

    }

    @Test
    public void testStart() throws Exception {

    }

    @Test
    public void testStop() throws Exception {

    }

    @Test
    public void testRestart() throws Exception {

    }

    @Test
    public void testStatus() throws Exception {

    }

    @Test
    public void testReadConfig() throws Exception {

    }

    @Test
    public void testSetConfig() throws Exception {

    }
}
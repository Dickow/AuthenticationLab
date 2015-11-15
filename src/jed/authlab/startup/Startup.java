package jed.authlab.startup;

import jed.authlab.printer.Printer;
import jed.authlab.printer.PrinterWithRoles;

/**
 * Created by Jeppe Dickow
 */
public class Startup {

    public static void main(String[] args) {

        // start the print server.

        //Thread printerThread = new Thread(new Printer());
        //printerThread.start();

        Thread printerThread2 = new Thread(new PrinterWithRoles());
        printerThread2.start();
    }
}

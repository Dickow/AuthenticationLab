package NF.authlab.startup;

import NF.authlab.printer.Printer;



public class Startup {

    public static void main(String[] args) {
        // start the print server.

        Thread printerThread = new Thread(new Printer());
        printerThread.start();
    }
}

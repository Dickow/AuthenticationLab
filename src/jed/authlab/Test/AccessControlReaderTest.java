package jed.authlab.Test;

import jed.authlab.security.Access;
import jed.authlab.security.AccessControlReader;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Jeppe Dickow on 11/5/2015.
 */
public class AccessControlReaderTest {

    @Test
    public void testReadAccessFile() throws Exception {
        ArrayList<Access> accesses = AccessControlReader.getInstance().readAccessFile("AccessControls.txt");

    }

    @Test
    public void testGetAccessLevel() throws Exception {

    }
}
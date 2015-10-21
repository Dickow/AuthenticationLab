package jed.authlab.Test;

import jed.authlab.security.Encrypter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by SharkGaming on 10/21/2015.
 */
public class EncrypterTest {
    Encrypter crypto;
    @Before
    public void setUp() throws Exception {
        crypto = new Encrypter();
    }

    @Test
    public void testMatches() throws Exception {
        String testString1 = "JeppeDickowTestString";
        String testString2 = "JeppeDickowTestString";
        String testString3 = "JeppeDickowFailtString";

        String hashString1 = String.format("%d", testString1.hashCode());
        String hashString2 = String.format("%d", testString2.hashCode());
        String hashString3 = String.format("%d", testString3.hashCode());

        String secString1 = crypto.encrypt(hashString1);
        String decryptedString1 = crypto.decrypt(secString1);
        Assert.assertEquals(true, hashString1.equals(decryptedString1));

        String secString2 = crypto.encrypt(hashString2);
        String decryptedString2 = crypto.decrypt(secString2);
        Assert.assertEquals(true, hashString2.equals(decryptedString2));

        String secString3 = crypto.encrypt(hashString3);
        String decryptedString3 = crypto.decrypt(secString3);
        Assert.assertEquals(true, hashString3.equals(decryptedString3));
    }
}
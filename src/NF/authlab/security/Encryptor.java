package NF.authlab.security;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class Encryptor {

    public boolean doesMatch(String string1, String string2){
        return string1.equals(string2);
    }

    public byte[] getHash(String pass, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);
        return digest.digest(pass.getBytes("UTF-8"));
    }

    public String generateSalt(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String s = new String(bytes);
        return s;
    }

    public String byteToBase64(byte[] data){
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}

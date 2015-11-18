package jed.authlab.security;

/**
 * Created by Jeppe Dickow on 11/11/2015.
 */
public class CreateSalts {
    public static void main(String[] args) {
        String[] users = {"Alice", "Ida", "Cecilia", "David", "Erica", "Fred", "George", "Henry"};
        Encrypter crypto = new Encrypter();
        for(int i = 0; i < users.length; i++){
            System.out.println(String.format("%s 's salt is %s",users[i],crypto.generateSalt()));
        }
    }
}

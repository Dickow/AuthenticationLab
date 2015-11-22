package NF.authlab.security;



public class SaltMaker {

    public static void main(String[] args){

        String[] users = {"Adam", "Benjamin", "Celine", "Dennis", "Edward", "Frederik", "George", "Herbert"};
        Encryptor cryptor = new Encryptor();
        for(int i = 0; i < users.length; i++){
            System.out.println(String.format("%s 's salt is %s", users[i], cryptor.generateSalt()));
        }
    }
}

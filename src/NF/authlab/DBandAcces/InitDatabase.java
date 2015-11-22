package NF.authlab.DBandAcces;


import NF.authlab.security.Encryptor;
import NF.authlab.security.Role;
import NF.authlab.security.SaltManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InitDatabase {

    private static Connection conn = null;
    private static String[] names = {"Adam", "Benjamin", "Celine", "Dennis", "Edward", "Frederik", "George", "Herbert"};
    private static Role[] roles = {Role.MANAGER, Role.SERVICETECHNICIAN, Role.POWERUSER, Role.ORDINARYUSER,
            Role.ORDINARYUSER, Role.ORDINARYUSER, Role.ORDINARYUSER, Role.ORDINARYUSER};


    public static void main(String[] args){
        initAuthDatabase();
    }

    private static void initAuthDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:authenticationlab.sqlite");
            Statement stmt = conn.createStatement();
            // String sql ="DROP TABLE USERINFO;";
            // stmt.execute(sql);


            String sql = "CREATE TABLE USERINFO" +
                    "(ID        INT          PRIMARY KEY," +
                    "NAME       TEXT         NOT NULL," +
                    "PASSWORD   TEXT         NOT NULL," +
                    "ROLE       TEXT         NOT NULL);";
            stmt.executeUpdate(sql);
            Encryptor cryptor = new Encryptor();

            for(int i = 0; i < names.length; i++){
                sql = String.format("INSERT INTO USERINFO (NAME, PASSWORD, ROLE) VALUES('%s', '%s', '%s');",
                        names[i], cryptor.byteToBase64(
                                cryptor.getHash("Pass1234", SaltManager.getInstance().getSalt(i).getBytes())),
                                roles[i].name());
                stmt.execute(sql);
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened connection to database succesfully");
    }
}

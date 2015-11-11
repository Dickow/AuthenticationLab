package jed.authlab.DataAccess;

import jed.authlab.security.Encrypter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by Jeppe Dickow on 10/26/2015.
 * This class is intended to setup the database, this should be run before anything else.
 * It also populates the database with one entry
 */
public class InitDB {
    private static Connection conn = null;
    private static String[] names = {"Alice","Bob","Cecilia","David","Erica","Fred","George"};
    private static String[] salts = {"gà?\u0001’œ‹UR`.Y&ÕY;û\u0014\"Ò","G`Í°†Jfý‘¹‹+]!Çˆö\\K-",
    "ï„\u0012ôI\u0001O\u0006ËÌ˜^/\u0010¨yRø®ˆ","úÉˆæ›ûn¿†\u000B?\u00AD@i×–…3Òƒ",
    "n€ÿx?%c\u0010\u000EMÕ\u0010÷©³‡§=¬.","â!zuAvÑ‡?Àâ·mo)¨^)Á>","¯|Ü„ë\u0015t[w\u0011•¸P,,\u001Cÿ¨x"};
    public static void main(String[] args) {
        initAuthDb();
    }


    private static void initAuthDb(){
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:authenticationlab.sqlite");
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE CREDENTIALS " +
                    "(ID         INT                PRIMARY KEY," +
                    "NAME       TEXT                NOT NULL," +
                    "PASSWORD   TEXT                NOT NULL);";
            stmt.executeUpdate(sql);
            Encrypter crypto = new Encrypter();
            for(int i = 0; i < names.length; i++) {
                sql = String.format("INSERT INTO CREDENTIALS (NAME, PASSWORD) VALUES ('%s', '%s');",
                        names[i], crypto.byteToBase64(crypto.getHash("Password1234",salts[i].getBytes())));
                stmt.execute(sql);
            }
            stmt.close();
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened connection to database successfully");
    }
}

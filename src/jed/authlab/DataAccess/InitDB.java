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
    private static String[] salts = {"g�?\u0001���UR`.Y&�Y;�\u0014\"�","G`Ͱ�Jf����+]!ǈ�\\K-",
    "�\u0012�I\u0001O\u0006�̘^/\u0010�yR���","�Ɉ��n��\u000B?\u00AD@iז�3҃",
    "n��x?%c\u0010\u000EM�\u0010�����=�.","�!zuAvч?��mo)�^)�>","�|܄�\u0015t[w\u0011��P,,\u001C��x"};
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

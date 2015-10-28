package jed.authlab.DataAccess;

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
            stmt.close();
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened connection to database successfully");
    }
}

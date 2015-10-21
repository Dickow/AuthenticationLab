package jed.authlab.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 * Created by Jeppe_000 on 21-10-2015.
 */
public class Database {
    private Connection conn = null;
    public Database(){
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:authenticationlab.db");
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE CREDENTIALS " +
                    "ID         INT PRIMARY KEY     NOT NULL," +
                    "NAME       TEXT                NOT NULL," +
                    "PASSWORD   TEXT                NOT NULL";
            stmt.executeUpdate(sql);

            // TODO make sure the password is hashed then put it into the database

            sql = String.format("INSERT INTO CREDENTIALS (ID, NAME, PASSWORD) " +
                    "VALUES (1, Jeppe Dickow, %s)","password1234");
            stmt.close();
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened connection to database successfully");
    }
}

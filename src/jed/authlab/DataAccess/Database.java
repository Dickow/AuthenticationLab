package jed.authlab.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * Created by Jeppe_000 on 21-10-2015.
 */
public class Database {
    private Connection conn = null;
    public Database(){
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:authenticationlab.db");
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened connection to database successfully");
    }

    public Connection getConn(){
        return this.conn;
    }

}

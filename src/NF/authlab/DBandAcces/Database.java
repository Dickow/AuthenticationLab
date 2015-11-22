package NF.authlab.DBandAcces;


import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    private Connection conn = null;

    public Database(){
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:autehtnicationlab.sqlite");

        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened connection to database succesfully");
    }

    public Connection getConn(){
        return this.conn;
    }
}

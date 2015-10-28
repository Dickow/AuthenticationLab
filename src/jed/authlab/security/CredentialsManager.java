package jed.authlab.security;

import jed.authlab.DataAccess.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Jeppe Dickow on 10/21/2015.
 */
public class CredentialsManager {
    private static CredentialsManager ourInstance = new CredentialsManager();

    private Database db;

    public static CredentialsManager getInstance() {
        return ourInstance;
    }

    private CredentialsManager() {
        db = new Database();
    }


    /**
     * try to authenticate the user against the db
     * @param securePass
     * @return
     * @throws SQLException
     */
    public boolean authenticate(String securePass) throws SQLException {

        // TODO not safe against SQL injection but it is intended to be
        Connection conn = db.getConn();
        Statement stmt = conn.createStatement();
        String sql = String.format("SELECT * FROM CREDENTIALS WHERE PASSWORD = '%s';", securePass);
        ResultSet result = stmt.executeQuery(sql);
        boolean exists = result.isBeforeFirst();
        System.out.println(exists);
        return exists;
    }

    /***
     * This is used to populate some data into the database, preferably this would be done with some registration method.
     * @param userName
     * @param securePass
     * @throws SQLException
     */
    public void registerUser(String userName, String securePass) throws SQLException {
        // TODO not safe against SQL injection but it is intended to be
        Connection conn = db.getConn();
        Statement stmt = conn.createStatement();
        String sql = String.format("INSERT INTO CREDENTIALS (NAME, PASSWORD) " +
                "VALUES('%s', '%s');", userName, securePass);
        stmt.execute(sql);
        stmt.close();
    }

}

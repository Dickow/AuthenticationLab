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

    public boolean authenticate(String securePass) throws SQLException {

        // TODO not safe against SQL injection
        Connection conn = db.getConn();
        Statement stmt = conn.createStatement();
        String sql = String.format("SELECT * FROM CREDENTIALS WHERE PASSWORD = '%s'", securePass);
        ResultSet result = stmt.executeQuery(sql);
        boolean exists = result.isBeforeFirst();
        System.out.println(exists);
        return exists;
    }

}

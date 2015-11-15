package jed.authlab.security;

import jed.authlab.DataAccess.Credential;
import jed.authlab.DataAccess.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Jeppe Dickow on 10/21/2015.
 */
public class CredentialsManager {
    private static CredentialsManager ourInstance = new CredentialsManager();

    private Database db;

    private CredentialsManager() {
        db = new Database();
    }

    public static CredentialsManager getInstance() {
        return ourInstance;
    }

    /**
     * try to authenticate the user against the db
     * @param securePass
     * @return
     * @throws SQLException
     */
    public Credential authenticate(String securePass, String userName) throws SQLException {

        // TODO not safe against SQL injection but it is intended to be
        Connection conn = db.getConn();
        Statement stmt = conn.createStatement();
        String sql = String.format("SELECT * FROM CREDENTIALS WHERE PASSWORD = '%s' AND NAME = '%s';", securePass, userName);
        ResultSet result = stmt.executeQuery(sql);
        ArrayList<Credential> list = new ArrayList<>();
        while(result.next()){
            Credential cred = new Credential();
            cred.setName(result.getString("NAME"));
            cred.setPassword(result.getString("PASSWORD"));
            cred.setRole(Role.valueOf(result.getString("ROLE")));
            list.add(cred);
        }
        /*
        System.out.println(list.size() > 0);
        for(Credential c : list){
            System.out.println(String.format("name: %s  password: %s",c.getName(), c.getPassword()));
        }
        */
        // just returning the first value found otherwise null of the user did not exist
        return list.size() > 0 ? list.get(0) : null;
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

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
    public boolean authenticate(String securePass, String userName) throws SQLException {

        // TODO not safe against SQL injection but it is intended to be
        Connection conn = db.getConn();
        Statement stmt = conn.createStatement();
        String sql = String.format("SELECT * FROM CREDENTIALS WHERE PASSWORD = '%s' AND NAME = '%s';", securePass, userName);
        ResultSet result = stmt.executeQuery(sql);
        System.out.println(result.toString());
        ArrayList<Credential> list = new ArrayList<>();
        while(result.next()){
            Credential cred = new Credential();
            cred.setName(result.getString("NAME"));
            cred.setPassword(result.getString("PASSWORD"));
            list.add(cred);
        }

        System.out.println(list.size() > 0);
        for(Credential c : list){
            System.out.println(String.format("name: %s  password: %s",c.getName(), c.getPassword()));
        }
        return list.size() > 0;
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

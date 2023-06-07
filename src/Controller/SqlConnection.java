package Controller;

import java.sql.*;

/**
 * deal with connection of the system with database
 */
public class SqlConnection implements DatabaseConnection {
    private String serverName;
    private String dbName;
    private String url;
    private String name;
    private String pass;


    /**
     * this constructor set connection information
     */

    public SqlConnection() {
        this.serverName="DESKTOP-NBI9F7Q";
        this.dbName="Management";
        this.url = "jdbc:sqlserver://" +serverName + ":1433;DatabaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
        this.name="eyad";
        this.pass="1234";
    }

    /**
     * this method get copy of connection
     * @return copy of connection
     */
    public Connection getConnection() {
        Connection connection;
        try
        {
            connection= DriverManager.getConnection(this.url,this.name,this.pass);
            return connection;
        }
        catch(Exception e)
        {
            System.out.println("error in connection"+e);
            return null;
        }
    }
}

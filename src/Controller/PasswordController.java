package Controller;

import Model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PasswordController {

    SqlConnection connect;
    public PasswordController() {
        connect=new SqlConnection();
    }
    public String search(int id) throws SQLException {
        Connection c=connect.getConnection();
        String sql="select Pasword from Member where email="+id;
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        int count=0;
        String pass = "";
        while(result.next())
        {
            pass = result.getString("password");

        }
        c.close();
        return pass;
    }
}

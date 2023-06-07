package Controller;

import Model.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * deal with user table
 */


public class UserController {
    /**
     * @param connect this parameter connect database
     */
    SqlConnection connect;


    /**
     * this constructor connect to database
     */
    public UserController() {

        connect=new SqlConnection();
    }

    /**
     * this method search about specific user using id and password to login
     * @param user
     * @return boolean  variable expresses search state
     * @throws SQLException
     */
    public boolean search(User user) throws SQLException {
        Connection c=connect.getConnection();
        String sql="select * from Member where email='"+user.getEmail()+"' and password='"+user.getPassword()+"'";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        int count=0;
        while(result.next())
        {
            user.setId(result.getInt("id"));
            user.setRole(result.getString("role"));
            count++;
        }
        c.close();
        return count!=0;
    }

    /**
     * this method get all employee emails
     * @return ArrayList of employee emails
     * @throws SQLException
     */

    public ArrayList<String> searchEmployeeEmails() throws SQLException {
        Connection c=connect.getConnection();
        String sql="select email from Member where role='employee'";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>emails=new ArrayList<>();
        while(result.next())
        {
            emails.add(result.getString("email"));
        }
        c.close();
        return emails;
    }

    /**
     * this methood get id of specific user
     * @param email
     * @return id of specific user
     * @throws SQLException
     */
    public int getEmployeeId(String email) throws SQLException
    {
        Connection c=connect.getConnection();
        String sql="select id from Member where email='"+email+"'";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        int id=0;
        while(result.next())
        {
            id=result.getInt("id");
        }
        c.close();
        return id;
    }

    /**
     * this method search about specific user
     * @param userId
     * @return specific user and its email , password and role
     * @throws SQLException
     */
    public User search ( int userId )throws SQLException{
        Connection c=connect.getConnection();
        User searchingUser = new User();
        String sql="select * from Member where id ="+userId;
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        int count=0;
        String pass = "";
        while(result.next())
        {
            searchingUser.setEmail(result.getString("email"));
            searchingUser.setPassword(result.getString("password"));
            searchingUser.setRole(result.getString("role"));


        }
        c.close();
        return searchingUser;

    }

    /**
     * this method add new user
     * @param user
     * @return boolean  variable expresses update state
     * @throws SQLException
     */
    public boolean add(User user) throws SQLException {
        Connection c=connect.getConnection();
        String sql="insert into Member (email,password,role) values(?,?,?)";
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getRole());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }

    /**
     * this method return all user table
     * @return Array list of all user and its emails and roles
     * @throws SQLException
     */
    public ArrayList<String> all() throws SQLException {
        Connection c=connect.getConnection();
        String sql="select * from Member";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>allUsers=new ArrayList<>();
        while(result.next())
        {
            allUsers.add(result.getInt("id")+"");
            allUsers.add(result.getString("email"));
            allUsers.add(result.getString("role"));

        }
        c.close();
        return allUsers;
    }

    /**
     * this method delete information of specific user
     * @param id
     * @return boolean  variable expresses delete state
     * @throws SQLException
     */
    public boolean delete(int id) throws SQLException {
        Connection c=connect.getConnection();
        String sql="delete from Member where id="+id;
        Statement statement=c.createStatement();
        int rows=statement.executeUpdate(sql);
        c.close();
        return rows!=0;
    }

    /**
     * this method update information of specific user
     * @param user
     * @return boolean  variable expresses update state
     * @throws SQLException
     */
    public boolean update(User user) throws SQLException {
        Connection c=connect.getConnection();
        String sql="update Member set email=?,password=?,role=?"+" where id="+user.getId();
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setString(1,user.getEmail());
        statement.setString(2,user.getPassword());
        statement.setString(3,user.getRole());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }
}

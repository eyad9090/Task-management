package Controller;


import Model.Penalty;

import java.sql.*;
import java.util.ArrayList;
/**
 * deal with employee penalty table
 */
public class PenaltyController {
    /**
     * @param connect this parameter connect database
     */
    SqlConnection connect;

    /**
     * this constructor connect to database
     */
    public PenaltyController() {
        connect=new SqlConnection();
    }

    /**
     * this method get all specific employee penalties
     * @param userId
     * @return Array list of all specific employee penalties and its details
     * @throws SQLException
     */
    public ArrayList<String> search(int userId) throws SQLException {
        Connection c=connect.getConnection();
        String sql="select * from User_Penalty where memberId="+userId;
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>penalty=new ArrayList<>();
        while(result.next())
        {
            penalty.add(result.getInt("memberId")+"");
            penalty.add(result.getString("details"));
        }
        c.close();
        return penalty;
    }

    /**
     * this method add new penalty to employee
     * @param penalty
     * @return boolean  variable expresses add state
     * @throws SQLException
     */

    public boolean add(Penalty penalty) throws SQLException {
        Connection c=connect.getConnection();
        String sql="insert into User_Penalty (memberId,details) values(?,?)";
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setInt(1, penalty.getUserId());
        statement.setString(2, penalty.getDetails());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }

    /**
     * this method get all employee penalties
     * @return Array list of all employee penalties
     * @throws SQLException
     */
    public ArrayList<String> search() throws SQLException {
        Connection c=connect.getConnection();
        String sql= """
                select p.id as id, m.email as email,p.details as details
                from User_Penalty as P inner join Member as m
                on p.memberId=m.id""";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>penalty=new ArrayList<>();
        while(result.next())
        {
            penalty.add(result.getInt("id")+"");
            penalty.add(result.getString("email"));
            penalty.add(result.getString("details"));
        }
        c.close();
        return penalty;
    }

    /**
     * this method update details of specific penallty
     * @param penalty
     * @return boolean  variable expresses update state
     * @throws SQLException
     */
    public boolean update(Penalty penalty) throws SQLException {
        Connection c=connect.getConnection();
        String sql="update User_Penalty set details=?"+" where id="+penalty.getId();
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setString(1,penalty.getDetails());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }

    /**
     * this mwthod delete specific penalty
     * @param id
     * @return boolean  variable expresses delete state
     * @throws SQLException
     */
    public boolean delete(int id) throws SQLException {
        Connection c=connect.getConnection();
        String sql="delete from User_Penalty where id="+id;
        Statement statement=c.createStatement();
        int rows=statement.executeUpdate(sql);
        c.close();
        return rows!=0;
    }
}

package Controller;

import Model.Penalty;
import Model.Report;

import java.sql.*;
import java.util.ArrayList;
/**
 * deal with employee penalty table
 */
public class ReportController {

    /**
     * @param connect this parameter connect database
     */

    SqlConnection connect;

    /**
     * this constructor connect to database
     */
    public ReportController() {
        connect=new SqlConnection();
    }

    /**
     * this method get all report of employees
     * @return Array list of all report of employees and its details and employee email
     * @throws SQLException
     */
    public ArrayList<String> search() throws SQLException {
        Connection c=connect.getConnection();
        String sql="select er.id as id, m.email as email ,er.report as report\n" +
                "from  Employee_report as er inner join Member as m\n" +
                "on er.memberId=m.id";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>reports=new ArrayList<>();
        while(result.next())
        {
            reports.add(result.getInt("id")+"");
            reports.add(result.getString("email"));
            reports.add(result.getString("report"));
        }
        c.close();
        return reports;
    }

    /**
     * this method add new report
     * @param report
     * @return  boolean  variable expresses add state
     * @throws SQLException
     */
    public boolean add(Report report) throws SQLException {
        Connection c=connect.getConnection();
        String sql="insert into Employee_report (memberId,report) values(?,?)";
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setInt(1, report.getUserId());
        statement.setString(2, report.getReport());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }

    /**
     * this method update specific report
     * @param report
     * @return  boolean  variable expresses update state
     * @throws SQLException
     */

    public boolean update(Report report) throws SQLException {
        Connection c=connect.getConnection();
        String sql="update Employee_report set report=?"+" where id="+report.getId();
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setString(1,report.getReport());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }

    /**
     * this method delete specific report
     * @param id
     * @return  boolean  variable expresses delete state
     * @throws SQLException
     */
    public boolean delete(int id) throws SQLException {
        Connection c=connect.getConnection();
        String sql="delete from Employee_report where id="+id;
        Statement statement=c.createStatement();
        int rows=statement.executeUpdate(sql);
        c.close();
        return rows!=0;
    }
}

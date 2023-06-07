package Controller;

import Model.EmployeeWorking;
import Model.User;

import java.sql.*;
import java.util.ArrayList;
/**
 * deal with employee working table
 */
public class EmployeeWorkingController {
    /**
     * @param connect this parameter connect database
     */
    SqlConnection connect;

    /**
     * this constructor connect to database
     */
    public EmployeeWorkingController() {
        connect=new SqlConnection();
    }

    /**
     * this method add start and finish  time for specific employee
     * @param employeeWorking
     * @return boolean  variable expresses add state
     * @throws SQLException
     */
    public boolean add(EmployeeWorking employeeWorking) throws SQLException {
        Connection c=connect.getConnection();
        String sql="insert into Employee_Wroking (memberId,start_time,finish_time) values (?,?,?)";
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setInt(1, employeeWorking.getUserId());
        statement.setString(2, employeeWorking.getStartTime());
        statement.setString(3, employeeWorking.getFinishTime());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }

    /**
     * this method get employee start and finish time in all days
     * @param userId
     * @return Array List of members and its date , start time and finish time
     * @throws SQLException
     */
    public ArrayList<String> search(int userId) throws SQLException {
        Connection c=connect.getConnection();
        String sql="select * from Employee_Wroking where memberId="+userId;
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>employeeWorking=new ArrayList<>();
        while(result.next())
        {
            employeeWorking.add(result.getInt("memberId")+"");
            employeeWorking.add(result.getString("today_date"));
            employeeWorking.add(result.getString("start_time"));
            employeeWorking.add(result.getString("finish_time"));
        }
        c.close();
        return employeeWorking;
    }


    /**
     * this method calculate number of hour working of specific employee
     * @param employeeWorking
     * @param email
     * @return number of hours  working of specific employee
     * @throws SQLException
     */
    public int calculateHours(EmployeeWorking employeeWorking,String email) throws SQLException {
        Connection c=connect.getConnection();
        String sql="SELECT sum(DATEDIFF(HOUR, ew.start_time , ew.finish_time)) AS hourDiff\n" +
                "from Employee_Wroking as ew inner join Member as m\n" +
                "on ew.memberId=m.id\n" +
                "where m.email='"+email+"' and year(ew.today_date)='"+employeeWorking.getYear()+"' and month(ew.today_date)='"+employeeWorking.getMonth()+"'\n";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        int hours=0;
        while(result.next())
        {
            hours=result.getInt("hourDiff");
        }
        c.close();
        return hours;
    }

}

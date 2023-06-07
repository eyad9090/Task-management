
package Controller;

import Model.EmployeeVacation;
import Model.EmployeeWorking;

import java.sql.*;
import java.util.ArrayList;
/**
 * deal with employee vacation table
 */
public class EmployeeVacationController {
    /**
     * @param connect this parameter connect database
     */
    SqlConnection connect;

    /**
     * this constructor connect to database
     */
    public EmployeeVacationController() {
        connect=new SqlConnection();
    }

    /**
     * this method get all vacation request of specific employee
     * @param userId
     * @return Array list of vacations requests and its start time , finish time , reason and state of request
     * @throws SQLException
     */

    public ArrayList<String> search(int userId) throws SQLException {
        Connection c=connect.getConnection();
        String sql="select * from Employee_Vacation where memberId="+userId;
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>employeeVacation=new ArrayList<>();
        while(result.next())
        {
            employeeVacation.add(result.getInt("id")+"");
            employeeVacation.add(result.getString("start_date"));
            employeeVacation.add(result.getString("finish_date"));
            employeeVacation.add(result.getString("reason"));
            employeeVacation.add(result.getString("vacation_state"));
        }
        c.close();
        return employeeVacation;
    }

    /**
     * this method add new vacation request
     * @param employeeVacation
     * @return boolean  variable expresses add state
     * @throws SQLException
     */
    public boolean add(EmployeeVacation employeeVacation) throws SQLException {
        Connection c=connect.getConnection();
        String sql="insert into Employee_Vacation (memberId,start_date,finish_date,reason) values(?,?,?,?)";
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setInt(1, employeeVacation.getUserId());
        statement.setString(2, employeeVacation.getStartDate());
        statement.setString(3, employeeVacation.getFinishDate());
        statement.setString(4, employeeVacation.getReason());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }

    /**
     * this method update details of vacation
     * @param employeeVacation
     * @return  boolean  variable expresses update state
     * @throws SQLException
     */
    public boolean update(EmployeeVacation employeeVacation) throws SQLException {
        Connection c=connect.getConnection();
        String sql="update Employee_vacation set start_date=?,finish_date=?,reason=?,vacation_state=?"+" where id="+employeeVacation.getId();
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setString(1,employeeVacation.getStartDate());
        statement.setString(2,employeeVacation.getFinishDate());
        statement.setString(3,employeeVacation.getReason());
        statement.setString(4,employeeVacation.getState());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }

    /**
     * this method delete vacation request
     * @param id
     * @return boolean  variable expresses delete state
     * @throws SQLException
     */
    public boolean delete(int id) throws SQLException {
        Connection c=connect.getConnection();
        String sql="delete from Employee_vacation where id="+id;
        Statement statement=c.createStatement();
        int rows=statement.executeUpdate(sql);
        c.close();
        return rows!=0;
    }

    /**
     * this method get vacation request of specific state ( accept , reject , wait )
     * @param state
     * @return vaction requests of specific state and its start date , finish date and reason
     * @throws SQLException
     */

    public ArrayList<String> search(String state) throws SQLException {
        Connection c=connect.getConnection();
        String sql="select * from Employee_Vacation where vacation_state='"+state+"'";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>employeeVacation=new ArrayList<>();
        while(result.next())
        {
            employeeVacation.add(result.getInt("id")+"");
            employeeVacation.add(result.getString("start_date"));
            employeeVacation.add(result.getString("finish_date"));
            employeeVacation.add(result.getString("reason"));
            employeeVacation.add(result.getString("vacation_state"));
        }
        c.close();
        return employeeVacation;
    }

    /**
     * this method get vacation request of specific employee
     * @return Array list of vacations requests and its start time , finish time , reason and state of request
     * @throws SQLException
     */
    public ArrayList<String> searchLeader() throws SQLException {
        Connection c=connect.getConnection();
        String sql="select ev.id as id, m.email as email,ev.start_date as start_date,\n" +
                "ev.finish_date as finish_date,ev.reason as reason,\n" +
                "ev.vacation_state as vacation_state\n" +
                "from Employee_Vacation as ev inner join Member as m\n" +
                "on ev.memberId=m.id";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>employeeVacation=new ArrayList<>();
        while(result.next())
        {
            employeeVacation.add(result.getInt("id")+"");
            employeeVacation.add(result.getString("email"));
            employeeVacation.add(result.getString("start_date"));
            employeeVacation.add(result.getString("finish_date"));
            employeeVacation.add(result.getString("reason"));
            employeeVacation.add(result.getString("vacation_state"));
        }
        c.close();
        return employeeVacation;
    }

    /**
     * this method get specific employee vacation request of specific state
     * @return Array list of vacations requests and its start time , finish time , reason and state of request
     * @throws SQLException
     */

    public ArrayList<String> searchLeader(String state) throws SQLException {
        Connection c=connect.getConnection();
        String sql="select ev.id as id, m.email as email,ev.start_date as start_date,\n" +
                "ev.finish_date as finish_date,ev.reason as reason,\n" +
                "ev.vacation_state as vacation_state\n" +
                "from Employee_Vacation as ev inner join Member as m\n" +
                "on ev.memberId=m.id\n" +
                "where ev.vacation_state='"+state+"'";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>employeeVacation=new ArrayList<>();
        while(result.next())
        {
            employeeVacation.add(result.getInt("id")+"");
            employeeVacation.add(result.getString("email"));
            employeeVacation.add(result.getString("start_date"));
            employeeVacation.add(result.getString("finish_date"));
            employeeVacation.add(result.getString("reason"));
            employeeVacation.add(result.getString("vacation_state"));
        }
        c.close();
        return employeeVacation;
    }

}


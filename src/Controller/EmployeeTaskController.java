package Controller;

import Model.EmployeeTask;

import java.sql.*;
import java.util.ArrayList;

/**
 * deal with employee task table
 */
public class EmployeeTaskController {
    /**
     * @param connect this parameter connect database
     */
    SqlConnection connect;

    /**
     * this constructor connect to database
     */
    public EmployeeTaskController() {
        connect=new SqlConnection();
    }

    /**
     * this method return result of employee tasks
     * @param userId specific user id
     * @return ArrayList of all employee tasks consist of its name , details , percentage and state
     * @throws SQLException
     */
    public ArrayList<String> search(int userId) throws SQLException {
        Connection c=connect.getConnection();
        String sql="select et.id as employeeTaskId,t.projectId as projectId,\n" +
                "t.taskPercentage as taskPercentage,\n" +
                "t.taskName as taskName,t.taskDetails as taskDetails,\n" +
                "et.task_state as taskState \n" +
                "from Task as t inner join Employee_Tasks as et\n" +
                "on t.id=et.taskId\n" +
                "where et.memberId="+userId;
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>employeeTask=new ArrayList<>();
        while(result.next())
        {
            employeeTask.add(result.getInt("employeeTaskId")+"");
            employeeTask.add(result.getInt("projectId")+"");
            employeeTask.add(result.getInt("taskPercentage")+"");
            employeeTask.add(result.getString("taskName"));
            employeeTask.add(result.getString("taskDetails"));
            employeeTask.add(result.getString("taskState"));
        }
        c.close();
        return employeeTask;
    }

    /**
     *  this method return result of employee tasks of specific state
     * @param state state of task ( finished , not finished)
     * @return  ArrayList of all employee tasks consist of its name , details , percentage and state
     * @throws SQLException
     */
    public ArrayList<String> search(String state) throws SQLException {
        Connection c=connect.getConnection();
        String sql="select et.id as employeeTaskId,t.projectId as projectId,\n" +
                "t.taskPercentage as taskPercentage,\n" +
                "t.taskName as taskName,t.taskDetails as taskDetails,\n" +
                "et.task_state as taskState \n" +
                "from Task as t inner join Employee_Tasks as et\n" +
                "on t.id=et.taskId\n" +
                "where et.task_state='"+state+"'";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>employeeTask=new ArrayList<>();
        while(result.next())
        {
            employeeTask.add(result.getInt("employeeTaskId")+"");
            employeeTask.add(result.getInt("projectId")+"");
            employeeTask.add(result.getInt("taskPercentage")+"");
            employeeTask.add(result.getString("taskName"));
            employeeTask.add(result.getString("taskDetails"));
            employeeTask.add(result.getString("taskState"));
        }
        c.close();
        return employeeTask;
    }

    /**
     * this method update tasks state
     * @param employeeTask details of task
     * @return boolean  variable expresses update state
     * @throws SQLException
     */
    public boolean update(EmployeeTask employeeTask) throws SQLException {
        Connection c=connect.getConnection();
        String sql="update Employee_Tasks set task_state = ?"+" where id="+employeeTask.getId();
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setString(1,employeeTask.getTaskState());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }

    /**
     * this method assign task to specific employee
     * @param employeeTask
     * @return boolean  variable expresses add state
     * @throws SQLException
     */
    public boolean add(EmployeeTask employeeTask) throws SQLException {
        Connection c=connect.getConnection();
        String sql="insert into Employee_Tasks(memberId,taskId) values(?,?)";
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setInt(1, employeeTask.getUserId());
        statement.setInt(2, employeeTask.getTaskId());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }

    /**
     * this method change task assigning
     * @param employeeTask
     * @return boolean  variable expresses update state
     * @throws SQLException
     */
    public boolean updateTask(EmployeeTask employeeTask) throws SQLException {
        Connection c=connect.getConnection();
        String sql="update Employee_Tasks set taskId=?"+" where id="+employeeTask.getId();
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setInt(1,employeeTask.getTaskId());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }

    /**
     * this method delete task
     * @param id
     * @return boolean  variable expresses delete state
     * @throws SQLException
     */
    public boolean delete(int id) throws SQLException {
        Connection c=connect.getConnection();
        String sql="delete from Employee_Tasks where id="+id;
        Statement statement=c.createStatement();
        int rows=statement.executeUpdate(sql);
        c.close();
        return rows!=0;
    }
}

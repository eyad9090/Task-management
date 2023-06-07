package Controller;

import Model.EmployeeTask;
import Model.EmployeeVacation;
import Model.Task;

import java.sql.*;
import java.util.ArrayList;

/**
 * this class deal with Tasks of Employee
 */
public class TaskController {
    SqlConnection connect;
    public TaskController() {
        connect=new SqlConnection();
    }

    /**
     * This method return all tasks with email of the Employee this task assigned to and the project name that has this task
     * @return ArrayList of String
     * @throws SQLException
     */
    public ArrayList<String> search() throws SQLException {
        Connection c=connect.getConnection();
        String sql="select et.id as id,m.email as email,p.projectName as projectName,\n" +
                "t.taskName as taskName,t.taskDetails as taskDetails,\n" +
                "et.task_state as task_state\n" +
                "from Employee_Tasks as et inner join Member as m\n" +
                "on et.memberId=m.id\n" +
                "inner join Task as t \n" +
                "on et.taskId=t.id\n" +
                "inner join Project as p\n" +
                "on t.projectId=p.id";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>tasks=new ArrayList<>();
        while(result.next())
        {
            tasks.add(result.getInt("id")+"");
            tasks.add(result.getString("email"));
            tasks.add(result.getString("projectName"));
            tasks.add(result.getString("taskName"));
            tasks.add(result.getString("taskDetails"));
            tasks.add(result.getString("task_state"));
        }
        c.close();
        return tasks;
    }


    /**
     * This method return all tasks of specific state(finished or not finished) with email of the Employee this
     * task has assigned to and the project name that has this task
     * @return ArrayList of String
     * @throws SQLException
     */
    public ArrayList<String> search(String state) throws SQLException {
        Connection c=connect.getConnection();
        String sql="select et.id as id,m.email as email,p.projectName as projectName,\n" +
                "t.taskName as taskName,t.taskDetails as taskDetails,\n" +
                "et.task_state as task_state\n" +
                "from Employee_Tasks as et inner join Member as m\n" +
                "on et.memberId=m.id\n" +
                "inner join Task as t \n" +
                "on et.taskId=t.id\n" +
                "inner join Project as p\n" +
                "on t.projectId=p.id\n" +
                "where task_state='"+state+"'";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>tasks=new ArrayList<>();
        while(result.next())
        {
            tasks.add(result.getInt("id")+"");
            tasks.add(result.getString("email"));
            tasks.add(result.getString("projectName"));
            tasks.add(result.getString("taskName"));
            tasks.add(result.getString("taskDetails"));
            tasks.add(result.getString("task_state"));
        }
        c.close();
        return tasks;
    }

    /**
     * This method return all tasks name that are not assigned to any Employee
     * @return ArrayList of String
     * @throws SQLException
     */
    public ArrayList<String> searchTasksNameNotAssigned() throws SQLException {
        Connection c=connect.getConnection();
        String sql="select taskName from task where assign='not assigned'";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>tasks=new ArrayList<>();
        while(result.next())
        {
            tasks.add(result.getString("taskName"));
        }
        c.close();
        return tasks;
    }
    /**
     * This method return all tasks name that are assigned to any Employee
     * @return ArrayList of String
     * @throws SQLException
     */
    public ArrayList<String> searchTasksNameAssigned() throws SQLException {
        Connection c=connect.getConnection();
        String sql="select taskName from task where assign='assigned'";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>tasks=new ArrayList<>();
        while(result.next())
        {
            tasks.add(result.getString("taskName"));
        }
        c.close();
        return tasks;
    }

    /**
     * This method return task id of specific name
     * @return int
     * @throws SQLException
     */
    public int getTaskId(String taskName) throws SQLException
    {
        Connection c=connect.getConnection();
        String sql="select id from Task where taskName='"+taskName+"'";
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
     * This method update Task to be assigned or not assigned
     * @return boolean
     * @throws SQLException
     */
    public boolean updateAssign(Task task) throws SQLException {
        Connection c=connect.getConnection();
        String sql="update Task set assign=?"+" where id="+task.getId();
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setString(1,task.getAssign());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }


    /**
     * This method get information of the task using the id of the task
     * @return ArrayList of String
     * @throws SQLException
     */
    public ArrayList<String> getTask(int id) throws SQLException {
        Connection c=connect.getConnection();
        String sql="select t.taskDetails as taskDetails,p.projectName as projectName\n" +
                "from Task as t inner join Project p\n" +
                "on t.projectId=p.id\n" +
                "where t.id="+id;
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>tasks=new ArrayList<>();
        while(result.next())
        {
            tasks.add(result.getString("taskDetails"));
            tasks.add(result.getString("projectName"));
        }
        c.close();
        return tasks;
    }

    /**
     * This method get information of the task and the name of the project that has this task
     * @return ArrayList of String
     * @throws SQLException
     */
    public ArrayList<String> searchLeader() throws SQLException {
        Connection c=connect.getConnection();
        String sql="select t.id as id,p.projectName as projectName,\n" +
                "t.taskName as taskName,t.taskDetails as taskDetails,\n" +
                "t.taskPercentage as taskPercentage,t.assign as assign\n" +
                "from Project as p inner join Task as t\n" +
                "on p.id=t.projectId";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>tasks=new ArrayList<>();
        while(result.next())
        {
            tasks.add(result.getInt("id")+"");
            tasks.add(result.getString("projectName"));
            tasks.add(result.getString("taskName"));
            tasks.add(result.getString("taskDetails"));
            tasks.add(result.getString("taskPercentage"));
            tasks.add(result.getString("assign"));
        }
        c.close();
        return tasks;
    }

    /**
     * This method get information of the task and the name of the project that has this task
     * and filter them with project name
     * @return ArrayList of String
     * @throws SQLException
     */
    public ArrayList<String> searchLeader(String projectName) throws SQLException {
        Connection c=connect.getConnection();
        String sql="select t.id as id,p.projectName as projectName,\n" +
                "t.taskName as taskName,t.taskDetails as taskDetails,\n" +
                "t.taskPercentage as taskPercentage,t.assign as assign\n" +
                "from Project as p inner join Task as t\n" +
                "on p.id=t.projectId\n" +
                "where p.projectName='"+projectName+"'";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>tasks=new ArrayList<>();
        while(result.next())
        {
            tasks.add(result.getInt("id")+"");
            tasks.add(result.getString("projectName"));
            tasks.add(result.getString("taskName"));
            tasks.add(result.getString("taskDetails"));
            tasks.add(result.getString("taskPercentage"));
            tasks.add(result.getString("assign"));
        }
        c.close();
        return tasks;
    }

    /**
     * This method add new task
     * @return boolean
     * @throws SQLException
     */
    public boolean add(Task task) throws SQLException {
        Connection c=connect.getConnection();
        String sql="insert into Task(projectId,taskName,taskDetails,taskPercentage) values (?,?,?,?)";
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setInt(1, task.getProjectId());
        statement.setString(2, task.getTaskName());
        statement.setString(3, task.getTaskDetails());
        statement.setInt(4, task.getTaskPercentage());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }


    /**
     * This method update the task with specific id
     * @return boolean
     * @throws SQLException
     */
    public boolean update(Task task) throws SQLException {
        Connection c=connect.getConnection();
        String sql="update Task set taskName=?,taskDetails=?"+" where id="+task.getId();
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setString(1,task.getTaskName());
        statement.setString(2,task.getTaskDetails());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }

    /**
     * This method delete the task with specific id
     * @return boolean
     * @throws SQLException
     */
    public boolean delete(int id) throws SQLException {
        Connection c=connect.getConnection();
        String sql="delete from Task where id="+id;
        Statement statement=c.createStatement();
        int rows=statement.executeUpdate(sql);
        c.close();
        return rows!=0;
    }


}

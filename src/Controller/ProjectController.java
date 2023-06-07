package Controller;

import Model.Project;

import java.sql.*;
import java.util.ArrayList;
/**
 * deal with employee vacation table
 */


public class ProjectController {

    /**
     * @param connect this parameter connect database
     */

    SqlConnection connect;

    /**
     * this constructor connect to database
     */
    public ProjectController() {
        connect=new SqlConnection();
    }


    /**
     * this method update project percentage
     * @param project
     * @return boolean  variable expresses update state
     * @throws SQLException
     */
    public boolean update(Project project) throws SQLException {

        Connection c=connect.getConnection();
        String sql="update Project set projectPercentage=projectPercentage+"+project.getProjectPercentage()+" where id="+project.getId();
        Statement statement=c.createStatement();
        int rows=statement.executeUpdate(sql);
        c.close();
        return rows!=0;
    }

    /**
     * this method get all projects name
     * @return Array list af project names
     * @throws SQLException
     */
    public ArrayList<String> searchProjectName() throws SQLException {
        Connection c=connect.getConnection();
        String sql="select projectName from Project";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>projects=new ArrayList<>();
        while(result.next())
        {
            projects.add(result.getString("projectName"));
        }
        c.close();
        return projects;
    }

    /**
     * this method get all finished project names
     * @return Array list af finished project names
     * @throws SQLException
     */
    public ArrayList<String> searchProjectNameFinished() throws SQLException {
        Connection c=connect.getConnection();
        String sql="select projectName from Project where projectPercentage!=0";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>projects=new ArrayList<>();
        while(result.next())
        {
            projects.add(result.getString("projectName"));
        }
        c.close();
        return projects;
    }

    /**
     * this method get id of specific project
     * @param projectName
     * @return id of specific project
     * @throws SQLException
     */
    public int getProjectId(String projectName) throws SQLException
    {
        Connection c=connect.getConnection();
        String sql="select id from Project where projectName='"+projectName+"'";
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
     * this method get percentage of specific project
     * @param projectId
     * @return percentage of specific project
     * @throws SQLException
     */

    public int getProjectPercentage(int projectId) throws SQLException
    {
        Connection c=connect.getConnection();
        String sql="select projectPercentage from Project where id="+projectId;
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        int percentage=0;
        while(result.next())
        {
            percentage=result.getInt("projectPercentage");
        }
        c.close();
        return percentage;
    }

    /**
     * this method get all projects in the system
     * @return Arrays list of all projects and its names and percentages
     * @throws SQLException
     */

    public ArrayList<String> all() throws SQLException {
        Connection c=connect.getConnection();
        String sql="select * from Project";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        ArrayList<String>allProjects=new ArrayList<>();
        while(result.next())
        {
            allProjects.add(result.getInt("id")+"");
            allProjects.add(result.getString("projectName"));
            allProjects.add(result.getInt("projectPercentage")+"");

        }
        c.close();
        return allProjects;
    }

    /**
     * this method add new project
     * @param project
     * @return boolean  variable expresses add state
     * @throws SQLException
     */
    public boolean add(Project project) throws SQLException {
        Connection c=connect.getConnection();
        String sql="insert into project (projectName) values(?)";
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setString(1, project.getProjectName());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }

    /**
     * this method delete project
     * @param id
     * @return boolean  variable expresses delete state
     * @throws SQLException
     */
    public boolean delete(int id) throws SQLException {
        Connection c=connect.getConnection();
        String sql="delete from project where id="+id;
        Statement statement=c.createStatement();
        int rows=statement.executeUpdate(sql);
        c.close();
        return rows!=0;
    }

    /**
     * this method update project details
     * @param project
     * @return boolean  variable expresses update state
     * @throws SQLException
     */

    public boolean update2(Project project) throws SQLException {
        Connection c=connect.getConnection();
        String sql="update project set projectName=?,projectPercentage=?"+" where id="+project.getId();
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setString(1,project.getProjectName());
        statement.setInt(2,project.getProjectPercentage());
        int rows=statement.executeUpdate();
        c.close();
        return rows!=0;
    }
}

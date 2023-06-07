package Model;

/**
 * this is task model
 */

public class Task {
    private int id;
    private int projectId;
    private String taskName;
    private String taskDetails;
    private int taskPercentage;
    private String assign;

    public String getAssign() {
        return assign;
    }

    public void setAssign(String assign) {
        this.assign = assign;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public int getTaskPercentage() {
        return taskPercentage;
    }

    public void setTaskPercentage(int taskPercentage) {

        this.taskPercentage = taskPercentage;
    }
}

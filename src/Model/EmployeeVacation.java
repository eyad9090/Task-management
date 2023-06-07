package Model;

/**
 * this is Employee vacation model
 */

public class EmployeeVacation {
    private int id;
    private int userId;
    private String startDate;
    private String finishDate;
    private String reason;
    private String state;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public boolean setFinishDate(String finishDate) {
        if(startDate==null)
            return false;
        else
        {
            this.finishDate = finishDate;
            String[] partsStartDate = this.startDate.split("/");
            String[] partsFinishDate = this.finishDate.split("/");
            boolean ans=Integer.parseInt(partsStartDate[0]) < Integer.parseInt(partsFinishDate[0])||
                    Integer.parseInt(partsStartDate[1]) < Integer.parseInt(partsFinishDate[1])||
                    Integer.parseInt(partsStartDate[2]) < Integer.parseInt(partsFinishDate[2]);
            return ans;
        }

    }

    public String getReason() {
        return reason;
    }

    public boolean setReason(String reason) {
        if(reason.isEmpty())
            return false;
        this.reason = reason;
        return true;
    }

    public String getState() {
        if(state==null)
            return "attempted";
        return state;
    }



    public void setState(String state) {
        this.state = state;
    }
}

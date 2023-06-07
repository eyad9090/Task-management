package Model;

/**
 * this is Employee working  model
 */

public class EmployeeWorking {
    private int userId;
    private String startTime;
    private String finishTime;
    private String date;
    private String year;
    private String month;
    private String day;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public boolean setFinishTime(String finishTime) {
        if(startTime==null)
            return false;
        else
        {
            this.finishTime = finishTime;
            String[] partsStartTime = startTime.split(":");
            String[] partsFinishTime = finishTime.split(":");
            return Integer.parseInt(partsStartTime[0]) < Integer.parseInt(partsFinishTime[0]);
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

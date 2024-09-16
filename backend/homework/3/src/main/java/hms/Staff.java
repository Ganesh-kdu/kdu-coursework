package hms;
public class Staff extends  User {
    private long staffId;
    private int yearsOfExperience;
    private String description;
    private double salary;

    public double getSalary() {
        return salary;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public long getStaffId() {
        return staffId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}

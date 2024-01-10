package hms;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private boolean insured;
    private int age;
    private boolean smoking;
    private HealthInsurancePlan insurancePlan = null;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getFullName(){
        return firstName.concat(lastName);
    }

    public void setInsured(boolean insured){
        this.insured = insured;
    }

    public boolean getInsured(){
        return this.insured;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setSmoking(boolean smoking){
        this.smoking = smoking;
    }
    public void setInsurancePlan(HealthInsurancePlan insurancePlan){
        this.insurancePlan = insurancePlan;
    }
    public int getAge(){
        return this.age;
    }

    public boolean getSmoking(){
        return this.smoking;
    }

    public HealthInsurancePlan getInsurancePlan(){
        return this.insurancePlan;
    }
}

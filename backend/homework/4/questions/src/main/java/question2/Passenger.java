package question2;

public class Passenger {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String travelClass;
    private String confirmationNumber;

    public Passenger(String firstName, String lastName, int age, String gender, String travelClass,
                     String confirmationNumber) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.travelClass = travelClass;
        this.confirmationNumber = confirmationNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
    public String getGender() {
        return gender;
    }
    public String getTravelClass() {
        return travelClass;
    }
    public String getConfirmationNumber() {
        return confirmationNumber;
    }
}
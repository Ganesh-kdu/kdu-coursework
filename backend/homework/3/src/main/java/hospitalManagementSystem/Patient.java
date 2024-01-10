package hospitalManagementSystem;

public class Patient extends User{
    private long patientId;

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public Patient(){}

    public Patient(long id,String firstName,String lastName,String gender,String email,long patientId,boolean insured,HealthInsurancePlan insurancePlan){
        this.patientId = patientId;
        this.setInsured(insured);
        this.setInsurancePlan(insurancePlan);
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setGender(gender);
        this.setEmail(email);
    }
}

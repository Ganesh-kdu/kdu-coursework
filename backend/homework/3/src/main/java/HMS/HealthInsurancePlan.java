package HMS;

public class HealthInsurancePlan {
    // Code for 'coverage' field goes here
    // Don't worry about the below code and also the InsuranceBrand class
    private InsuranceBrand offeredBy;
    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }
    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }
}
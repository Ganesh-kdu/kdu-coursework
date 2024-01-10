package HMS;

public abstract class HealthInsurancePlan {
    private double coverage;
    private InsuranceBrand  offeredby;

    public void setOfferedBy(InsuranceBrand offeredby){
        this.offeredby = offeredby;
    }

    public InsuranceBrand getOfferedBy(){
        return offeredby;
    }

    public void setCoverage(double coverage){
        this.coverage = coverage;
    }

    public double getCoverage(){
        return coverage;
    }

    public abstract double computeMonthlyPremium(double salary,int age,boolean smoking);
}

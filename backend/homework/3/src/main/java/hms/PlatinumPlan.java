package hms;

public class PlatinumPlan extends HealthInsurancePlan{

    public PlatinumPlan(){
        setCoverage(0.9);
    }

    public double computeMonthlyPremium(double salary,int age,boolean smoking) {
        return salary * 0.08 + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}

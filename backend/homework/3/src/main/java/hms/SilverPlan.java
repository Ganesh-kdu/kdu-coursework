package hms;

public class SilverPlan extends HealthInsurancePlan{
    public SilverPlan(){
        setCoverage(0.7);
    }

    public double computeMonthlyPremium(double salary,int age,boolean smoking) {
        return salary * 0.06 + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}

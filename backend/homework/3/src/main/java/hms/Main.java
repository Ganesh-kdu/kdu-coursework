package hms;

public class Main {

    public static void main(String[] args){

        User staff = new User();
        InsuranceBrand insuranceBrand = new BlueCrossBlueShield();
        HealthInsurancePlan insurancePlan = new PlatinumPlan();

        insurancePlan.setOfferedBy(insuranceBrand);
        staff.setInsurancePlan(insurancePlan);
        Logs.slf4jLogger.debug("output is {}",insurancePlan.computeMonthlyPremium(5000, 56, true));
    }
}
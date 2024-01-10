package hospitalManagementSystem;

public class Billing {
    public static double[] computePaymentAmount(Patient patient,double amount){
        HealthInsurancePlan healthInsurancePlan = patient.getInsurancePlan();
        double[] answer = new double[2];
        if(healthInsurancePlan == null){
            answer[0] = 0;
            answer[1] = amount - calculatePlan(patient.getInsurancePlan());
            return answer;
        }
        double insurance = healthInsurancePlan.getCoverage(); //0.9

        answer[0] = insurance * amount; //900
        double userCurrent = amount - answer[0]; //100
        double discount = calculatePlan(patient.getInsurancePlan());
        answer[1] = Math.max(0, userCurrent - discount);
        return answer;
    }

    public static double calculatePlan(HealthInsurancePlan healthInsurancePlan){
        if(healthInsurancePlan instanceof PlatinumPlan){
            return 50;
        }else if(healthInsurancePlan instanceof GoldPlan){
            return 40;
        }else if(healthInsurancePlan instanceof SilverPlan){
            return 30;
        }else if(healthInsurancePlan instanceof BronzePlan){
            return 25;
        }else{
            return 20;
        }
    }
    public static void main(String[] args) {
        HealthInsurancePlan insurancePlan = new PlatinumPlan();
        Patient patient = new Patient(1,"Alice","Bob","Male","email@email.com",11,true,insurancePlan);
        double[] payments = Billing.computePaymentAmount(patient, 1000.0);
        String output = "\nAmount covered By insurance company is: " + payments[0] +"\nAmount that patient has to pay is: " + payments[1];
        Logs.slf4jLogger.debug(output);
    }
}

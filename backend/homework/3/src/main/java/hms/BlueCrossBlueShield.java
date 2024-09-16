package hms;

public class BlueCrossBlueShield implements InsuranceBrand{
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking){
        double ans;
        if(insurancePlan instanceof PlatinumPlan){
            ans = premium(age, smoking);
        }else if(insurancePlan instanceof GoldPlan){
            ans = gold(age, smoking);
        }else if(insurancePlan instanceof SilverPlan){
            ans = silver(age, smoking);
        }else{
            ans = bronze(age, smoking);
        }
        return ans;
    }

    public static double premium(int age,boolean smoking){
        double ans = 0;
        if(age > 55){
            ans += 200;
        }
        if(smoking){
            ans+= 100;
        }
        return ans;
    }
    public static double gold(int age,boolean smoking){
        double ans = 0;
        if(age > 55){
            ans += 150;
        }
        if(smoking){
            ans += 90;
        }
        return ans;
    }
    public static double silver(int age,boolean smoking){
        double ans = 0;
        if(age > 55){
            ans += 100;
        }
        if(smoking){
            ans += 80;
        }
        return ans;
    }
    public static double bronze(int age,boolean smoking){
        double ans = 0;
        if(age > 55){
            ans += 50;
        }
        if(smoking){
            ans += 70;
        }
        return ans;
    }
}

package com.ganesh.q3;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import com.ganesh.LogMaster;
public class Main {
    private static int target;
    private static class FactorialAndFactors implements Runnable{
        private AtomicInteger flag = new AtomicInteger(0);
        public int factorial() {
            int result = 1;
            for (int i=1; i<target; i++){
                result*=i;
            }
            LogMaster.print("Factorial done");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return 0;
            }
            return result;
        }
        public ArrayList<Integer> factors() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            for (int i = 1; i < target; i++) {
                if (target % i == 0) {
                    result.add(i);
                }
            }
            LogMaster.print("Factors done");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return new ArrayList<Integer>();
            }
            return result;
        }
        @Override
        public void run() {
            if(0==flag.getAndAdd(1)){
                LogMaster.print(factorial());
            }else {
                LogMaster.print(factors().toString());
            }
            
        }
    }
    public static void main(String[] args){
        target = 10;
        FactorialAndFactors calculate = new FactorialAndFactors();
        Thread thread = new Thread(calculate);
        Thread thread2= new Thread(calculate);
        thread.start();
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LogMaster.print("Ending main thread");
    }
}

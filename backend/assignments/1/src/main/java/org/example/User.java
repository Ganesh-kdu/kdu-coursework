package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    private HashMap<String, Double> wallet;
    private String walletId;
    private String firstName;
    private String lastName;
    private Double profit;
    private String phoneNumber;

    private static HashMap<String, Double> createWallet(String path) throws IOException {
        HashMap<String, Double> wallet = new HashMap<>();
        FileReader fileReader = new FileReader(new File(path));
        CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
        String[] nextRecord;
        while ((nextRecord = csvReader.readNext())!=null){
            wallet.put(nextRecord[3],0.0);
        }
        return wallet;
    }
    public User(String firstName, String lastName, String phoneNumber, String walletId) throws IOException {
        this.walletId = walletId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.profit = 0.0;
        this.wallet =  createWallet("src/main/resources/coins.csv");
    }

    public Double getProfit() {
        return profit;
    }

    public synchronized void updateProfit(double change){
        profit += change;
    }

    public Map<String, Double> getWallet() {
        return wallet;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static JsonNode parseJsonFile(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File from = new File(path);
        return mapper.readTree(from);
    }
    public static List<Coins> parseCSV(String path) throws IOException {
        ArrayList<Coins> coinList = new ArrayList<>();
        FileReader fileReader = new FileReader(new File(path));
        CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
        String[] nextRecord;
        while ((nextRecord = csvReader.readNext())!=null){
            Coins coin = new Coins(Integer.parseInt(nextRecord[1]),nextRecord[2],nextRecord[3],Double.parseDouble(nextRecord[4]),Long.parseLong(nextRecord[5]));
            coinList.add(coin);
        }
        return coinList;
    }

    public static List<User> parseUserCSV(String path) throws IOException {
        ArrayList<User> userList = new ArrayList<>();
        FileReader fileReader = new FileReader(new File(path));
        CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
        String[] nextRecord;
        while ((nextRecord = csvReader.readNext())!=null){
            User user = new User(nextRecord[1],nextRecord[2],nextRecord[3],nextRecord[4]);
            userList.add(user);
        }
        return userList;
    }
    public static void executeTransactions(JsonNode jsonTransactions, CountDownLatch latch) throws InterruptedException {
        ExecutorService transactionThreadPool = Executors.newFixedThreadPool(jsonTransactions.size());
        Helper menu = new Helper();
        Thread menuThread = new Thread(menu);
        menuThread.start();
        for(JsonNode transaction: jsonTransactions){
            ExecuteTransaction transactionObject = new ExecuteTransaction(transaction, latch);
            transactionThreadPool.execute(transactionObject);
        }
        transactionThreadPool.shutdown();
        menuThread.join();

    }
    public static void main(String [] args) throws IOException, InterruptedException {
        JsonNode jsonTransactions = parseJsonFile("src/test/resources/test_transaction_4.json");
        CountDownLatch latch = new CountDownLatch(jsonTransactions.size());
        executeTransactions(jsonTransactions, latch);
        latch.await();
    }
}

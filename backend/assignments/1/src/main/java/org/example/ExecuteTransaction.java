package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ExecuteTransaction implements Runnable{
    private static final String ERROR = "ERROR";
    private static List<Coins> marketPlace;
    private static List<User> traders;
    private User trader;
    private Coins coin;
    private JsonNode transactionDetails;
    private CountDownLatch latch;
    private String transactionType;
    private int retries;
    static {
        try {
            marketPlace = Main.parseCSV("src/main/resources/coins.csv");
            traders = Main.parseUserCSV("src/main/resources/traders.csv");
        } catch (IOException e) {
            Log.customLogger("File not found",ERROR);
        }
    }

    ExecuteTransaction(JsonNode transactionDetails, CountDownLatch latch){
        this.transactionDetails = transactionDetails;
        this.latch = latch;
        this.transactionType = transactionDetails.get("type").asText();
        this.trader = null;
        this.retries = 0;
    }
    @Override
    public void run(){
        boolean success = false;
        getTransactionCoin();
        getTransactionTrader();
        while (!success && retries<100){
            if (transactionType.equals("BUY")){
                success = buyTransaction();
            } else if (transactionType.equals("SELL")) {
                success = sellTransaction();
            }else if (transactionType.equals("UPDATE_PRICE")){
                success = updatePrice();
            }else {
                success = updateVolume();
            }
            if(!success){
                try {
                    Thread.sleep(75);
                    retries+=1;
                } catch (InterruptedException e) {
                    Log.customLogger("Crashed while attempting to sleep thread",ERROR);
                }
            }
        }
        latch.countDown();
        String hash = getBlockHash();
        if (retries<100)
            Log.customLogger(String.format("Exiting %d, Block id: %s",latch.getCount(),hash),"DEBUG");
        else
            Log.customLogger(String.format("Cancelled %s transaction after max 100 retries, insufficient amount", transactionType),ERROR);
    }
    private String getBlockHash() {
        /**
         * Introducing delay mimicking complex
         * calculation being performed.
         */
        String saltchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        Random rnd = new Random();
        double temp=0;
        for (double i = 0; i < 199999999; i++) {
            temp=i;
        }
        temp-=199999998;
        while (transactionHash.length() < 128) {
            int index =  (rnd.nextInt() * saltchars.length());
            transactionHash.append(saltchars.charAt(index));
        }
        String hashCode = transactionHash.toString();
        return Double.toString(temp) + "x" + hashCode.toLowerCase();
    }
    private boolean updateVolume() {
        synchronized (coin.getCirculatingSupply()) {
            coin.updateCirculatingSupply(transactionDetails.get("data").get("volume").asLong());
        }
        return true;
    }

    private boolean updatePrice() {
        synchronized (coin.getPrice()) {
            coin.setPrice(transactionDetails.get("data").get("price").asDouble());
        }
        return true;
    }
    private void getTransactionTrader(){
        if(transactionType.equals("BUY") || transactionType.equals("SELL")) {
            synchronized (traders) {
                for (User t : traders) {
                    if (t.getWalletId().equals(transactionDetails.get("data").get("wallet_address").asText())) {
                        this.trader = t;
                    }
                }
            }
        }
    }
    private void getTransactionCoin(){
        synchronized (marketPlace){
            for(Coins c: marketPlace){
                if(c.getCoinSymbol().equals(transactionDetails.get("data").get("coin").asText())){
                    this.coin = c;
                }
            }
        }
    }
    private boolean sellTransaction() {
        boolean success = false;
        long change = transactionDetails.get("data").get("quantity").asLong();
        synchronized (trader.getWallet().get(transactionDetails.get("data").get("coin").asText())){
            if (trader.getWallet().get(transactionDetails.get("data").get("coin").asText())>=change){
                trader.getWallet().put(transactionDetails.get("data").get("coin").asText(),trader.getWallet().get(transactionDetails.get("data").get("coin").asText())-change);
                success = true;
            }
            if(success){
                synchronized (coin.getCirculatingSupply()) {
                    coin.updateCirculatingSupply(change);
                }
                synchronized (coin.getPrice()){
                    trader.updateProfit(coin.getPrice()*change);
                }
            }
        }

        return success;
    }

    private boolean buyTransaction() {
        boolean success = false;
        long change = transactionDetails.get("data").get("quantity").asLong();
        synchronized (trader.getWallet().get(transactionDetails.get("data").get("coin").asText())){
            synchronized (coin.getCirculatingSupply()){
                if (coin.getCirculatingSupply().get()>=change){
                    coin.updateCirculatingSupply(-change);
                    success=true;
                }
            }
            if (success){
                synchronized (coin.getPrice()){
                    trader.updateProfit(-coin.getPrice()*change);
                }
            }
            trader.getWallet().put(transactionDetails.get("data").get("coin").asText(),trader.getWallet().get(transactionDetails.get("data").get("coin").asText())+change);
        }

        return success;
    }
    public static List<Coins> getMarketPlace() {
        return marketPlace;
    }

    public static List<User> getTraders() {
        return traders;
    }

}

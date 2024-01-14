package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ExecuteTransaction implements Runnable{
    private static List<Coins> marketPlace;
    private static List<User> traders;
    private User trader;
    private Coins coin;
    private JsonNode transactionDetails;
    private CountDownLatch latch;
    private String transactionType;
    static {
        try {
            marketPlace = Main.parseCSV("src/main/resources/coins.csv");
            traders = Main.parseUserCSV("src/main/resources/traders.csv");
        } catch (IOException e) {
            Log.customLogger("File not found","ERROR");
        }
    }

    ExecuteTransaction(JsonNode transactionDetails, CountDownLatch latch){
        this.transactionDetails = transactionDetails;
        this.latch = latch;
        this.transactionType = transactionDetails.get("type").asText();
        this.trader = null;
    }
    @Override
    public void run() {
        boolean success = false;
        getTransactionCoin();
        getTransactionTrader();
        while (!success){
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
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Log.customLogger("Crashed while attempting to sleep thread","ERROR");
                }
            }
        }
        latch.countDown();
        Log.customLogger(String.format("Exiting %d",latch.getCount()),"DEBUG");
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
}

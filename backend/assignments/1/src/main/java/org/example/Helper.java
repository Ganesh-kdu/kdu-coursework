package org.example;

import java.util.*;

public class Helper implements Runnable{
    private int getChoice(){
        Scanner sc = new Scanner(System.in);
        Log.customLogger("Choose an option", "INFO");
        Log.customLogger("1: Get coin details by code", "INFO");
        Log.customLogger("2: Top N coins by price", "INFO");
        Log.customLogger("3: Get portfolio by wallet address", "INFO");
        Log.customLogger("4: Get profit/loss", "INFO");
        Log.customLogger("5: Show top 5 and bottom 5 traders", "INFO");
        Log.customLogger("enter choice: ", "INFO");
        return Integer.parseInt(sc.next());
    }
    private void getCoin(){
        Scanner sc = new Scanner(System.in);
        String code = sc.next();
        List<Coins> marketPlace = ExecuteTransaction.getMarketPlace();
        synchronized (marketPlace){
            for(Coins c: marketPlace){
                if(c.getCoinSymbol().equals(code)){
                    Log.customLogger(String.format("Coin Name: %s",c.getCoinName()),"INFO");
                    Log.customLogger(String.format("Current price: %s",Double.toString(c.getPrice())),"INFO");
                    Log.customLogger(String.format("Current Supply: %d",c.getCirculatingSupply().get()),"INFO");
                    Log.customLogger(String.format("Coin Rank: %d%n%n",c.getRank()),"INFO");
                }
            }
        }
    }
    static class CoinComparator implements Comparator<Object> {
        public int compare(Object o1,Object o2){
            Coins s1=(Coins) o1;
            Coins s2=(Coins)o2;
            if(s1.getPrice().equals(s2.getPrice()))
                return 0;
            else if(s1.getPrice()>s2.getPrice())
                return 1;
            else
                return -1;
        }
    }
    private void getNCoins() {
        List<Coins> coins = ExecuteTransaction.getMarketPlace();
        synchronized (coins) {
            coins.sort(new CoinComparator());
        }
        Log.customLogger("Top 5","INFO");
        coins.subList(0,5).forEach(u -> Log.customLogger(u.getCoinName()+u.getPrice(),"INFO")
        );

    }
    static class TraderComparator implements Comparator<Object> {
        public int compare(Object o1,Object o2){
            User s1=(User) o1;
            User s2=(User)o2;
            if(s1.getProfit().equals(s2.getProfit()))
                return 0;
            else if(s1.getProfit()>s2.getProfit())
                return 1;
            else
                return -1;
        }
    }
    private void getNTraders() {
        List<User> traders = ExecuteTransaction.getTraders();
        synchronized (traders) {
            traders.sort(new TraderComparator());
        }
        Log.customLogger("Top 5","INFO");
        traders.subList(0,5).forEach(u -> Log.customLogger(u.getFirstName()+u.getLastName()+u.getProfit(),"INFO")
        );
        Log.customLogger("Bottom 5","INFO");
        traders.subList(traders.size()-5,traders.size()).forEach(u -> Log.customLogger(u.getFirstName()+u.getLastName()+u.getProfit(),"INFO")
        );
    }

    private void getPortfolio() {
        Scanner sc = new Scanner(System.in);
        String walletID = sc.next();
        List<User> traders = ExecuteTransaction.getTraders();
        Map<String, Double> wallet = null;
        synchronized (traders){
            for(User t: traders){
                if(t.getWalletId().equals(walletID)){
                    wallet = t.getWallet();
                    break;
                }
            }
        }
        assert wallet != null;
        wallet.forEach((k, v)->{
            if (v!=0){
                Log.customLogger(String.format("Coin: %s\t\tQty: %s",k, v),"INFO");
            }
        });
    }

    private void getProfit(){
        Scanner sc = new Scanner(System.in);
        String walletID = sc.next();
        List<User> traders = ExecuteTransaction.getTraders();
        synchronized (traders){
            for(User t: traders){
                if(t.getWalletId().equals(walletID)){
                    Log.customLogger(String.format("Current Profit = %s%n%n",t.getProfit()),"INFO");
                    break;
                }
            }
        }

    }
    @Override
    public void run() {
        Log.customLogger("Starting menu thread","DEBUG");
        int choice = 1;
        while (choice>=1 && choice<=5) {
            choice = getChoice();
            switch (choice){
                case 1:
                    getCoin();
                    break;
                case 2:
                    getNCoins();
                    break;
                case 3:
                    getPortfolio();
                    break;
                case 4:
                    getProfit();
                    break;
                case 5:
                    getNTraders();
                    break;
                default:
                    Log.customLogger("Exiting","INFO");
            }
        }
        Log.customLogger("Ending menu thread","DEBUG");
    }
}

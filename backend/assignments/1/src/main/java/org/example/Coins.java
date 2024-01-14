package org.example;

import java.util.concurrent.atomic.AtomicLong;

public class Coins {
    private int rank;
    private String name;
    private String symbol;
    private Double price;
    private AtomicLong circulatingSupply;

    public Coins(int rank, String name, String symbol, Double price, Long circulatingSupply) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.circulatingSupply = new AtomicLong(circulatingSupply);
    }

    public int getRank() {
        return rank;
    }

    public String getCoinName() {
        return name;
    }

    public String getCoinSymbol() {
        return symbol;
    }

    public synchronized Double getPrice() {
        return price;
    }

    public synchronized void setPrice(double price){this.price = price;}
    public AtomicLong getCirculatingSupply() {
        return circulatingSupply;
    }

    public synchronized void updateCirculatingSupply(long change){
        circulatingSupply.getAndAdd(change);
    }
}

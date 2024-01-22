package org.example.beans;

public class Vehicle {
    Tyre t;
    Speaker s;
    int price;

    public Vehicle(Tyre t, Speaker s, int price) {
        this.t = t;
        this.s = s;
        this.price = price + s.getPrice() + t.getPrice();
    }

    public int getPrice() {
        return price;
    }
}

package org.example.beans;

public class Tyre {
    String brand;
    int price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Tyre() {
    }

    public int getPrice() {
        return price;
    }

    public Tyre(String brand, int price) {
        this.brand = brand;
        this.price = price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

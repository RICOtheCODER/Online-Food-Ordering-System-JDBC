package com.foodieapp.model;


import lombok.Data;

@Data
public class MenuItem {

    private String name;
    private double price;
    private ItemType type;
    private double calorieCount;

    public MenuItem(){

    }
    public MenuItem(String name, double price, ItemType type, double calorieCount) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.calorieCount = calorieCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public double getCalorieCount() {
        return calorieCount;
    }

    public void setCalorieCount(double calorieCount) {
        this.calorieCount = calorieCount;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", calorieCount=" + calorieCount +
                '}';
    }
}

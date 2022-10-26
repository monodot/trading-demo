package com.example.trading;

public class Trade {

    private String customer;
    private String asset;
    private String action; // buy or sell
    private int units;

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "customer='" + customer + '\'' +
                ", asset='" + asset + '\'' +
                ", action='" + action + '\'' +
                ", units=" + units +
                '}';
    }
}

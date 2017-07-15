package com.billmartam.transaction;

import java.io.Serializable;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class Transaction implements Serializable{
    private float price;
    private String date;
    private boolean isCredit;
    private String description;

    public Transaction() {

    }

    public Transaction(float price) {
        this.price = price;
    }

    public Transaction(String date, String description, float price) {
        this.date = date;
        setDescription(description);
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.replace(",","");
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isCredit() {
        return isCredit;
    }

    public void setCredit(boolean credit) {
        isCredit = credit;
    }


    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        String transactionStr = date+" "+description+" "+price+" ";
        return isCredit ? transactionStr+"CR" : transactionStr.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        return Float.compare(that.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return (price != +0.0f ? Float.floatToIntBits(price) : 0);
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

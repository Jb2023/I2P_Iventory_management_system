package org.uos;

public class DataStorage {
    String description;
    float unitPrice, qtyInStock, totalPrice;
    DataStorage(String description, float unitPrice, float qtyInStock, float totalPrice) {
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyInStock = qtyInStock;
        this.totalPrice = totalPrice;
    }
}

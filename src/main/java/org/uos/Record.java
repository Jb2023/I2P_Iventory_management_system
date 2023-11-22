package org.uos;

class Record {
    String description;
    float unitPrice, qtyInStock, totalPrice;
    Record(String description, float unitPrice, float qtyInStock, float totalPrice) {
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyInStock = qtyInStock;
        this.totalPrice = totalPrice;
    }


}

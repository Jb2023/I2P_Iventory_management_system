package org.uos;

/**
 A constructor used to populate new record entries.
 Used in Processes.addItem() and is populated by user input into Java.
 **/
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
}

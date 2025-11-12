package edu.bluejack25_1.pakaian_sepatu.model;

public class Order {

    private long id;
    private String name;
    private long quantity;

    public Order(){}

    public Order(long id, String name, long quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

}

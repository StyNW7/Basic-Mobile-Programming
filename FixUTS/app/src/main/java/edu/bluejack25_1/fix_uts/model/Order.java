package edu.bluejack25_1.fix_uts.model;

public class Order {

    private String name;
    private String orderName;
    private String quantity;

    public Order(){};

    public Order(String name, String orderName, String quantity) {
        this.name = name;
        this.orderName = orderName;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

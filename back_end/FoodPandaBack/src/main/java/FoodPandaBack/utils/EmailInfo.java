package FoodPandaBack.utils;

import java.util.List;

public class EmailInfo {

    private String address;
    private String details;
    private List<CartItem> cart;
    private double total;

    public EmailInfo() {
    }

    public EmailInfo(String address, String details, List<CartItem> cart, double total) {
        this.address = address;
        this.details = details;
        this.cart = cart;
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

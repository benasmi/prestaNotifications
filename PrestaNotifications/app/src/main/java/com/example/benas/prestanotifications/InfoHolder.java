package com.example.benas.prestanotifications;


import android.widget.LinearLayout;

public class InfoHolder {

    private String order_date;
    private String order_reference;
    private String buyer_name;
    private String cost;
    private String order_amount;
    private String payment_method;
    private String order_status;
    private String type;
    private String message;


    public InfoHolder(String cost,String type, String buyer_name, String order_amount, String order_date, String order_reference, String order_status, String payment_method, String message, boolean isClicked) {
        this.buyer_name = buyer_name;
        this.order_amount = order_amount;
        this.type = type;
        this.cost = cost;
        this.order_date = order_date;
        this.order_reference = order_reference;
        this.order_status = order_status;
        this.payment_method = payment_method;
        this.message = message;
    }



    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_reference() {
        return order_reference;
    }

    public void setOrder_reference(String order_reference) {
        this.order_reference = order_reference;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

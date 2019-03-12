package com.online.bookstore.model.order;

public class PaymentImpl implements Payment {
    private int paymentId;
    private String name;
    private int CreditCardNum;
    private String expDate;
    private Double amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditCardNum() {
        return CreditCardNum;
    }

    public void setCreditCardNum(int creditCardNum) {
        CreditCardNum = creditCardNum;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
}

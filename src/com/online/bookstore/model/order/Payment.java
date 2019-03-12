package com.online.bookstore.model.order;

public interface Payment {

    public int getPaymentId();
    public void setPaymentId(int paymentId);
    public String getName();
    public void setName(String name);
    public int getCreditCardNum();
    public void setCreditCardNum(int creditCardNum);
    public String getExpDate();
    public void setExpDate(String expDate);
    public Double getAmount();
    public void setAmount(Double amount);
}

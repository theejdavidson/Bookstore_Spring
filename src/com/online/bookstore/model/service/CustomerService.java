package com.online.bookstore.model.service;


import com.online.bookstore.model.customer.Customer;

public class CustomerService {
	private Customer customer;
	
	public void setCustomer(Customer customer) {
	    this.customer = customer;
	  }
	
	public Customer getCustomer() {
		return customer;
	}
	
}

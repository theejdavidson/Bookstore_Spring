package com.online.bookstore.client;

import java.util.List;

import com.online.bookstore.model.order.PaymentImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.online.bookstore.model.customer.Address;
import com.online.bookstore.model.customer.Customer;
import com.online.bookstore.model.order.Order;
import com.online.bookstore.model.order.OrderDetail;
import com.online.bookstore.model.product.Book;
import com.online.bookstore.model.product.Product;
import com.online.bookstore.model.service.CustomerService;

public class BookStoreClient {
			
		public static void main (String args[]) throws Exception { 
	
	        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
	        System.out.println("***************** Application Context instantiated! ******************");

	        //Spring to inject the right object implementation in CustomerService for customer using Setter Injection
	        //Also, bootstrapping the CustomerService instantiation using factory
	        CustomerService customerService = (CustomerService) context.getBean("customerService");
	     
	        Customer customer = customerService.getCustomer();
			customer.setFirstName("Michael");
	        customer.setLastName("Gerard");
	        customer.setCustomerId("AY2345");
	        
	        //Spring to inject the right object implementation in Customer object for BillingAddress using Setter Injection
	        Address billingAddress = customer.getBillingAddress();
	        
	        billingAddress.setStreet("500 West Madison St.");
	        billingAddress.setUnit("Suite 1000");
	        billingAddress.setCity("Chicago");
	        billingAddress.setState("IL");
	        billingAddress.setZip("66610");	               
	        
	        /**********************************************************************/
	        //Bootstrapping the Order instantiation using factory
	        Order order1 = (Order) context.getBean("order");
	        
	        order1.setOrderId("XX-66734");

	        //Associate the order with the customer
	        customer.addOrder(order1);
	        
	        //order detail contains products ordered
	        OrderDetail orderDetail1 = (OrderDetail) context.getBean("orderDetail");
	        //First product
	        Book product1 = orderDetail1.getBook(); 
	        product1.setId("BF-7898");
	        product1.setISBN("234-89675-27690");
	        product1.setTitle("Patterns of Enterprise Application Architecture");
	        product1.setAuthor("Folwer, Martin");
	        product1.setPrice(50.99);
	        orderDetail1.setQuantity(1);
	        //Add product to order
	        order1.addProduct(orderDetail1);

	        //Second order detail
	        OrderDetail orderDetail2 = (OrderDetail) context.getBean("orderDetail");
	        //Second product
	        Book product2 = orderDetail2.getBook();
	        product2.setId("BF-2345");
	        product2.setISBN("892-12345-93667");
	        product2.setTitle("Web Application Architecture");
	        product2.setAuthor("Shklar, Leon");
	        product2.setPrice(45.99);
	        orderDetail2.setQuantity(1);

	        order1.getPayment().setPaymentId(1);
	        order1.getPayment().setAmount(50.01);
	        order1.getPayment().setCreditCardNum(1241234);
	        order1.getPayment().setExpDate("Never");
	        order1.getPayment().setName("Jim's Payment");

	        //Add product to order
	        order1.addProduct(orderDetail2);
	        
	        //finish order	        
	        order1.confirmOrder();
	        order1.orderPayed();
	        
	        // NOTE: To cancel the request, un-comment the following line.
	        //order.cancel(); Then, comment out the next 2 lines.
	        order1.orderSendOut();
	        order1.orderDelivered();
	        
	        // Print out an order summary
        	System.out.println("\tCustomer Name: \t\t\t" + customer.getFirstName() + " " + customer.getLastName() + "\n");
        	System.out.println("\tBilling Address:\t" + customer.getBillingAddress().getStreet() + 
        		"\n\t\t\t\t" + customer.getBillingAddress().getUnit() + 
        		"\n\t\t\t\t" + customer.getBillingAddress().getCity() + ", " + 
        		customer.getBillingAddress().getState() + " " + customer.getBillingAddress().getZip() +
        		"\n");
	        List<Order> orders = customer.getOrders();
	        for (Order order : orders) {        
	        	// Format order output
	        	System.out.println("\n\t" +"+++++++++++++++++++++++++++++++++");
	        	System.out.println("\tOrder Id: \t\t" + order.getOrderId() + "\n");
	        	System.out.println("\tOrder status: \t\t" + order.getOrderState() + "\n");

	        	System.out.println("\tOrder Items: ");
	        	List<OrderDetail> orderLines = order.getOrderDetails();
	        	for (OrderDetail line : orderLines) {
	        		System.out.println("\t\t\t\t" + line.getBook().getTitle() + "\t" + 
	        			line.getBook().getPrice() + " x " + line.getQuantity());
	        	}
	        	double orderTotal = order.getOrderTotal();
		        System.out.println("\n\tOrder Total:\t\t" + orderTotal);
		        //Prints out order payment info without needing to instantiate a Payment object, it all gets done
				//through order1's payment object via Spring's dependency injection
		        System.out.println("\n\tOrder Payment info:\t\t" + "\nName: " + order1.getPayment().getName() + "\nEXP Date: " +
						order1.getPayment().getExpDate() + " \nCredit card num: "
						+ order1.getPayment().getCreditCardNum() + "\nAmount: " + order.getPayment().getAmount());
	       }
		}

}

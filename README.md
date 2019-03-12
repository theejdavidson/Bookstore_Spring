# Bookstore_Spring
A bookstore project featuring dependency injection via Spring

Order can reach the interface without needing to directly reach
the PaymentImpl class logic. The benefit is that an order can be
created and directly set payment attributes without a payment object
being set. See client main class for implementation.

package org.example.ordersservice.exception;

public class ApplicationErrors {
    public static ApplicationException ORDER_EXISTS() { return new ApplicationException(40001, 400, "order exited"); };
    public static ApplicationException ORDER_NOT_EXISTS() { return new ApplicationException(40002, 400, "order not exited"); };
}

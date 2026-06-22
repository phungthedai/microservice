package org.example.ordersservice.exception;

public class ApplicationErrors {
    public static ApplicationException ORDER_EXISTS() { return new ApplicationException(40001, 400, "order exited"); };
    public static ApplicationException ORDER_NOT_EXISTS() { return new ApplicationException(40002, 400, "order not exited"); };
    public static ApplicationException PROMOTION_NOT_EXISTS() { return new ApplicationException(40301, 403, "promotion not exited"); };
    public static ApplicationException PROMOTION_Expired () { return new ApplicationException(40301, 403, "promotion expired"); };
    public static ApplicationException PROMOTION_UNISSUED () { return new ApplicationException(40302, 403, "promotion unissued"); };
    public static ApplicationException INELIGIBLE_TOTAL_AMOUNT () { return new ApplicationException(40002, 400, "Ineligible total amount"); };
    public static ApplicationException USER_LIMIT_EXCEEDED () { return new ApplicationException(40003, 400, "User limit"); };
}

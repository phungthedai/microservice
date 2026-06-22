package org.example.promotionservice.exception;

public class ApplicationErrors {
    public static ApplicationException PROMOTIONS_EXISTS() { return new ApplicationException(40001, 400, "promotions exited"); };
}

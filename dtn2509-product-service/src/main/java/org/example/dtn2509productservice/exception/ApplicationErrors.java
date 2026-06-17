package org.example.dtn2509productservice.exception;

public class ApplicationErrors {
    public static ApplicationException CATEGORY_NAME_EXISTS() { return new ApplicationException(40001, 400, "username exited"); };
    public static ApplicationException PRODUCT_NAME_EXISTS() { return new ApplicationException(40001, 400, "username exited"); };
    public static ApplicationException CATEGORY_NOT_FOUND() { return new ApplicationException(40401, 404, "category not found"); };
    public static ApplicationException PARENT_CATEGORY_NOT_FOUND() { return new ApplicationException(40401, 404, "parent category not found"); };
    public static ApplicationException PRODUCT_NOT_FOUND() { return new ApplicationException(40401, 404, "category not found"); };
}

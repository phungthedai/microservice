package org.example.promotionservice.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException extends RuntimeException {
    private Integer code;
    private Integer status;

    public ApplicationException(String message) {
        super(message);
    }
    public ApplicationException(Integer code, Integer status, String message) {
        super(message);
        this.code = code;
        this.status = status;
    }
}

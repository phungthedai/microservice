package org.example.dtn2509productservice.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException extends RuntimeException {
    private Integer code;
    private Integer status;

    public ApplicationException(Integer code, Integer status, String message) {
        super(message);
        this.code = code;
        this.status = status;
    }
}

package org.example.dtn2509productservice.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private T data;
    private String message;
    private Integer code;
    private Integer status;
//    private MetaData metaData;

    public BaseResponse(T data, Integer code, Integer status, String message) {
        this.data = data;
        this.message = message;
        this.code = code;
        this.status = status;
    }
    public BaseResponse(T data, Integer code, String message) {
        this.data = data;
        this.message = message;
        this.code = code;
    }
    public BaseResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }
    public BaseResponse(String message) {
        this.message = message;
    }
    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

//    public static <T> BaseResponse<List<T>> topPage(Integer code, String message, Page page) {
//        BaseResponse<List<T>> baseResponse = new BaseResponse<List<T>>();
//        baseResponse.setCode(code);
//        baseResponse.setMessage(message);
//        baseResponse.setData(page.getContent());
//        MetaData metaData = new MetaData(page.getNumber(),  page.getSize(), page.getTotalElements());
//        baseResponse.setMetaData(metaData);
//        return baseResponse;
//    }
}

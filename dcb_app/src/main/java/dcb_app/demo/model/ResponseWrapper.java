package dcb_app.demo.model;

import lombok.Data;

@Data
public class ResponseWrapper<T> {
    private String message;
    private T data;
}
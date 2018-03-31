package model.responsemodel;

import java.io.Serializable;

public class ResponseError extends ResponseModel implements Serializable{

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

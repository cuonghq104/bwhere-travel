package model.responsemodel;

import java.io.Serializable;

public class ResponseInsertSuccess extends ResponseModel implements Serializable{

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

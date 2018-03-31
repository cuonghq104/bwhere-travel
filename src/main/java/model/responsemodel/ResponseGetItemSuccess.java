package model.responsemodel;

import java.io.Serializable;

public class ResponseGetItemSuccess extends ResponseModel implements Serializable{

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object object) {
        this.data = object;
    }
}

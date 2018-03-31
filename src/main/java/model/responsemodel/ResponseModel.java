package model.responsemodel;

import java.io.Serializable;

public class ResponseModel implements Serializable{

    protected boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

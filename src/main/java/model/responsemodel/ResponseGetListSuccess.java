package model.responsemodel;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseGetListSuccess extends ResponseModel implements Serializable{

    private ArrayList list;

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }
}

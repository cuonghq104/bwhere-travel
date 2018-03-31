package model;

import java.io.Serializable;
import java.util.ArrayList;

public class JourneyDetail extends Journey implements Serializable{

    private ArrayList<JourneyPlaceDetail> details;

    public ArrayList<JourneyPlaceDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<JourneyPlaceDetail> details) {
        this.details = details;
    }
}

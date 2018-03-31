package model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class JourneyPlace implements Serializable{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String idJourney;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String idPlace;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String time;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String day;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int distance;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int cost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdJourney() {
        return idJourney;
    }

    public void setIdJourney(String idJourney) {
        this.idJourney = idJourney;
    }

    public String getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(String idPlace) {
        this.idPlace = idPlace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}

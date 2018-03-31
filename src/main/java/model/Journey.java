package model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class Journey implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String start;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String end;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected int cost;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String imageUrl;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String travelBy;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String time;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTravelBy() {
        return travelBy;
    }

    public void setTravelBy(String travelBy) {
        this.travelBy = travelBy;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

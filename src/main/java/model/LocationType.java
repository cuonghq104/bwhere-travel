package model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class LocationType implements Serializable{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nameEng;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nameVie;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getNameVie() {
        return nameVie;
    }

    public void setNameVie(String nameVie) {
        this.nameVie = nameVie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

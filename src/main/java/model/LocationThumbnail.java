package model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class LocationThumbnail implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String imageUrl;

}

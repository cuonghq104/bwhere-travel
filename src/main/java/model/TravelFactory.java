package model;

public class TravelFactory {

    public static final String LOCATION = "location";

    public static final String THUMB = "thumb";

    public static final String TYPE = "type";

    public static Object create(String object) {
        switch (object) {
            case LOCATION:
                return new Location();
            case THUMB:
                return new LocationThumbnail();
            case TYPE:
                return new LocationType();
            default:
                return new Object();
        }
    }
}

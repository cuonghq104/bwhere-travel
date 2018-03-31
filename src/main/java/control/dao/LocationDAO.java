package control.dao;

import control.common.Constant;
import control.common.DatabaseConnector;
import control.common.RandomString;
import control.common.Utilities;
import model.*;
import model.RequestModel.LocationRequestModel;
import model.responsemodel.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class LocationDAO {

    public Location getLocationInfo(String id) {
        Location location = null;
        Connection con = DatabaseConnector.getInstance();
        String sql = "SELECT travel_place.id, travel_place.latitude, travel_place.longitude, travel_place.name, travel_place.icon, travel_place.zoom, travel_place.description, travel_category.*\n" +
                "FROM travel_place\n" +
                "LEFT JOIN travel_category ON travel_place.category = travel_category.id\n" +
                "WHERE travel_place.id = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);

            ResultSet rs = pst.executeQuery();
            if (rs.last()) {
                rs.beforeFirst();
            }

            while (rs.next()) {
                double latitute = rs.getDouble(2);
                double longitute = rs.getDouble(3);
                String name = Utilities.optimizeString(rs.getString(4));
                String image = Utilities.optimizeString(rs.getString(5));
                int zoom = rs.getInt(6);
                String description = Utilities.optimizeString(rs.getString(7));
                String locationTypeNameENG = rs.getString(9);
                String locationTypeNameVIE = rs.getString(10);

                LocationType locationType = new LocationType();
                locationType.setNameVie(locationTypeNameVIE);
                locationType.setNameEng(locationTypeNameENG);

                location = new Location();
                location.setImage(image);
                location.setName(name);
                location.setLocationType(locationType);
                location.setLatitude(latitute);
                location.setLongitude(longitute);
                location.setZoom(zoom);
                location.setDescription(description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return location;

    }

    public ArrayList<String> getThumbnailList(String id) {
        String sql = "SELECT name FROM travel_image " +
                "WHERE id_parent = ?";

        ArrayList<String> images = new ArrayList<>();
        try {
            PreparedStatement pst = DatabaseConnector.getInstance().prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.last()) {
                rs.beforeFirst();
            }

            while (rs.next()) {
                String image = rs.getString(1);
                images.add(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return images;
    }
    public ResponseModel getLocationDetail(String id) {

        Location location = getLocationInfo(id);

        if (location == null) {
            ResponseError error = new ResponseError();
            String message = "Id doesn't exist";
            error.setMessage(message);
            error.setSuccess(false);
            return error;
        } else {
            ArrayList<String> images = getThumbnailList(id);
            location.setThumbnails(images);

            ResponseGetItemSuccess success = new ResponseGetItemSuccess();
            success.setSuccess(true);
            success.setData(location);
            return success;
        }
    }

    public String addLocation(Location location) {
        Connection con = DatabaseConnector.getInstance();
        String sql = "INSERT INTO travel_place " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        int rs = 0;

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, location.getId());
            pst.setDouble(2, location.getLatitude());
            pst.setDouble(3, location.getLongitude());
            pst.setString(4, location.getName());
            pst.setString(5, location.getImage());
            pst.setString(6, location.getLocationType().getId());
            pst.setInt(7, location.getZoom());
            pst.setString(8, location.getDescription());

            rs = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (rs > 0) ? "ok" : "error";
    }

    public ResponseModel getLocationList() {

        Connection con = DatabaseConnector.getInstance();

        String sql = "SELECT travel_place.id, travel_place.name, travel_place.icon, travel_place.zoom, travel_place.icon, travel_category.name_vi, travel_place.latitude, travel_place.longitude\n" +
                "FROM travel_place\n" +
                "LEFT JOIN travel_category ON travel_category.id = travel_place.category";

        ArrayList<Location> locations = new ArrayList<>();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.last()) {
                rs.beforeFirst();

                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2).replace("\u0000", "");
                    String image = rs.getString(3);
                    int zoom = rs.getInt(4);
                    String locationTypeImage = rs.getString(5);
                    String locationTypeName = rs.getString(6);
                    double latitude = rs.getDouble(7);
                    double longitude = rs.getDouble(8);
                    //

                    LocationType locationType = (LocationType) TravelFactory.create(TravelFactory.TYPE);
                    locationType.setNameVie(locationTypeName);
                    locationType.setImage(Constant.LOCATION_TYPE_ICON_URL_PREFIX + locationTypeImage + Constant.LOCATION_ICON_EXT);
                    //

                    Location location = (Location) TravelFactory.create(TravelFactory.LOCATION);
                    location.setLocationType(locationType);
                    location.setZoom(zoom);
                    location.setId(id);
                    location.setName(name);
                    location.setImage(image);
                    location.setLatitude(latitude);
                    location.setLongitude(longitude);
                    //

                    locations.add(location);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ResponseError error = new ResponseError();
            error.setSuccess(false);
            error.setMessage(e.getMessage());
        }

        ResponseGetListSuccess success = new ResponseGetListSuccess();
        success.setSuccess(true);
        success.setList(locations);
        return success;
    }

    public ResponseModel getLocationTypeList() {
        String sql = "SELECT * FROM travel_category ";

        ArrayList<LocationType> locationTypes = new ArrayList<>();

        try {
            PreparedStatement pst = DatabaseConnector.getInstance().prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            if (rs.last()) {
                rs.beforeFirst();
            }

            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(3);
                String icon = rs.getString(4);

                LocationType type = new LocationType();
                type.setImage(icon);
                type.setNameVie(name);
                type.setId(id);

                locationTypes.add(type);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            ResponseError error = new ResponseError();
            error.setSuccess(false);
            error.setMessage(e.getMessage());
            return error;
        }

        ResponseGetListSuccess success = new ResponseGetListSuccess();
        success.setSuccess(true);
        success.setList(locationTypes);
        return success;
    }

    public ResponseModel insertLocation(LocationRequestModel model) {

        String sql = "INSERT INTO travel_place(id, latitude, longitude, name, icon, category, create_by, create_at, description) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int result = 0;

        try {
            PreparedStatement pst = DatabaseConnector.getInstance().prepareStatement(sql);
            pst.setString(1, new RandomString(32).nextString());
            pst.setDouble(2, model.getLatitude());
            pst.setDouble(3, model.getLongitude());
            pst.setString(4, model.getName());
            pst.setString(5, model.getImage());
            pst.setString(6, model.getCategory());
            pst.setString(7, model.getCreateBy());
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            pst.setString(8, timeStamp);
            pst.setString(9, model.getDescription());

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            ResponseError error = new ResponseError();
            error.setMessage(e.getMessage());
            error.setSuccess(false);
            return error;
        }

        if (result > 0) {
            ResponseInsertSuccess success = new ResponseInsertSuccess();
            success.setMessage("Insert succeeded");
            success.setSuccess(true);
            return success;
        } else {
            return null;
        }
    }

    public boolean verifyAdmin(String id) {
        String sql = "SELECT id FROM tbl_admin " +
                "WHERE id = ?";

        try {
            PreparedStatement pst = DatabaseConnector.getInstance().prepareStatement(sql);
            pst.setString(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                rs.beforeFirst();
            }

            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    public ResponseModel getPendingLocationList() {
        Connection con = DatabaseConnector.getInstance();

        String sql = "SELECT travel_place.id, travel_place.name, travel_place.icon, travel_place.zoom, travel_place.icon, travel_category.name_vi, travel_place.latitude, travel_place.longitude\n" +
                "FROM travel_place\n" +
                "LEFT JOIN travel_category ON travel_category.id = travel_place.category " +
                "WHERE travel_place.verifyAdmin IS NULL ";

        ArrayList<Location> locations = new ArrayList<>();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.last()) {
                rs.beforeFirst();

                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2).replace("\u0000", "");
                    String image = rs.getString(3);
                    int zoom = rs.getInt(4);
                    String locationTypeImage = rs.getString(5);
                    String locationTypeName = rs.getString(6);
                    double latitude = rs.getDouble(7);
                    double longitude = rs.getDouble(8);
                    //

                    LocationType locationType = (LocationType) TravelFactory.create(TravelFactory.TYPE);
                    locationType.setNameVie(locationTypeName);
                    locationType.setImage(Constant.LOCATION_TYPE_ICON_URL_PREFIX + locationTypeImage + Constant.LOCATION_ICON_EXT);
                    //

                    Location location = (Location) TravelFactory.create(TravelFactory.LOCATION);
                    location.setLocationType(locationType);
                    location.setZoom(zoom);
                    location.setId(id);
                    location.setName(name);
                    location.setImage(image);
                    location.setLatitude(latitude);
                    location.setLongitude(longitude);
                    //

                    locations.add(location);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ResponseError error = new ResponseError();
            error.setSuccess(false);
            error.setMessage(e.getMessage());
        }

        ResponseGetListSuccess success = new ResponseGetListSuccess();
        success.setSuccess(true);
        success.setList(locations);
        return success;
    }

    public ResponseModel verifyPlace(String idAdmin, String idPlace) {
        String sql = "UPDATE travel_place \n" +
                "SET verifyAdmin = ?\n" +
                "WHERE id = ?";

        int result = 0;

        try {
            PreparedStatement pst = DatabaseConnector.getInstance().prepareStatement(sql);
            pst.setString(1, idAdmin);
            pst.setString(2, idPlace);

            result = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            ResponseError responseError = new ResponseError();
            responseError.setMessage(e.getMessage());
            responseError.setSuccess(false);
            return responseError;
        }

        if (result > 0) {
            ResponseInsertSuccess success = new ResponseInsertSuccess();
            success.setSuccess(true);
            success.setMessage("Update succeeded");
            return success;
        }
        return null;
    }

}

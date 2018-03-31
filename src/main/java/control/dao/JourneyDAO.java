package control.dao;

import control.common.Constant;
import control.common.DatabaseConnector;
import control.common.RandomString;
import model.Journey;
import model.JourneyDetail;
import model.JourneyPlace;
import model.JourneyPlaceDetail;
import model.responsemodel.ResponseError;
import model.responsemodel.ResponseGetListSuccess;
import model.responsemodel.ResponseModel;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import control.common.Constant.*;

public class JourneyDAO {

    public String insertJourney(Journey journey) {
        String sql = "INSERT INTO journey(id, start, end, description, cost, imageUrl, travelBy, time) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        int rs = 0;

        try {
            PreparedStatement pst = DatabaseConnector.getInstance().prepareStatement(sql);
            pst.setString(1, new RandomString(16).nextString());
            pst.setString(2, journey.getStart());
            pst.setString(3, journey.getEnd());
            pst.setString(4, journey.getDescription());
            pst.setInt(5, journey.getCost());
            pst.setString(6, journey.getImageUrl());
            pst.setString(7, journey.getTravelBy());
            pst.setString(8, journey.getTime());

            rs = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error : " + e.getMessage();
        }

        if (rs > 0) {
            return "success";
        }
        return "";
    }

    public ResponseModel getList() {
        String sql = "SELECT * FROM journey ";
        ArrayList<Journey> journeys = new ArrayList<>();

        try {
            PreparedStatement pst = DatabaseConnector.getInstance().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.last()) {
                rs.beforeFirst();
            }

            while (rs.next()) {
                String id = rs.getString(1);
                String start = rs.getString(2);
                String end = rs.getString(3);
                String description = rs.getString(4);
                int cost = rs.getInt(5);
                String imageUrl = rs.getString(6);
                String travelBy = rs.getString(7);
                String time = rs.getString(8);

                Journey journey = new Journey();
                journey.setEnd(end);
                journey.setId(id);
                journey.setImageUrl(imageUrl);
                journey.setStart(start);
                journey.setTime(time);

                journeys.add(journey);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ResponseError error = new ResponseError();
            error.setSuccess(false);
            error.setMessage(e.getMessage());
            return error;
        }

        ResponseGetListSuccess success = new ResponseGetListSuccess();
        success.setList(journeys);
        success.setSuccess(true);
        return success;
    }

    public String insertJourneyPlace(JourneyPlace place) {
        String sql = "INSERT INTO journey_place(idJourney, idPlace, description, cost, time, day, distance) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?) ";

        int rs = 0;
        try {
            PreparedStatement pst = DatabaseConnector.getInstance().prepareStatement(sql);
            pst.setString(1, place.getIdJourney());
            pst.setString(2, place.getIdPlace());
            pst.setString(3, place.getDescription());
            pst.setInt(4, place.getCost());
            pst.setString(5, place.getTime());
            pst.setString(6, place.getDay());
            pst.setInt(7, place.getDistance());

            rs = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }

        if (rs > 0) {
            return "Succeeded";
        }
        return "";
    }

    public JourneyDetail getJourneyDetail(String id) {
        String sql = "SELECT * FROM journey WHERE id = ?";
        JourneyDetail journey = null;
        try {
            PreparedStatement pst = DatabaseConnector.getInstance().prepareStatement(sql);
            pst.setString(1, id);

            ResultSet rs = pst.executeQuery();
            if (rs.last()) {
                rs.beforeFirst();
            }

            while (rs.next()) {
                String start = rs.getString(2);
                String end = rs.getString(3);
                String description = rs.getString(4);
                int cost = rs.getInt(5);
                String imageUrl = rs.getString(6);
                String travelBy = rs.getString(7);
                String time = rs.getString(8);

                journey = new JourneyDetail();
                journey.setCost(cost);
                journey.setDescription(description);
                journey.setEnd(end);
                journey.setId(id);
                journey.setImageUrl(imageUrl);
                journey.setStart(start);
                journey.setTravelBy(travelBy);
                journey.setTime(time);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return journey;
    }

    public ArrayList<JourneyPlaceDetail> getPlaceList(String idJourney) {
        String sql = "SELECT journey_place.id, journey_place.description, journey_place.cost, journey_place.time, journey_place.day, journey_place.distance,\n" +
                "travel_place.name, travel_place.icon, travel_category.name_vi\n" +
                "FROM journey_place\n" +
                "LEFT JOIN travel_place ON travel_place.id = journey_place.idPlace\n" +
                "LEFT JOIN travel_category ON travel_place.category = travel_category.id\n" +
                "WHERE journey_place.idJourney = ?";

        ArrayList<JourneyPlaceDetail> details = new ArrayList<>();

        try {
            PreparedStatement pst = DatabaseConnector.getInstance().prepareStatement(sql);
            pst.setString(1, idJourney);
            ResultSet rs = pst.executeQuery();

            if (rs.last()) {
                rs.beforeFirst();
            }

            while (rs.next()) {

                JourneyPlaceDetail detail = new JourneyPlaceDetail();

                String id = rs.getString(1);
                String description = rs.getString(2);
                int cost = rs.getInt(3);
                String time = rs.getString(4);
                String day = rs.getString(5);
                int distance = rs.getInt(6);
                String name = rs.getString(7);
                String thumbnail = rs.getString(8);
                String type = rs.getString(9);

                detail.setName(name);
                detail.setType(type);
                detail.setCost(cost);
                detail.setDay(day);
                detail.setDistance(distance);
                detail.setDescription(description);
                detail.setId(id);
                detail.setTime(time);
                detail.setThumbnail(Constant.LOCATION_ICON_URL_PREFIX + thumbnail + Constant.LOCATION_ICON_EXT);

                details.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }
}

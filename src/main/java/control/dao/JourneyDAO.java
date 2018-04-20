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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface JourneyDAO {
    public String insertJourney(Journey journey);

    public ResponseModel getList();

    public String insertJourneyPlace(JourneyPlace place);

    public JourneyDetail getJourneyDetail(String id);

    public ArrayList<JourneyPlaceDetail> getPlaceList(String idJourney);

}

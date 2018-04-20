package control.dao;

import control.common.Constant;
import control.common.DatabaseConnector;
import control.common.RandomString;
import control.common.Utilities;
import model.Location;
import model.LocationType;
import model.RequestModel.LocationRequestModel;
import model.TravelFactory;
import model.responsemodel.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public interface LocationDAO {
    public Location getLocationInfo(String id);

    public ArrayList<String> getThumbnailList(String id);

    public ResponseModel getLocationDetail(String id);

    public ResponseModel getLocationList();

    public ResponseModel getLocationTypeList();

    public ResponseModel getLocationListByUser(String userId);

    public ResponseModel insertLocation(LocationRequestModel model);

    public ResponseModel getPendingLocationList();

    public ResponseModel verifyPlace(String idAdmin, String idPlace);
}

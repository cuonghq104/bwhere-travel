package hello;

import control.common.Constant;
import control.common.Utils;
import control.dao.JourneyDAOImplement;
import control.dao.LocationDAOImp;
import model.*;
import model.RequestModel.LocationRequestModel;
import model.responsemodel.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class GreetingController {

    @RequestMapping(Constant.API_GET_LOCATION_LIST)
    public ResponseModel getLocationList() {
        LocationDAOImp dao = new LocationDAOImp();
        ResponseModel responseBody = dao.getLocationList();
        return responseBody;
    }

    @RequestMapping(Constant.API_LOCATION_DETAIL)
    public ResponseModel getLocationDetail(@RequestParam(value = "locationId", defaultValue = "1") String id) {
        LocationDAOImp dao = new LocationDAOImp();
        ResponseModel model = dao.getLocationDetail(id);
        return model;
    }

    @RequestMapping(value = Constant.API_LOCATION_DETAIL, method = RequestMethod.POST)
    public ResponseModel insertNewLocation(@RequestBody LocationRequestModel model) {
        LocationDAOImp dao = new LocationDAOImp();
        ResponseModel rm = dao.insertLocation(model);
        return rm;
    }

    @RequestMapping(value = Constant.API_GET_LOCATION_BY_USER, method = RequestMethod.GET)
    public ResponseModel getLocationListByUser(@RequestParam(value = "userId", defaultValue = "1") String id) {
        LocationDAOImp dao = new LocationDAOImp();
        ResponseModel rm = dao.getLocationListByUser(id);
        return rm;
    }

    @RequestMapping(value = Constant.API_GET_PENDING_LOCATION, method = RequestMethod.GET)
    public ResponseModel getPendingLocationList(@RequestParam(name = "admin", defaultValue = "0") String id) {
        LocationDAOImp dao = new LocationDAOImp();
        boolean isAdmin = dao.verifyAdmin(id);


        if (!isAdmin) {
            ResponseError error = new ResponseError();
            error.setSuccess(false);
            error.setMessage("Authorization error");
            return error;
        } else {
            ResponseModel model = dao.getPendingLocationList();
            return model;
        }

    }

    @RequestMapping(value = Constant.API_VERIFY_LOCATION, method = RequestMethod.PUT)
    public ResponseModel verifyPlace(@RequestParam(name = "admin", defaultValue = "0") String idAdmin, @RequestParam(name = "location", defaultValue = "0") String idLocation) {
        LocationDAOImp dao = new LocationDAOImp();
        boolean isAdmin = dao.verifyAdmin(idAdmin);

        if (!isAdmin) {
            ResponseError error = new ResponseError();
            error.setSuccess(false);
            error.setMessage("Authorization error");
            return error;
        } else {
            ResponseModel model = dao.verifyPlace(idAdmin, idLocation);
            return model;
        }

    }


    @RequestMapping(value = Constant.API_JOURNEY, method = RequestMethod.POST)
    public ResponseModel addJourney(@RequestBody Journey journey) {
        Utils.log("addJourney", journey.toString());
        JourneyDAOImplement dao = new JourneyDAOImplement();
        String msg = dao.insertJourney(journey);
        if (msg.contains("Error")) {
            ResponseError error = new ResponseError();
            error.setSuccess(false);
            error.setMessage(msg);
            return error;
        } else {
            ResponseInsertSuccess success = new ResponseInsertSuccess();
            success.setMessage(msg);
            success.setSuccess(true);
            return success;
        }
    }

    @RequestMapping(value = Constant.API_JOURNEY, method = RequestMethod.GET)
    public ResponseModel getListJourney() {
        Utils.log("getListJourney", "");
        JourneyDAOImplement dao = new JourneyDAOImplement();
        return dao.getList();
    }

    @RequestMapping(value = Constant.API_INSERT_JOURNEY_PLACE, method = RequestMethod.POST)
    public ResponseModel insertPlace(@RequestBody JourneyPlace place) {
        Utils.log("insertPlace", place.toString());
        JourneyDAOImplement dao = new JourneyDAOImplement();
        String msg = dao.insertJourneyPlace(place);
        if (msg.contains("Error")) {
            ResponseError error = new ResponseError();
            error.setMessage(msg);
            error.setSuccess(false);
            return error;
        } else {
            ResponseInsertSuccess success = new ResponseInsertSuccess();
            success.setSuccess(true);
            success.setMessage(msg);
            return success;
        }

    }

    @RequestMapping(value = Constant.API_JOURNEY_DETAIL, method = RequestMethod.GET)
    public ResponseModel getJourneyDetail(@RequestParam(value = "idJourney", defaultValue = "0") String idJourney) {
        Utils.log("getJourneyDetail", idJourney + "");
        JourneyDAOImplement dao = new JourneyDAOImplement();
        JourneyDetail detail = dao.getJourneyDetail(idJourney);
        ArrayList<JourneyPlaceDetail> list = dao.getPlaceList(idJourney);
        detail.setDetails(list);
        ResponseGetItemSuccess success = new ResponseGetItemSuccess();
        success.setSuccess(true);
        success.setData(detail);
        return success;
    }

    @RequestMapping(value = Constant.API_LOCATION_TYPE, method = RequestMethod.GET)
    public ResponseModel getList() {
        LocationDAOImp dao = new LocationDAOImp();
        ResponseModel model = dao.getLocationTypeList();
        return model;
    }


}

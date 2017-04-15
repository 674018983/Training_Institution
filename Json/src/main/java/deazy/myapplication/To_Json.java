package deazy.myapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by XZC on 2017/4/14.
 */

public class To_Json {

    public String  To_Json(Training_Institution training_institution){
        String result ="";
        JSONObject object = new JSONObject();
        JSONArray mjsonArray = new JSONArray();
        JSONObject mjsonObject = new JSONObject();
        try {
            mjsonObject.put("CompanyName",training_institution.getCompanyName());
            mjsonObject.put("CompanyAddress",training_institution.getCompanyAddress());
            mjsonArray.put(mjsonObject);

            object.put("Training_Institution",mjsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        result = object.toString();
        Log.e("盛开的积分", "To_Json: "+result);
        return result;
    }


}

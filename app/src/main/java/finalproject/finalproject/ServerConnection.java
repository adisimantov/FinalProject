package finalproject.finalproject;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import finalproject.finalproject.Model.Checkin;
import finalproject.finalproject.Model.Model;

/**
 * Created by adi on 02-Apr-16.
 */
public class ServerConnection {
    protected static final String TAG = "ServerConnection";

    public JSONObject prepareDataToServer(Calendar cal, double latitude, double longitude){

        Utils.TimePart currentTimePart =  Utils.TimePart.getTimePart(cal);
        Utils.TimePart nextTimePart = currentTimePart.getNext();
        Utils.TimePart prevTimePart = currentTimePart.getPrevious();
        Log.d(TAG, "prev - " + prevTimePart.name() + " curr - " + currentTimePart.name() + "  next - " + nextTimePart.name());

        List<Integer> times = new ArrayList<Integer>();
        times.add(currentTimePart.ordinal());
        times.add(nextTimePart.ordinal());
        times.add(prevTimePart.ordinal());

        Model.getInstance().getLocalCheckinAsync(new Model.GetCheckinListnListener() {
            @Override
            public void onResult(List<Checkin> checkin) {
                for (Checkin checkin1: checkin) {
                    Log.d(TAG, checkin1.getType() + " " + checkin1.getCount());
                }
            }
        },times);


        return null;
    }
}

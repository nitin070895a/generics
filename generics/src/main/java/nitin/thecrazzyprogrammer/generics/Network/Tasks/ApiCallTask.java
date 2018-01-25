package nitin.thecrazzyprogrammer.generics.Network.Tasks;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nitin.thecrazzyprogrammer.generics.Network.Helper.NetworkHelper;
import nitin.thecrazzyprogrammer.generics.Network.Listeners.ApiCallListener;

import nitin.thecrazzyprogrammer.common.R;

/**
 * Created by Nitin Khurana on 1/17/2018.
 *
 * Common task to be used in all the API(s)
 */
public class ApiCallTask extends AsyncTask<Void, Void, Void> {

    private final String KEY_RESPONSE_OBJECT = "response";
    private final String KEY_RESULT_ARRAY = "result";
    private final String KEY_RESPONSE_OBJECT_MESSAGE = "msg";
    private final String KEY_RESPONSE_OBJECT_STATUS = "status";

    private String url;
    private ApiCallListener apiCallListener;

    private boolean success = false;
    private String msg = "";
    private boolean allowEncryption = true;

    Context context;

    /**
     * Common task to be used in all the API(s)
     * @param context the context from where this task was called
     * @param url the url to be hit
     * @param apiCallListener the class where the interface methods will be defined
     */
    public ApiCallTask(Context context, String url, ApiCallListener apiCallListener) {

        this.url = url;
        this.context = context;
        this.apiCallListener = apiCallListener;

    }

    @Override
    protected Void doInBackground(Void... voids) {

        if (!NetworkHelper.isConnected(context)) {

            success = false;
            msg = context.getString(R.string.no_internet);

            return null;
        }

        try {
            JSONObject apiAnswer = new JSONObject(NetworkHelper.callApi(url/*, allowEncryption*/));

            JSONObject response = apiAnswer.getJSONObject(KEY_RESPONSE_OBJECT);
            success = Boolean.parseBoolean(response.optString(KEY_RESPONSE_OBJECT_STATUS).toLowerCase());
            msg = response.optString(KEY_RESPONSE_OBJECT_MESSAGE);

            JSONArray result = apiAnswer.getJSONArray(KEY_RESULT_ARRAY);
            //BasicFunctions.showVeryLongLog("API Result : " +  result.toString());
            apiCallListener.parseResult(result, url);

        } catch (JSONException e) {
            success = false;
            msg = context.getString(R.string.there_was_a_problem);  // server/api error
            e.printStackTrace();
        } catch (Exception e) {
            success = false;
            msg = context.getString(R.string.could_not_connect);  // null pointer exception
            e.printStackTrace();
        }

        //BasicFunctions.showLog(msg);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        apiCallListener.onApiResult(success, msg, url);
    }
}

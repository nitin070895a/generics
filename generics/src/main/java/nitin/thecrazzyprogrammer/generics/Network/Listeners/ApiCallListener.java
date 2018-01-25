package nitin.thecrazzyprogrammer.generics.Network.Listeners;

import org.json.JSONArray;

/**
 * Created by Nitin Khurana on 7/17/2017.
 *
 * The listener invoked from from API Call task
 */
public interface ApiCallListener {

    /**
     * Called after the execution of the API is completed along with the parsing
     * @param success true if api responded status = true in response JSON object
     * @param msg the message received in the API response JSON object
     * @param callingUrl the URL that was called
     */
    void onApiResult(boolean success, String msg, String callingUrl);

    /**
     * Called when the response of the API is received
     * Parsing should be done in this method
     * @param result The result array from the response of the API
     * @param callingUrl the URL that was called
     */
    void parseResult(JSONArray result, String callingUrl);
}

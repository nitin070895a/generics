package nitin.thecrazzyprogrammer.generics.Network.Helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Nitin Khurana on 1/17/2018.
 */
public class NetworkHelper {

    /**
     * Tells if Internet is available or not
     * @param context The context of the activity
     * @return true/false for internet connected/disconnected respectively
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    /**
     * Calls an non encrypted API
     * @param uri the url of the API to be called
     * @return The response from the API
     */
    public static String callApi(String uri) {

        //BasicFunctions.showLog("URL " + uri);

        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            StringBuffer output = new StringBuffer();
            while (scanner.hasNext()) {
                output.append(scanner.nextLine());
            }
            scanner.close();
            conn.disconnect();

            //BasicFunctions.showLog("API RESULT" + output.toString());
            return output.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //BasicFunctions.showLog("Error in API " + uri);
        return null;
    }
}

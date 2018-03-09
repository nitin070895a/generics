package nitin.thecrazyprogrammer.generics.Tools;

import android.content.Context;
import android.content.SharedPreferences;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Nitin Khurana on 8/30/2017.
 * <p>
 * <p>
 * <p>
 * This class tells if the time interval set by us for doing some task is expired or still left
 * <p>
 * If there's time left is will return false else true
 * <p>
 * <p>
 * <p>
 * This is to prevent any excessive calls of task, that are not needed
 * <p>
 * Suppose we want to do a task at homescreen only once a day, but the user visits the homescreen multiple times a day
 * <p>
 * This class can prevent the task from being called every time user visits the homescreen
 * <p>
 * <p>
 * <p>
 * The time interval is also flexible, it's up to you how much interval you want
 * <p>
 * <p>
 * <p>
 * Steps :
 * <p>
 * <p>
 * <p>
 * 1.) Create an instance of this class passing your context and your shared_preference name
 * <p>
 * 2.) Call the method isThisTheTimeToShow(....) passing the variable name and the interval of time
 * <p>
 * <p>
 * <p>
 * **** NOTE : Var name can be anything but you should name it accordingly so that it is unique in the shared preference that you just passed ****
 * <p>
 * **** NOTE : Once a var name is passed it should never be changed in future ****
 * <p>
 * <p>
 * <p>
 * eg : IsTimeExpired isTimeExpired = new IsTimeExpired(this, "sample_shared_pref");
 * <p>
 * if(isTimeExpired.isThisTheTimeToShow("sample_var_name", "24:00:00")))   // once a day (24 hrs)
 * <p>
 * {
 * <p>
 * // do my task
 * <p>
 * }
 */
public class IsTimeExpired {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    // first time dikhana hai k nahi
    boolean include_boundry = false;

    /**
     * @param context          Context of your activity
     * @param shared_pref_name your shared preference where you store variables
     */
    public IsTimeExpired(Context context, String shared_pref_name) {

        sharedPreferences = context.getApplicationContext().getSharedPreferences(shared_pref_name, MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    /**
     * Tells if the time interval set by us for doing some task is expired or still left
     * <p>
     * If there's time left it will return false else true
     *
     * @param var_name      The name of the variable in the shared_pref
     *                      <p>
     *                      <p>
     *                      <p>
     *                      **** NOTE : Var name can be anything but you should name it accordingly so that it is unique in the shared preference that you just passed ****
     *                      <p>
     *                      **** NOTE : Once a var name is passed it should never be changed in future ****
     * @param time_interval The time interval between which the task is not supposed to be carried out if it has been carried out once
     * @return If there's time left will return false else true
     */

    public boolean isThisTheTimeToShow(String var_name, String time_interval) {

        long current_time = System.currentTimeMillis();
        long stored_time = sharedPreferences.getLong(var_name, current_time);

        if (stored_time == current_time) {

            editor.putLong(var_name, current_time).commit();
            return include_boundry;

        } else if (((current_time - stored_time) > convertTimeToMiliSec(time_interval))) {

            if (sharedPreferences.getLong(var_name, 555) != 0)
                editor.putLong(var_name, current_time).commit();

            return true;
        }

        return false;
    }

    public void resetClock(String var_name) {

        editor.putLong(var_name, 0).commit();
    }

    public Long getValue(String var_name) {

        return sharedPreferences.getLong(var_name, System.currentTimeMillis());
    }

    /**
     * If set isThisTheTimeToShow return true for the first time
     *
     * @param include_boundry should include boundary conditions (first time)
     */
    public void setIncludeBoundry(boolean include_boundry) {

        this.include_boundry = include_boundry;
    }

    /**
     * Private method converts time string to miliseconds
     *
     * @param time Time in string format (hh:mm:ss)
     * @return the time in miliseconds
     */
    private long convertTimeToMiliSec(String time) {

        long t = 0;

        try {
            String arr[] = time.split(":");

            int h = Integer.parseInt(arr[0]);
            int m = Integer.parseInt(arr[1]);
            int s = Integer.parseInt(arr[2]);

            t += s;
            t += m * 60;
            t += h * 3600;

        } catch (Exception e) {
            t = 0;
        }

        return t * 1000;

    }

}
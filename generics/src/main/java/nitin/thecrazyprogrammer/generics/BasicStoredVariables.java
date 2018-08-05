package nitin.thecrazyprogrammer.generics;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Template for organising settings and other shared preferences
 * Created by Nitin on 26/07/18.
 */
public abstract class BasicStoredVariables {

    protected Context context;

    /**
     * Constructor
     * @param context context of your activity
     */
    public BasicStoredVariables(Context context){
        this.context = context;
    }

    /**
     * Gives the boolean value present in the shared preferences. If not present,
     * it will give the default value whose resource id was passed
     *
     * @param defSharedPrefs The shared preference to look into
     * @param id key id present in strings.xml whose value is to be found
     * @param def_id the resource id of the default value present in strings.xml
     *
     * @return The result value of the searched preference
     */
    protected boolean getBoolValue(SharedPreferences defSharedPrefs, int id, int def_id){
        return defSharedPrefs.getBoolean(context.getString(id), context.getResources().getBoolean(def_id));
    }

    /**
     * Gives the integer value present in the shared preferences. If not present,
     * it will give the default value whose resource id was passed
     *
     * @param defSharedPrefs The shared preference to look into
     * @param id key id present in strings.xml whose value is to be found
     * @param def_id the resource id of the default value present in strings.xml
     *
     * @return The result value of the searched preference
     */
    protected int getIntValue(SharedPreferences defSharedPrefs, int id, int def_id){
        return defSharedPrefs.getInt(context.getString(id), context.getResources().getInteger(def_id));
    }

    /**
     * Gives the String value present in the shared preferences. If not present,
     * it will give the default value whose resource id was passed
     *
     * @param defSharedPrefs The shared preference to look into
     * @param id key id present in strings.xml whose value is to be found
     * @param def_id the resource id of the default value present in strings.xml
     *
     * @return The result value of the searched preference
     */
    protected String getStringValue(SharedPreferences defSharedPrefs, int id, int def_id){
        return defSharedPrefs.getString(context.getString(id), String.valueOf(context.getResources().getInteger(def_id)));
    }
}

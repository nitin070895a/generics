package nitin.thecrazyprogrammer.generics.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import nitin.thecrazyprogrammer.generics.Activities.AppCompatPreferenceActivity;
import nitin.thecrazyprogrammer.generics.R;

/**
 * Created by Nitin on 15/04/18.
 */
public abstract class GenericSettingsFragment extends PreferenceFragment {

    protected Context context;
    protected boolean enableDefaultsOption = true;
    protected SharedPreferences sharedPreferencesFile;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();

        if(setPrefName() != null){
            sharedPreferencesFile = context.getSharedPreferences(setPrefName(), Context.MODE_PRIVATE);
            getPreferenceManager().setSharedPreferencesName(setPrefName());
        }

        addPreferencesFromResource(setPrefFile());

        setHasOptionsMenu(true);

        try {
            ((AppCompatPreferenceActivity)getActivity()).getSupportActionBar().setTitle(setTitle());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(enableDefaultsOption)
            inflater.inflate(R.menu.settings_menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.reset){
            final AlertDialog dialog = new AlertDialog.Builder(context, /*Colorify.getDialogTheme()*/ R.style.AppTheme_AlertDialog)
                    .setCancelable(true)
                    .setTitle(getString(R.string.apply_defaults))
                    .setMessage(getString(R.string.apply_defaults_desc))
                    .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            applyDefaults();
                        }
                    })
                    .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create();

            dialog.show();

            int accent_color = fetchAccentColor(context);
            dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(accent_color);
            dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(accent_color);
        }

        return true;
    }

    public int fetchAccentColor(Context context) {
        TypedValue typedValue = new TypedValue();

        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[] {nitin.thecrazyprogrammer.common.R.attr.colorAccent });
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }

    protected void enablePreference(Preference preference, boolean enable){
        //preference.setShouldDisableView(!enable);
        preference.setEnabled(enable);
    }

    protected void applyDefaults(){
        sharedPreferencesFile.edit().clear().apply();
        ((AppCompatPreferenceActivity)context).onBackPressed();

    }

    protected abstract int setPrefFile();
    protected abstract String setPrefName();
    protected abstract String setTitle();
}

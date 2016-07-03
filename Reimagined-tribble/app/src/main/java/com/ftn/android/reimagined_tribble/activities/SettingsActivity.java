package com.ftn.android.reimagined_tribble.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.SwitchPreference;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.model.GasStation;
import com.ftn.android.reimagined_tribble.model.Incident;
import com.ftn.android.reimagined_tribble.model.User;

import org.androidannotations.annotations.AfterPreferences;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.PreferenceByKey;
import org.androidannotations.annotations.PreferenceChange;
import org.androidannotations.annotations.PreferenceClick;
import org.androidannotations.annotations.PreferenceScreen;

import java.io.File;

/**
 * Created by szberko
 */

@PreferenceScreen(R.xml.preferences)
@EActivity
public class SettingsActivity extends AppCompatPreferenceActivity implements
        ColorChooserDialog.ColorCallback{

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private SharedPreferences preferences;
    private SharedPreferences.Editor preferencesEditor;

    @AfterPreferences
    protected void init(){
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        preferencesEditor = preferences.edit();

    }

    @PreferenceByKey(R.string.pref_key_radius)
    ListPreference radius;

    @PreferenceByKey(R.string.pref_key_purge_data_on_phone)
    Preference purgeDataOnPhone;

    @PreferenceByKey(R.string.pref_key_auto_download_pictures)
    SwitchPreference autoDownloadPictures;

    @PreferenceByKey(R.string.pref_key_colorize_gas_station)
    Preference gasStationColorPicker;

    @PreferenceByKey(R.string.pref_key_colorize_incidents)
    Preference incidentColorPicker;

    @PreferenceClick(R.string.pref_key_colorize_gas_station)
    protected void colorizeGasStations(){
        ColorPickerDialogBuilder
                .with(this)
                .setTitle(getString(R.string.pref_title_colorize_gas_station))
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        preferencesEditor.putString("gasStationColor", selectedColor + "");
                        preferencesEditor.commit();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }

    @PreferenceClick(R.string.pref_key_colorize_incidents)
    protected void colorizeIncidents(){
        ColorPickerDialogBuilder
                .with(this)
                .setTitle(getString(R.string.pref_title_colorize_incidents))
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        preferencesEditor.putString("incidentStationColor", selectedColor + "");
                        preferencesEditor.commit();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }

    @PreferenceChange(R.string.pref_key_auto_download_pictures)
    protected void autoDownloadPictures(boolean newValue){
        preferencesEditor.putBoolean("autoDownloadPictures", newValue);
        preferencesEditor.commit();
    }

    @PreferenceChange(R.string.pref_key_radius)
    protected void radius(String radius){
        preferencesEditor.putFloat("radius", Float.parseFloat(radius));
        preferencesEditor.commit();
    }

    @PreferenceClick(R.string.pref_key_purge_data_on_phone)
    protected void purgeDataOnPhone(){
        final MaterialDialog purgeDataDialog;

        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .title(R.string.questions_purge_data)
                .content(R.string.details_purge_data)
                .positiveText(R.string.positive_purge_data_answer)
                .negativeText(R.string.negative_purge_data_answer)
                .autoDismiss(true)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        purgeData();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    }
                });

        purgeDataDialog = builder.build();
        purgeDataDialog.show();

    }

    private void purgeData(){
        File cacheDirectory = getCacheDir();
        File applicationDirectory = new File(cacheDirectory.getParent());
        if (applicationDirectory.exists()) {
            String[] fileNames = applicationDirectory.list();
            for (String fileName : fileNames) {
                if (!fileName.equals("lib") && !fileName.equals("databases")) {
                    deleteFile(new File(applicationDirectory, fileName));
                }
            }
        }

        User.deleteAll(User.class);
        Incident.deleteAll(Incident.class);
        GasStation.deleteAll(GasStation.class);
        loginPrefsEditor.clear();
        loginPrefsEditor.commit();
        LoginActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP).start();
    }

    public static boolean deleteFile(File file) {
        boolean deletedAll = true;
        if (file != null) {
            if (file.isDirectory()) {
                String[] children = file.list();
                for (int i = 0; i < children.length; i++) {
                    deletedAll = deleteFile(new File(file, children[i])) && deletedAll;
                }
            } else {
                deletedAll = file.delete();
            }
        }
        return deletedAll;
    }

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {

    }

}

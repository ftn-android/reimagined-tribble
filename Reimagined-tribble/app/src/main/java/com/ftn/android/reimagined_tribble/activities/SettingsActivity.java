package com.ftn.android.reimagined_tribble.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.SwitchPreference;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.model.GasStation;
import com.ftn.android.reimagined_tribble.model.Incident;
import com.ftn.android.reimagined_tribble.model.User;

import org.androidannotations.annotations.AfterPreferences;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.PreferenceByKey;
import org.androidannotations.annotations.PreferenceClick;
import org.androidannotations.annotations.PreferenceScreen;

import java.io.File;

/**
 * Created by szberko
 */

@PreferenceScreen(R.xml.preferences)
@EActivity
public class SettingsActivity extends AppCompatPreferenceActivity {

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    @AfterPreferences
    protected void init(){
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
    }

    @PreferenceByKey(R.string.pref_key_start_gps_with_app)
    SwitchPreference startGpsWithApp;

    @PreferenceByKey(R.string.pref_key_keep_data_on_phone)
    SwitchPreference keepDataOnPhone;

    @PreferenceByKey(R.string.pref_key_purge_data_on_phone)
    Preference purgeDataOnPhone;

    @PreferenceByKey(R.string.pref_key_auto_sync)
    SwitchPreference autoSync;

    @PreferenceByKey(R.string.pref_key_auto_download_pictures)
    SwitchPreference autoDownloadPictures;

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

}

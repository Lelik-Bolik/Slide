package me.ccrama.redditslide.Fragments;

import android.app.Activity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SwitchCompat;

import me.ccrama.redditslide.R;
import me.ccrama.redditslide.SettingValues;

public class SettingsDataFragment {

    private final Activity context;

    public SettingsDataFragment(Activity context) {
        this.context = context;
    }

    public void Bind() {
        final RelativeLayout datasavingDataSaveTypeLayout = context.findViewById(R.id.settings_datasaving_datasavetype);
        final TextView datasavingLowQualityView = context.findViewById(R.id.settings_datasaving_lowquality);

        final RelativeLayout datasavingDataSaveQualityLayout = context.findViewById(R.id.settings_datasaving_datasavequality);
        final TextView datasavingCurrentModeView = context.findViewById(R.id.settings_datasaving_currentmode);

        final SwitchCompat datasavingVideoQualitySwitch = context.findViewById(R.id.settings_datasaving_videoquality);

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//* Data saving mode */
        datasavingDataSaveTypeLayout.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(context, v);
            popup.getMenuInflater().inflate(R.menu.imagequality_settings, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.never:
                        SettingValues.lowResMobile = false;
                        SettingValues.lowResAlways = false;
                        SettingValues.prefs.edit()
                                .putBoolean(SettingValues.PREF_LOW_RES_MOBILE, false)
                                .apply();
                        SettingValues.prefs.edit()
                                .putBoolean(SettingValues.PREF_LOW_RES_ALWAYS, false)
                                .apply();
                        break;
                    case R.id.mobile:
                        SettingValues.lowResMobile = true;
                        SettingValues.lowResAlways = false;
                        SettingValues.prefs.edit()
                                .putBoolean(SettingValues.PREF_LOW_RES_MOBILE, true)
                                .apply();
                        SettingValues.prefs.edit()
                                .putBoolean(SettingValues.PREF_LOW_RES_ALWAYS, false)
                                .apply();
                        break;
                    case R.id.always:
                        SettingValues.lowResMobile = true;
                        SettingValues.lowResAlways = true;
                        SettingValues.prefs.edit()
                                .putBoolean(SettingValues.PREF_LOW_RES_MOBILE, true)
                                .apply();
                        SettingValues.prefs.edit()
                                .putBoolean(SettingValues.PREF_LOW_RES_ALWAYS, true)
                                .apply();
                        break;
                }
                datasavingLowQualityView.setText(
                        SettingValues.lowResMobile ? SettingValues.lowResAlways ? context.getString(R.string.datasave_always)
                                : context.getString(R.string.datasave_mobile) : context.getString(R.string.never));
                if (datasavingLowQualityView.getText().equals(context.getString(R.string.never))) {
                    datasavingDataSaveQualityLayout.setAlpha(0.25f);
                    datasavingCurrentModeView.setText("Enable datasaving mode");
                    datasavingVideoQualitySwitch.setEnabled(false);
                } else {
                    datasavingDataSaveQualityLayout.setAlpha(1f);
                    datasavingCurrentModeView.setText(
                            SettingValues.noImages ? context.getString(R.string.never_load_images)
                                    : SettingValues.lqLow ? context.getString(R.string.load_low_quality)
                                    : SettingValues.lqMid ? context.getString(R.string.load_medium_quality)
                                    : context.getString(R.string.load_high_quality));
                    datasavingVideoQualitySwitch.setEnabled(true);
                }
                return true;
            });
            popup.show();
        });
        if (datasavingLowQualityView.getText().equals(context.getString(R.string.never))) {
            datasavingDataSaveQualityLayout.setAlpha(0.25f);
            datasavingCurrentModeView.setText("Enable datasaving mode");
        }
        //Datasaving type multi choice
        datasavingLowQualityView.setText(
                SettingValues.lowResMobile ? SettingValues.lowResAlways ? context.getString(R.string.datasave_always)
                        : context.getString(R.string.datasave_mobile)
                        : context.getString(R.string.never));

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//* Data saving quality */
        datasavingDataSaveQualityLayout.setOnClickListener(v -> {
            if (!datasavingLowQualityView.getText().equals(context.getString(R.string.never))) {
                PopupMenu popup = new PopupMenu(context, v);
                popup.getMenuInflater().inflate(R.menu.imagequality_mode, popup.getMenu());
                popup.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.never:
                            SettingValues.noImages = true;
                            SettingValues.loadImageLq = true;
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_NO_IMAGES, true)
                                    .apply();
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_IMAGE_LQ, true)
                                    .apply();
                            break;
                        case R.id.low:
                            SettingValues.noImages = false;
                            SettingValues.loadImageLq = true;
                            SettingValues.lqLow = true;
                            SettingValues.lqMid = false;
                            SettingValues.lqHigh = false;
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_NO_IMAGES, false)
                                    .apply();
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_IMAGE_LQ, true)
                                    .apply();
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_LQ_LOW, true)
                                    .apply();
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_LQ_MID, false)
                                    .apply();
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_LQ_HIGH, false)
                                    .apply();
                            break;
                        case R.id.medium:
                            SettingValues.noImages = false;
                            SettingValues.loadImageLq = true;
                            SettingValues.lqLow = false;
                            SettingValues.lqMid = true;
                            SettingValues.lqHigh = false;
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_NO_IMAGES, false)
                                    .apply();
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_IMAGE_LQ, true)
                                    .apply();
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_LQ_LOW, false)
                                    .apply();
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_LQ_MID, true)
                                    .apply();
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_LQ_HIGH, false)
                                    .apply();
                            break;
                        case R.id.high:
                            SettingValues.noImages = false;
                            SettingValues.loadImageLq = true;
                            SettingValues.lqLow = false;
                            SettingValues.lqMid = false;
                            SettingValues.lqHigh = true;
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_NO_IMAGES, false)
                                    .apply();
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_IMAGE_LQ, true)
                                    .apply();
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_LQ_LOW, false)
                                    .apply();
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_LQ_MID, false)
                                    .apply();
                            SettingValues.prefs.edit()
                                    .putBoolean(SettingValues.PREF_LQ_HIGH, true)
                                    .apply();
                            break;
                    }
                    datasavingCurrentModeView.setText(
                            SettingValues.noImages ? context.getString(R.string.never_load_images)
                                    : SettingValues.lqLow ? context.getString(R.string.load_low_quality)
                                    : SettingValues.lqMid ? context.getString(R.string.load_medium_quality)
                                    : context.getString(R.string.load_high_quality));
                    return true;
                });
                popup.show();
            }
        });
        //Image mode multi choice
        datasavingCurrentModeView.setText(SettingValues.noImages ? context.getString(R.string.never_load_images)
                : SettingValues.lqLow ? context.getString(R.string.load_low_quality)
                : SettingValues.lqMid ? context.getString(R.string.load_medium_quality)
                : context.getString(R.string.load_high_quality));

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        datasavingVideoQualitySwitch.setChecked(SettingValues.lqVideos);
        datasavingVideoQualitySwitch.setEnabled(SettingValues.lowResMobile || SettingValues.lowResAlways);
        datasavingVideoQualitySwitch.setOnCheckedChangeListener((v, checked) -> {
            SettingValues.lqVideos = checked;
            SettingValues.prefs.edit().putBoolean(SettingValues.PREF_LQ_VIDEOS, checked).apply();
        });
    }
}

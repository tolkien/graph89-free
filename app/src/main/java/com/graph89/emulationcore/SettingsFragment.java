package com.graph89.emulationcore;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceManager;

import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SeekBarPreference;

import com.Bisha.TI89Emu.R;
import com.graph89.common.CalculatorConfiguration;
import com.graph89.common.CalculatorInstance;
import com.graph89.common.CalculatorInstanceHelper;
import com.graph89.common.CalculatorTypes;
import com.graph89.common.SkinDefinition;

public class SettingsFragment extends PreferenceFragmentCompat {

    private FragmentActivity            mContext                = null;
    private CalculatorInstance          mActiveInstance			= null;
    private CalculatorInstanceHelper    mCalculatorInstances	= null;

    SharedPreferences prefs;

    public static int					MaxScreenZoom			= 1;
    public static int					DefaultScreenZoom		= 1;

    private SeekBarPreference			mHapticFeedback			= null;
    private SeekBarPreference			mTimeout				= null;

    private ListPreference				mSkinList				= null;
    private ListPreference				mSkinListV200			= null;
    private ListPreference				mSkinListTI84			= null;
    private ListPreference				mOrientationList		= null;
    private ListPreference				mLcdType				= null;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        mContext = getActivity();
        assert mContext != null;
        prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        GetActiveInstance();
        Init();

        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        mSkinList = findPreference(CalculatorConfiguration.SkinKey);
        mSkinListV200 = findPreference(CalculatorConfiguration.SkinKeyV200);
        mSkinListTI84 = findPreference(CalculatorConfiguration.SkinKeyTI84);

        mOrientationList = findPreference(CalculatorConfiguration.OrientationKey);

        mHapticFeedback = findPreference(CalculatorConfiguration.HapticFeedbackKey);
        mHapticFeedback.setMin(0);
        mTimeout = findPreference(CalculatorConfiguration.AutoOFFKey);
        mTimeout.setMin(3);
        mTimeout.setMax(31);
        mLcdType = findPreference(CalculatorConfiguration.LCDTypeKey);

        Configure();
        prefs.registerOnSharedPreferenceChangeListener(prefListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals(CalculatorConfiguration.HapticFeedbackKey))
            {
                mActiveInstance.Configuration.HapticFeedback = sharedPreferences
                        .getInt(CalculatorConfiguration.HapticFeedbackKey, 10);
            }
            else if (key.equals(CalculatorConfiguration.AudioFeedBackKey))
            {
                mActiveInstance.Configuration.AudioFeedBack = sharedPreferences
                        .getBoolean(CalculatorConfiguration.AudioFeedBackKey, false);
            }
            else if (key.equals(CalculatorConfiguration.ZoomModeKey))
            {
                mActiveInstance.Configuration.ZoomMode = sharedPreferences
                        .getBoolean(CalculatorConfiguration.ZoomModeKey, false);
            }
            else if (key.equals(CalculatorConfiguration.AutoOFFKey))
            {
                mActiveInstance.Configuration.AutoOFF = sharedPreferences
                        .getInt(CalculatorConfiguration.AutoOFFKey, 5);
            }
            else if (key.equals(CalculatorConfiguration.SkinKey))
            {
                String newValue = sharedPreferences
                        .getString(CalculatorConfiguration.SkinKey, "Default");
                mActiveInstance.Configuration.Skin = SkinDefinition
                        .StringToSkinType(newValue, mActiveInstance.CalculatorType);
                mSkinList.setSummary(sharedPreferences
                        .getString(CalculatorConfiguration.SkinKey, newValue));
            }
            else if (key.equals(CalculatorConfiguration.SkinKeyTI84))
            {
                String newValue = sharedPreferences
                        .getString(CalculatorConfiguration.SkinKeyTI84, "Classic 84");
                mActiveInstance.Configuration.Skin = SkinDefinition
                        .StringToSkinType(newValue, mActiveInstance.CalculatorType);
                mSkinListTI84.setSummary(sharedPreferences
                        .getString(CalculatorConfiguration.SkinKeyTI84, newValue));
            }
            else if (key.equals(CalculatorConfiguration.SkinKeyV200))
            {
                String newValue = sharedPreferences
                        .getString(CalculatorConfiguration.SkinKeyV200, "Classic V200");
                mActiveInstance.Configuration.Skin = SkinDefinition
                        .StringToSkinType(newValue, mActiveInstance.CalculatorType);
                mSkinListV200.setSummary(sharedPreferences
                        .getString(CalculatorConfiguration.SkinKeyV200, newValue));
            }
            else if (key.equals(CalculatorConfiguration.SaveStateOnExitKey))
            {
                mActiveInstance.Configuration.SaveStateOnExit = sharedPreferences
                        .getBoolean(CalculatorConfiguration.SaveStateOnExitKey, true);
            }
            else if (key.equals(CalculatorConfiguration.EnableGrayScaleKey))
            {
                mActiveInstance.Configuration.EnableGrayScale = sharedPreferences
                        .getBoolean(CalculatorConfiguration.EnableGrayScaleKey, false);
            }
            else if (key.equals(CalculatorConfiguration.CPUSpeedKey))
            {
                mActiveInstance.Configuration.CPUSpeed = sharedPreferences
                        .getInt(CalculatorConfiguration.CPUSpeedKey, 100);
            }
            else if (key.equals(CalculatorConfiguration.OverclockWhenBusyKey))
            {
                mActiveInstance.Configuration.OverclockWhenBusy = sharedPreferences
                        .getBoolean(CalculatorConfiguration.OverclockWhenBusyKey, true);
            }
            else if (key.equals(CalculatorConfiguration.EnergySaveKey))
            {
                mActiveInstance.Configuration.EnergySave = sharedPreferences
                        .getBoolean(CalculatorConfiguration.EnergySaveKey, true);
            }
            else if (key.equals(CalculatorConfiguration.TurnOffOnScreenOffKey))
            {
                mActiveInstance.Configuration.TurnOffOnScreenOff = sharedPreferences
                        .getBoolean(CalculatorConfiguration.TurnOffOnScreenOffKey, true);
            }
            else if (key.equals(CalculatorConfiguration.OrientationKey))
            {
                mActiveInstance.Configuration.Orientation = sharedPreferences
                        .getString(CalculatorConfiguration.OrientationKey, "Portrait");
            }
            else if (key.equals(CalculatorConfiguration.LCDTypeKey))
            {
                mActiveInstance.Configuration.UseLCDGrid = !sharedPreferences
                        .getString(CalculatorConfiguration.LCDTypeKey, "Solid")
                        .equals("Solid");

                mLcdType.setSummary(sharedPreferences
                        .getString(CalculatorConfiguration.LCDTypeKey, "Solid"));
            }

            mCalculatorInstances.Save();
        }
    };

    @Override
    public void onResume()
    {
        super.onResume();

        mSkinList.setSummary(prefs.getString(
                CalculatorConfiguration.SkinKey, "Default"));
        mSkinListV200.setSummary(prefs.getString(
                CalculatorConfiguration.SkinKeyV200, "Classic V200"));
        mSkinListTI84.setSummary(prefs.getString(
                CalculatorConfiguration.SkinKeyTI84, "Classic 84"));
        mOrientationList.setSummary(prefs.getString(
                CalculatorConfiguration.OrientationKey, "Portrait"));
        mLcdType.setSummary(prefs.getString(
                CalculatorConfiguration.LCDTypeKey, "Solid"));

        prefs.registerOnSharedPreferenceChangeListener(prefListener);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        //prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy()
    {
        //prefs.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }

    private void Init()
    {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(CalculatorConfiguration.HapticFeedbackKey,
                mActiveInstance.Configuration.HapticFeedback);
        editor.putBoolean(CalculatorConfiguration.AudioFeedBackKey,
                mActiveInstance.Configuration.AudioFeedBack);
        editor.putBoolean(CalculatorConfiguration.ZoomModeKey,
                mActiveInstance.Configuration.ZoomMode);
        editor.putInt(CalculatorConfiguration.ScreenScaleKey,
                mActiveInstance.Configuration.ScreenScale);
        editor.putInt(CalculatorConfiguration.AutoOFFKey,
                mActiveInstance.Configuration.AutoOFF);

        if (mActiveInstance.CalculatorType == CalculatorTypes.TI89 ||
                mActiveInstance.CalculatorType == CalculatorTypes.TI89T)
        {
            editor.putString(CalculatorConfiguration.SkinKey,
                    SkinDefinition.SkinTypeToString(mActiveInstance.Configuration.Skin,
                            mActiveInstance.CalculatorType));
            editor.putString(CalculatorConfiguration.OrientationKey,
                    mActiveInstance.Configuration.Orientation);
        }
        else if (CalculatorTypes.isTilem(mActiveInstance.CalculatorType))
        {
            editor.putString(CalculatorConfiguration.SkinKeyTI84,
                    SkinDefinition.SkinTypeToString(mActiveInstance.Configuration.Skin,
                            mActiveInstance.CalculatorType));
            editor.putString(CalculatorConfiguration.OrientationKey,
                    mActiveInstance.Configuration.Orientation);
        }
        else
        {
            editor.putString(CalculatorConfiguration.SkinKeyV200,
                    SkinDefinition.SkinTypeToString(mActiveInstance.Configuration.Skin,
                            mActiveInstance.CalculatorType));
        }
        editor.putInt(CalculatorConfiguration.CPUSpeedKey,
                mActiveInstance.Configuration.CPUSpeed);
        editor.putBoolean(CalculatorConfiguration.OverclockWhenBusyKey,
                mActiveInstance.Configuration.OverclockWhenBusy);
        editor.putBoolean(CalculatorConfiguration.EnergySaveKey
                , mActiveInstance.Configuration.EnergySave);
        editor.putBoolean(CalculatorConfiguration.SaveStateOnExitKey,
                mActiveInstance.Configuration.SaveStateOnExit);
        editor.putBoolean(CalculatorConfiguration.EnableGrayScaleKey,
                mActiveInstance.Configuration.EnableGrayScale);
        editor.putBoolean(CalculatorConfiguration.TurnOffOnScreenOffKey,
                mActiveInstance.Configuration.TurnOffOnScreenOff);

        editor.putString(CalculatorConfiguration.LCDTypeKey,
                mActiveInstance.Configuration.UseLCDGrid ? "Dot Matrix" : "Solid");

        editor.apply();
    }

    private void Configure()
    {
        Preference c = findPreference("CONFIG_TITLE");
        assert c != null;
        c.setTitle("Configuration - \"" + mActiveInstance.Title + "\"");

        PreferenceGroup d = findPreference("CONFIG_CAT_DISPLAY_SETTINGS");
        assert d != null;

        if (mActiveInstance.CalculatorType == CalculatorTypes.TI89 ||
                mActiveInstance.CalculatorType == CalculatorTypes.TI89T)
        {
            d.removePreference(mSkinListV200);
            d.removePreference(mSkinListTI84);
        }
        else if (CalculatorTypes.isTilem(mActiveInstance.CalculatorType))
        {
            d.removePreference(mSkinListV200);
            d.removePreference(mSkinList);
        }
        else if (mActiveInstance.CalculatorType == CalculatorTypes.TI92 ||
                mActiveInstance.CalculatorType == CalculatorTypes.TI92PLUS ||
                mActiveInstance.CalculatorType == CalculatorTypes.V200) {
            d.removePreference(mSkinList);
            d.removePreference(mSkinListTI84);
            d.removePreference(mOrientationList);
        }
    }

    private void GetActiveInstance()
    {
        mCalculatorInstances = new CalculatorInstanceHelper(mContext);
        int ID = mCalculatorInstances.GetLastUsedInstanceID();

        mActiveInstance = mCalculatorInstances.GetByIndex(ID);
    }
}

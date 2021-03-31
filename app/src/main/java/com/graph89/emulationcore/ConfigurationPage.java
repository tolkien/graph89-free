package com.graph89.emulationcore;

import android.os.Bundle;
import android.view.KeyEvent;
import androidx.appcompat.app.AppCompatActivity;
import com.Bisha.TI89Emu.R;

public class ConfigurationPage extends AppCompatActivity {

    public static final String			CONFIG_NAME				= "Graph89";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(EmulatorActivity.Orientation);
        setContentView(R.layout.settings);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_BACK:
            case KeyEvent.KEYCODE_HOME:
                finish();
                break;
        }

        return super.onKeyDown(keyCode, event);
    }
}

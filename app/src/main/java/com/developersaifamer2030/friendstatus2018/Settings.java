package com.developersaifamer2030.friendstatus2018;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.developersaifamer2030.friendstatus2018.NotificationOnlin.MyToken;

public class Settings extends AppCompatActivity {

    private Switch myswitch;
    SharedPref sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpref = new SharedPref(this);
        if (sharedpref.loadNightModeState() == true) {
            setTheme(R.style.darktheme);
        } else setTheme(R.style.AppTheme);

        //ــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــ

        setContentView(R.layout.activity_settings);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final TextView mod = (TextView) findViewById(R.id.mod);
        mod.setText(R.string.enable_dark);
        myswitch = (Switch) findViewById(R.id.myswitch);
        if (sharedpref.loadNightModeState() == true) {
            myswitch.setChecked(true);
            mod .setText(R.string.enable_night);
        }else {
            mod .setText(R.string.enable_dark);
        }
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedpref.setNightModeState(true);
                    mod.setText(R.string.enable_night);

                    restartApp();
                } else {
                    sharedpref.setNightModeState(false);
                    restartApp();
                    mod.setText(R.string.enable_night);
                }
            }
        });
    }

    public void restartApp() {
        Intent i = new Intent(getApplicationContext(), HomeStatus.class);
        startActivity(i);
        finish();
    }

}

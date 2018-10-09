package com.developersaifamer2030.friendstatus2018;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.developersaifamer2030.friendstatus2018.Parmission.ParmissionActivity;
import com.developersaifamer2030.friendstatus2018.adapter.RecyclerViewMediaAdapter;

import java.io.File;
import java.util.ArrayList;

public class HomeStatus extends ParmissionActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String WHATSAPP_STATUSES_LOCATION = "/WhatsApp/Media/.Statuses";
    private RecyclerView mRecyclerViewMediaList;
    private LinearLayoutManager mLinearLayoutManager;
    public static final String TAG = "Home";
    SharedPref sharedpref;
    TextView textView;
    Animation animation;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpref = new SharedPref(this);
        if (sharedpref.loadNightModeState() == true) {
            setTheme(R.style.darktheme);
        } else setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_home_status);


        animation = AnimationUtils.loadAnimation(HomeStatus.this, R.anim.fade_out);
        textView = (TextView) findViewById(R.id.textView2);
        textView.setAnimation(animation);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        // getSupportActionBar().setIcon(R.drawable.logo_512);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mRecyclerViewMediaList = (RecyclerView) findViewById(R.id.recyclerViewMedia);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerViewMediaList.setLayoutManager(gridLayoutManager);
        RecyclerViewMediaAdapter recyclerViewMediaAdapter = new RecyclerViewMediaAdapter
                (this.getListFiles(new File(Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + WHATSAPP_STATUSES_LOCATION)), HomeStatus.this);
        mRecyclerViewMediaList.setAdapter(recyclerViewMediaAdapter);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    final Fragment[] selectedFragment = {null};

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            startActivity(new Intent(HomeStatus.this, HomeStatus.class));
                            break;
                        case R.id.navigation_Settings:
                            startActivity(new Intent(HomeStatus.this, Settings.class));

                            break;
                        case R.id.navigation_Privacy_Policy:
                            startActivity(new Intent(HomeStatus.this, PrivacyPolicy.class));

                            break;
                    }

                    return true;
                }
            };

    public ArrayList<File> getListFiles(File parentDir) {

        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files;
        files = parentDir.listFiles();
        if (files != null) {

            for (File file : files) {
                if (file.getName().endsWith(".jpg") ||
                        file.getName().endsWith(".gif") ||
                        file.getName().endsWith(".mp4")) {
                    if (!inFiles.contains(file))
                        inFiles.add(file);
                    textView.setText(" ");

                    // Toast.makeText(this, R.string.show_status, Toast.LENGTH_LONG).show();
                }


            }
        }
        return inFiles;
    }


    @Override
    public void onRefresh() {

        RecyclerViewMediaAdapter.clearSelectedStatuses();
    }

}

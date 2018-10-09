package com.developersaifamer2030.friendstatus2018.NotificationOnlin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import com.developersaifamer2030.friendstatus2018.R;
import com.google.firebase.iid.FirebaseInstanceId;


public class MyToken extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_token);
        String s =("MY Token :"+ FirebaseInstanceId.getInstance().getToken());
        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(s);
    }
}

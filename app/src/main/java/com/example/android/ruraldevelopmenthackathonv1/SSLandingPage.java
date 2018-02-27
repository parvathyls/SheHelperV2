package com.example.android.ruraldevelopmenthackathonv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by psivapra on 2/18/2018.
 */

public class SSLandingPage   extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ss_landingpage);

    }
    public void RegisterParents(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();
        if(button_text.equals("Register Parents"))
        {
            Intent registerParents = new Intent(this,RegisterParents.class);
            startActivity(registerParents);
        }

    }
    public void FetchRecords(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();
        if(button_text.equals("Search for Records"))
        {
            Intent admin = new Intent(this,FetchRecords.class);
            startActivity(admin);
        }

    }
    public void Reports(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();
        if(button_text.equals("Reports"))
        {
            Intent reports = new Intent(this,SSReports.class);
            startActivity(reports);
        }

    }
}

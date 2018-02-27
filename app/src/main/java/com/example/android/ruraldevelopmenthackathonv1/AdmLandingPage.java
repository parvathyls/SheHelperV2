package com.example.android.ruraldevelopmenthackathonv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by psivapra on 2/26/2018.
 */

public class AdmLandingPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm_landingpage);
    }
    public void RegisterSS(View view){
        Intent registerSS = new Intent(this,MainActivity.class);
        startActivity(registerSS);
    }
    public void AdminReports(View view){
        Intent admReports = new Intent(this,AdmReports.class);
        startActivity(admReports);
    }
}

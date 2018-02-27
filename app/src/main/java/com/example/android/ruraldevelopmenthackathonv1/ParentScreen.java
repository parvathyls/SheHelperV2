package com.example.android.ruraldevelopmenthackathonv1;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import static android.view.View.GONE;

/**
 * Created by psivapra on 2/26/2018.
 */

public class ParentScreen extends AppCompatActivity {
    UserSessionManager session;
    static String parent_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_screen);
        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String email = user.get(UserSessionManager.KEY_EMAIL);
        String apptmt_dt="";
        LinearLayout babyLayout=(LinearLayout) findViewById(R.id.rd_ss_babydetails_layout);
        RelativeLayout duedateLayout=(RelativeLayout)findViewById(R.id.due_dt_layout);
        RelativeLayout motherapptmtLayout=(RelativeLayout)findViewById(R.id.mother_current_apptmt_layout);
        SQLiteDatabase newDB = new SampleDBContract.SampleDBSQLiteHelper(this).getWritableDatabase();
        Cursor c = newDB.rawQuery("SELECT parent_id,mother_name,due_dt,next_apptmt_dt FROM rd_parent where par_email='"+email+"'", null);
        if (c != null ) {
            if (c.moveToFirst()) {
                do {
                    parent_id=c.getString(c.getColumnIndex("parent_id"));
                    TextView motherName = (TextView) findViewById(R.id.rd_ss_mother_name);
                    motherName.setText(c.getString(c.getColumnIndex("mother_name")));
                    Cursor c1 = newDB.rawQuery("SELECT baby_name,baby_nxt_apptmt FROM rd_baby where parent_id='"+parent_id+"'", null);
                    if (c1.getCount()<=0){
                        babyLayout.setVisibility(GONE);
                        duedateLayout.setVisibility(View.VISIBLE);
                        TextView due_dt=(TextView) findViewById(R.id.rd_ss_due_dt);
                        due_dt.setText(c.getString(c.getColumnIndex("due_dt")));
                        motherapptmtLayout.setVisibility(View.VISIBLE);
                        TextView mother_appt_dt=(TextView) findViewById(R.id.rd_ss_mother_apptmt_dt);
                        mother_appt_dt.setText(c.getString(c.getColumnIndex("next_apptmt_dt")));
                        apptmt_dt=c.getString(c.getColumnIndex("next_apptmt_dt"));
                    }else{
                        babyLayout.setVisibility(View.VISIBLE);
                        duedateLayout.setVisibility(View.GONE);
                        motherapptmtLayout.setVisibility(View.GONE);
                        if (c1 != null ) {
                            if (c1.moveToFirst()) {
                                do {
                                    TextView babyName = (TextView) findViewById(R.id.rd_ss_baby_name);
                                    babyName.setText(c1.getString(c1.getColumnIndex("baby_name")));
                                    TextView babyApptmtdt = (TextView) findViewById(R.id.rd_ss_baby_apptmt_dt);
                                    babyApptmtdt.setText(c1.getString(c1.getColumnIndex("baby_nxt_apptmt")));
                                    apptmt_dt=c1.getString(c1.getColumnIndex("baby_nxt_apptmt"));
                                }while (c1.moveToNext());
                            }
                        }
                    }
                } while (c.moveToNext());
            }
        }
        AddNotification(apptmt_dt);
        final EditText searchBox = (EditText) findViewById(R.id.rd_ss_searchbox);
        searchBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (searchBox.getRight() - searchBox.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        //  Toast.makeText(null, "Clicked", Toast.LENGTH_LONG).show();
                        Log.e(getClass().getSimpleName(), "Opening results in browser");
                        String query="video for " + searchBox.getText().toString();
                        String escapedQuery = null;
                        try {
                            escapedQuery = URLEncoder.encode(query, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        Uri uri = Uri.parse("http://www.google.com/#q=" + escapedQuery);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        return true;
                    }
                }
                return false;
            }
        });
    }
    public void CallSS(View view){
        int checkPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:9980855766"));
        if (checkPermission == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent);
        }
    }
    public void AddNotification(String apptmt_dt){
        final Intent emptyIntent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, emptyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_menu_manage)
                        .setContentTitle("Appointment Alert")
                        .setContentText("Your next appointment is on "+apptmt_dt)
                        .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());
    }

}

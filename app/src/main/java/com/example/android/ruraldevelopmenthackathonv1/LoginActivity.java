package com.example.android.ruraldevelopmenthackathonv1;

/**
 * Created by psivapra on 2/26/2018.
 */

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    Button btnLogin;

    EditText txtUsername, txtPassword;

    // User Session Manager Class
    UserSessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // User Session Manager
        session = new UserSessionManager(getApplicationContext());

        // get Email, Password input text
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        final String s = ((RDGlobal) this.getApplication()).getCurrentRole();
        Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();


        // User Login button
        btnLogin = (Button) findViewById(R.id.btnLogin);


        // Login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Get username, password from EditText
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                String savedPassword="";
                // Validate if username, password is filled
                if (username.trim().length() > 0 && password.trim().length() > 0) {
                    if(s.equals("Admin")) {
                        savedPassword = getAdminPassword(username);
                    }
                    if(s.equals("SS")) {
                        savedPassword = getSSPassword(username);
                    }
                    if(s.equals("Parent")) {
                        savedPassword = getParentPassword(username);
                    }

                    if (savedPassword.equals(password)) {

                        // Creating user login session
                        // Statically storing name="Android Example"
                        // and email="androidexample84@gmail.com"
                        if(s.equals("Admin")) {
                            session.createUserLoginSession("Admin",
                                    username);

                            // Starting MainActivity
                            Intent i = new Intent(getApplicationContext(), AdmLandingPage.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            // Add new Flag to start new Activity
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);

                            finish();
                        }
                        if(s.equals("SS")) {
                            session.createUserLoginSession("Swasthya Saathi",
                                    username);

                            // Starting MainActivity
                            Intent i = new Intent(getApplicationContext(), SSLandingPage.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            // Add new Flag to start new Activity
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);

                            finish();
                        }
                        if(s.equals("Parent")) {
                            session.createUserLoginSession("Parent",
                                    username);

                            // Starting MainActivity
                            Intent i = new Intent(getApplicationContext(), ParentScreen.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            // Add new Flag to start new Activity
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);

                            finish();
                        }
                    } else {

                        // username / password doesn't match&
                        Toast.makeText(getApplicationContext(),
                                "Username/Password is incorrect",
                                Toast.LENGTH_LONG).show();

                    }
                } else {

                    // user didn't entered username or password
                    Toast.makeText(getApplicationContext(),
                            "Please enter username and password",
                            Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    public String getAdminPassword(String admemail) {
        SQLiteDatabase newDB = new SampleDBContract.SampleDBSQLiteHelper(this).getWritableDatabase();
        String password="";
        Cursor c = newDB.rawQuery("SELECT password FROM rd_admin where email='" + admemail + "'", null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    password = c.getString(c.getColumnIndex("password"));
                } while (c.moveToNext());

            }
        }
        return password;
    }

    public String getSSPassword(String ssemail) {
        SQLiteDatabase newDB = new SampleDBContract.SampleDBSQLiteHelper(this).getWritableDatabase();
        String password="";
        Cursor c = newDB.rawQuery("SELECT ss_password FROM rd_ss_person where ss_email='" + ssemail + "'", null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    password = c.getString(c.getColumnIndex("ss_password"));
                } while (c.moveToNext());

            }
        }
        return password;
    }
    public String getParentPassword(String parentemail){
        SQLiteDatabase newDB = new SampleDBContract.SampleDBSQLiteHelper(this).getWritableDatabase();
        String password="";
        Cursor c = newDB.rawQuery("SELECT par_password FROM rd_parent where par_email='" + parentemail + "'", null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    password = c.getString(c.getColumnIndex("par_password"));
                } while (c.moveToNext());

            }
        }
        return password;
    }

}
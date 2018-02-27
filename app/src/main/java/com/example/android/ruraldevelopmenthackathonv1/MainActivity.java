package com.example.android.ruraldevelopmenthackathonv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnItemSelectedListener {
    private Spinner spinner;
    private static final String[] paths = {"English", "Hindi", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*Parvathy*/
        spinner = (Spinner) findViewById(R.id.rd_adm_ss_language);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private static final String TAG = "MainActivity";


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void SaveSS(View view) {
        Long newRowId;
        TextView viewSSID = (TextView) findViewById(R.id.rd_adm_ssid);

        SQLiteDatabase database = new SampleDBContract.SampleDBSQLiteHelper(this).getWritableDatabase();

        ContentValues values = new ContentValues();
        EditText editSSName = (EditText) findViewById(R.id.rd_adm_ss_name);
        values.put(SampleDBContract.SSPerson.SSName, editSSName.getText().toString());
        EditText editSSEmail = (EditText) findViewById(R.id.rd_adm_ss_email);
        values.put(SampleDBContract.SSPerson.SSEmail, editSSEmail.getText().toString());
        EditText editSSPhone = (EditText) findViewById(R.id.rd_adm_ss_phone);
        values.put(SampleDBContract.SSPerson.SSPhone, editSSPhone.getText().toString());
        EditText editSSPassword = (EditText) findViewById(R.id.rd_adm_ss_password);
        values.put(SampleDBContract.SSPerson.SSPassword, editSSPassword.getText().toString());
        Spinner editSSLang = (Spinner) findViewById(R.id.rd_adm_ss_language);
        values.put(SampleDBContract.SSPerson.SSLang, editSSLang.getSelectedItem().toString());
        EditText editSSVillage = (EditText) findViewById(R.id.rd_adm_ss_village);
        values.put(SampleDBContract.SSPerson.SSVillage, editSSVillage.getText().toString());
        EditText editSSPostal = (EditText) findViewById(R.id.rd_adm_ss_postalcode);
        values.put(SampleDBContract.SSPerson.SSPostal, editSSPostal.getText().toString());


        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(Calendar.getInstance().getTime());
            long date = calendar.getTimeInMillis();
            values.put(SampleDBContract.SSPerson.SSAddedDate, date);
        } catch (Exception e) {
            Log.e(TAG, "Error", e);
            Toast.makeText(this, "Date is in the wrong format", Toast.LENGTH_LONG).show();
            return;
        }
        if (viewSSID.getText().toString() == "") {
            newRowId = database.insert(SampleDBContract.SSPerson.TABLE_NAME, null, values);
            viewSSID.setText(Objects.toString(newRowId, null));
            Toast.makeText(this, "The new Row Id is " + newRowId, Toast.LENGTH_LONG).show();
        } else {
            database.update(SampleDBContract.SSPerson.TABLE_NAME, values, "SS_ID=" + viewSSID.getText().toString(), null);
            Toast.makeText(this, "Row Id:" + viewSSID.getText().toString() + " updated", Toast.LENGTH_LONG).show();
        }

    }
    public void ClearSS(View view) {
        Long newRowId;
        TextView viewSSID = (TextView) findViewById(R.id.rd_adm_ssid);
        viewSSID.setText("");
        EditText editSSName = (EditText) findViewById(R.id.rd_adm_ss_name);
        editSSName.setText("");
        EditText editSSEmail = (EditText) findViewById(R.id.rd_adm_ss_email);
        editSSEmail.setText("");
        EditText editSSPhone = (EditText) findViewById(R.id.rd_adm_ss_phone);
        editSSPhone.setText("");
        EditText editSSPassword = (EditText) findViewById(R.id.rd_adm_ss_password);
        editSSPassword.setText("");
        Spinner editSSLang = (Spinner) findViewById(R.id.rd_adm_ss_language);
        editSSLang.setSelection(0);
        EditText editSSVillage = (EditText) findViewById(R.id.rd_adm_ss_village);
        editSSVillage.setText("");
        EditText editSSPostal = (EditText) findViewById(R.id.rd_adm_ss_postalcode);
        editSSPostal.setText("");
    }
}

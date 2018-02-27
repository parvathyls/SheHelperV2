package com.example.android.ruraldevelopmenthackathonv1;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static android.view.View.GONE;

/**
 * Created by psivapra on 2/25/2018.
 */


public class FetchRecords extends AppCompatActivity {
    private Context context;
    protected void onCreate(Bundle savedInstanceState)

    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fetch_records);
        context = this;
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
                        Log.e(getClass().getSimpleName(), "Clicked");
                        openAndQueryDatabase();
                        displayResultList();
                        return true;
                    }
                }
                return false;
            }
        });
        RecordClick(null);

    }

        private ArrayList<String> results = new ArrayList<String>();
        private ArrayList<String> arrparent_id = new ArrayList<String>();

    private void openAndQueryDatabase() {
        EditText searchBox1 = (EditText) findViewById(R.id.rd_ss_searchbox);
        try {
            SQLiteDatabase newDB = new SampleDBContract.SampleDBSQLiteHelper(this).getWritableDatabase();
            Cursor c = newDB.rawQuery("SELECT parent_id,mother_name,par_village FROM rd_parent where mother_name like '%"+searchBox1.getText()+"%'", null);

            if (c != null ) {
                Log.e(getClass().getSimpleName(), "1");
                if  (c.moveToFirst()) {
                    Log.e(getClass().getSimpleName(), "2");
                    do {
                        Log.e(getClass().getSimpleName(), "3");
                        String firstName = c.getString(c.getColumnIndex("mother_name"));
                        String village =  c.getString(c.getColumnIndex("par_village"));
                        results.add("Name: " + firstName + "\t Village: " + village);
                        arrparent_id.add(c.getString(c.getColumnIndex("parent_id")));
                    }while (c.moveToNext());
                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }

    }
    private void displayResultList() {
        TextView tView = new TextView(this);
        tView.setText("Results");
        ListView resultView=(ListView)findViewById(R.id.rd_ss_results);
        resultView.addHeaderView(tView);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results);
        resultView.setAdapter(arrayAdapter);
        //setListAdapter(new ArrayAdapter<String>(this,
          //      android.R.layout.simple_list_item_1, results));
        //resultView.setTextFilterEnabled(true);

    }
private static String baby_id;
    private static String mother_id;
    public void RecordClick (View view) {
        ListView lv=(ListView)findViewById(R.id.rd_ss_results);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
//                Toast.makeText(getApplicationContext(),
  //                      "Click ListItem Number " + position, Toast.LENGTH_LONG)
    //                    .show();
                TextView parent_id=(TextView)findViewById(R.id.rd_ss_recparent_id);
                //SQLiteDatabase newDB1 = new SampleDBContract.SampleDBSQLiteHelper(this).getWritableDatabase();
                parent_id.setText(arrparent_id.get(position-1));
                mother_id=arrparent_id.get(position-1);
                try {
                    TextView parent_id1=(TextView)findViewById(R.id.rd_ss_recparent_id);
                    SQLiteDatabase newDB = new SampleDBContract.SampleDBSQLiteHelper(context).getWritableDatabase();
                    Cursor c = newDB.rawQuery("SELECT mother_name,mother_age,risk_status,due_dt,next_apptmt_dt FROM rd_parent where parent_id='"+parent_id1.getText()+"'", null);
                    Cursor c1 = newDB.rawQuery("SELECT baby_id,baby_name,baby_dob,baby_health_sts,baby_nxt_apptmt FROM rd_baby where parent_id='"+parent_id1.getText()+"'", null);
                    LinearLayout detailsLayout=(LinearLayout) findViewById(R.id.rd_ss_detailLayout);
                    detailsLayout.setVisibility(View.VISIBLE);
                    LinearLayout fetchRecordsLayout=(LinearLayout) findViewById(R.id.rd_ss_fetchrecords);
                    fetchRecordsLayout.setVisibility(GONE);
                    TextView motherStatuslbl=(TextView) findViewById(R.id.rd_ss_recmother_sts_lbl);
                    TextView motherStatus=(TextView) findViewById(R.id.rd_ss_recmother_status);
                    TextView dueDatelbl=(TextView) findViewById(R.id.rd_ss_recdue_dt_label);
                    TextView dueDate=(TextView) findViewById(R.id.rd_ss_recdue_dt);
                    LinearLayout babyLayout=(LinearLayout) findViewById(R.id.rd_ss_babydetails_layout);
                    RelativeLayout babyNextApptLayout= (RelativeLayout) findViewById(R.id.rd_next_apptmt);
                    babyNextApptLayout.setVisibility(GONE);
                    Button babyNextApptButton=(Button) findViewById(R.id.rd_ss_babySetNextApptmt);
                    babyNextApptButton.setVisibility(GONE);

                    if (c != null ) {
                        if  (c.moveToFirst()) {
                            do {
                                TextView motherName=(TextView) findViewById(R.id.rd_ss_recmother_name);
                                motherName.setText(c.getString(c.getColumnIndex("mother_name")));
                                TextView motherAge=(TextView) findViewById(R.id.rd_ss_recmother_age);
                                motherAge.setText(c.getString(c.getColumnIndex("mother_age")));
                                if (c1.getCount()<=0){

                                motherStatus.setText(c.getString(c.getColumnIndex("risk_status")));

                                dueDate.setText(c.getString(c.getColumnIndex("due_dt")));
                                    TextView motherCurrentAppointment=(TextView) findViewById(R.id.rd_ss_mother_current_apptmt_dt);
                                    motherCurrentAppointment.setText(c.getString(c.getColumnIndex("next_apptmt_dt")));
                                    RelativeLayout motherNextApptLayout= (RelativeLayout) findViewById(R.id.rd_mother_next_apptmt);
                                    motherNextApptLayout.setVisibility(View.GONE);
                                    Button motherNextApptButton=(Button) findViewById(R.id.rd_ss_motherSetNextApptmt);
                                    motherNextApptButton.setVisibility(View.GONE);
                                babyLayout.setVisibility(GONE);
                                }
                                else {
                                    motherStatuslbl.setVisibility(GONE);
                                    motherStatus.setVisibility(GONE);
                                    dueDate.setVisibility(GONE);
                                    dueDatelbl.setVisibility(GONE);

                                    RelativeLayout motherCurrentAppointmentLayout=(RelativeLayout)findViewById(R.id.mother_current_apptmt_layout);
                                    motherCurrentAppointmentLayout.setVisibility(GONE);
                                    Button MarkApptmt=(Button) findViewById(R.id.rd_ss_motherMarkApptmt);
                                    MarkApptmt.setVisibility(View.GONE);
                                    RelativeLayout motherNextApptLayout= (RelativeLayout) findViewById(R.id.rd_mother_next_apptmt);
                                    motherNextApptLayout.setVisibility(View.GONE);
                                    Button motherNextApptButton=(Button) findViewById(R.id.rd_ss_motherSetNextApptmt);
                                    motherNextApptButton.setVisibility(View.GONE);

                                    babyLayout.setVisibility(View.VISIBLE);
                                    if(c1.moveToFirst()){
                                        do{
                                            TextView babyName=(TextView) findViewById(R.id.rd_ss_recbaby_name);
                                            babyName.setText(c1.getString(c1.getColumnIndex("baby_name")));
                                            TextView babyAge=(TextView) findViewById(R.id.rd_ss_baby_age);
                                            if(c1.getString(c1.getColumnIndex("baby_dob"))!=""){
                                                babyAge.setText(calculateAgeInMonths(c1.getString(c1.getColumnIndex("baby_dob"))) + " months");
                                            }
                                            baby_id=c1.getString(c1.getColumnIndex("baby_id"));
                                            TextView babyHealthStatus=(TextView) findViewById(R.id.rd_ss_recbaby_status);
                                            babyHealthStatus.setText(c1.getString(c1.getColumnIndex("baby_health_sts")));
                                            TextView babyCurrentAppointment=(TextView) findViewById(R.id.rd_ss_current_apptmt_dt);
                                            babyCurrentAppointment.setText(c1.getString(c1.getColumnIndex("baby_nxt_apptmt")));

                                            babyNextApptLayout.setVisibility(View.GONE);
                                            babyNextApptButton.setVisibility(View.GONE);
                                        }while (c1.moveToNext());
                                    }
                                }


                            }while (c.moveToNext());
                        }
                    }
                } catch (SQLiteException se ) {
                    Log.e(getClass().getSimpleName(), "Could not create or Open the database");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                /*Intent intent = new Intent(MainActivity.this, SendMessage.class);
                String message = "abc";
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);*/
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public int calculateAgeInMonths(String agestr) throws ParseException {
        Log.e(getClass().getSimpleName(), agestr);
        /*String birthday=agestr.split("/")[1];
        String birthmonth=agestr.split("/")[2];
        String birthyear=agestr.split("/")[3];*/
        //LocalDate birthdate=LocalDate.of(Integer.parseInt(birthyear), Integer.parseInt(birthmonth), Integer.parseInt(birthday));
     /*   LocalDate today=LocalDate.now();
        Date birthdate=new SimpleDateFormat("dd/MM/yyyy").parse(agestr);
        LocalDate birthdate1 = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Period p = Period.between(birthdate1, today);*/
        //return p.getMonths();

        return 6;

    }
    static Long ApptmtID;
    public void MarkBabyApptmtDone(View view)
    {
        Long newRowId;
        SQLiteDatabase database = new SampleDBContract.SampleDBSQLiteHelper(this).getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SampleDBContract.BabyApptmt.apptmt_baby_id, baby_id);

        TextView CurrentApptDate=(TextView) findViewById(R.id.rd_ss_current_apptmt_dt);
        values.put(SampleDBContract.BabyApptmt.baby_apptmt_dt, CurrentApptDate.getText().toString());
        values.put(SampleDBContract.BabyApptmt.baby_apptmt_done, "Y");
        newRowId = database.insert(SampleDBContract.BabyApptmt.BabyApptmtTbl, null, values);
        ApptmtID=newRowId;
        Toast.makeText(this, " Appointment set to done. ApptmtID " + newRowId, Toast.LENGTH_LONG).show();
        RelativeLayout babyCurrentApptLayout= (RelativeLayout) findViewById(R.id.current_apptmt_layout);
        babyCurrentApptLayout.setVisibility(View.GONE);
        Button babyCurrentApptButton=(Button) findViewById(R.id.rd_ss_babyMarkApptmt);
        babyCurrentApptButton.setVisibility(View.GONE);
        RelativeLayout babyNextApptLayout= (RelativeLayout) findViewById(R.id.rd_next_apptmt);
        babyNextApptLayout.setVisibility(View.VISIBLE);
        Button babyNextApptButton=(Button) findViewById(R.id.rd_ss_babySetNextApptmt);
        babyNextApptButton.setVisibility(View.VISIBLE);
    }
    public void BabySetNextAppointment(View view)
    {
        SQLiteDatabase database = new SampleDBContract.SampleDBSQLiteHelper(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        EditText nextApptDt=(EditText)findViewById(R.id.rd_ss_next_apptmt_dt);
        values.put(SampleDBContract.BabyDB.baby_nxt_apptmt, nextApptDt.getText().toString());
        database.update(SampleDBContract.BabyDB.BabyTbl, values, "baby_id=" + baby_id , null);
        Toast.makeText(this, " Next Appointment date updated.", Toast.LENGTH_LONG).show();
        nextApptDt.setEnabled(false);
        nextApptDt.setInputType(InputType.TYPE_NULL);
        Button babyNextApptButton=(Button) findViewById(R.id.rd_ss_babySetNextApptmt);
        babyNextApptButton.setEnabled(false);
    }
    public void MarkMotherApptmtDone(View view)
    {
        Long newRowId;
        SQLiteDatabase database = new SampleDBContract.SampleDBSQLiteHelper(this).getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SampleDBContract.MotherApptmt.apptmt_parent_id, mother_id);

        TextView CurrentApptDate=(TextView) findViewById(R.id.rd_ss_mother_current_apptmt_dt);
        values.put(SampleDBContract.MotherApptmt.mother_apptmt_dt, CurrentApptDate.getText().toString());
        values.put(SampleDBContract.MotherApptmt.mother_apptmt_done, "Y");
        newRowId = database.insert(SampleDBContract.MotherApptmt.MotherApptmtTbl, null, values);
        ApptmtID=newRowId;
        Toast.makeText(this, " Appointment set to done. ApptmtID " + newRowId, Toast.LENGTH_LONG).show();
        RelativeLayout motherCurrentApptLayout= (RelativeLayout) findViewById(R.id.mother_current_apptmt_layout);
        motherCurrentApptLayout.setVisibility(View.GONE);
        Button motherCurrentApptButton=(Button) findViewById(R.id.rd_ss_motherMarkApptmt);
        motherCurrentApptButton.setVisibility(View.GONE);
        RelativeLayout motherNextApptLayout= (RelativeLayout) findViewById(R.id.rd_mother_next_apptmt);
        motherNextApptLayout.setVisibility(View.VISIBLE);
        Button motherNextApptButton=(Button) findViewById(R.id.rd_ss_motherSetNextApptmt);
        motherNextApptButton.setVisibility(View.VISIBLE);
    }
    public void MotherSetNextAppointment(View view)
    {
        SQLiteDatabase database = new SampleDBContract.SampleDBSQLiteHelper(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        EditText nextApptDt=(EditText)findViewById(R.id.rd_ss_mother_next_apptmt_dt);
        values.put(SampleDBContract.ParentDB.next_apptmt_dt, nextApptDt.getText().toString());
        database.update(SampleDBContract.ParentDB.ParentTbl, values, "parent_id=" + mother_id , null);
        Toast.makeText(this, " Next Appointment date updated.", Toast.LENGTH_LONG).show();
        nextApptDt.setEnabled(false);
        nextApptDt.setInputType(InputType.TYPE_NULL);
        Button motherNextApptButton=(Button) findViewById(R.id.rd_ss_motherSetNextApptmt);
        motherNextApptButton.setEnabled(false);
    }
    public void UpdateDetails(View view)
    {

    }
}

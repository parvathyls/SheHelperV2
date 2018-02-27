package com.example.android.ruraldevelopmenthackathonv1;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;
import java.util.Objects;

import static android.app.DatePickerDialog.*;

/**
 * Created by psivapra on 2/18/2018.
 */

public class RegisterParents   extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener{
        /*,View.OnClickListener {*/
    private Spinner ssLang;
    private static final String[] languages = {"English", "Hindi", "Other"};
    private Spinner parBloodGroup;
    private static final String[] bloodgroups = {"O+ve", "O-ve", "A+ve","A-ve","B+ve","B-ve","AB+ve","AB-ve"};
    private Spinner parRiskStatus;
    private static final String[] parRiskStatuses = {"Low Risk","Moderate Risk","High Risk"};
    private Spinner babyGender;
    private static final String[] babyGenders = {"Male","Female"};
    private Spinner babyRiskStatus;
    private static final String[] babyRiskStatuses = {"Low Risk","Moderate Risk","High Risk"};
    //LinearLayout healthLayout=(LinearLayout) findViewById(R.id.rd_ss_phealthdetails_layout);
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_parents);
        LinearLayout personLayout=(LinearLayout) findViewById(R.id.rd_ss_pparentpersondetails);
        personLayout.setVisibility(View.VISIBLE);
        LinearLayout personotherLayout=(LinearLayout) findViewById(R.id.rd_ss_pparentotherdetails);
        personotherLayout.setVisibility(View.GONE);
        LinearLayout healthLayout=(LinearLayout) findViewById(R.id.rd_ss_phealthdetails_layout);
        healthLayout.setVisibility(View.GONE);
        LinearLayout healthcareLayout=(LinearLayout) findViewById(R.id.rd_ss_phealtcaredetails_layout);
        healthcareLayout.setVisibility(View.GONE);
        LinearLayout babyLayout=(LinearLayout) findViewById(R.id.rd_ss_baby_layout);
        babyLayout.setVisibility(View.GONE);
        /*
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
*/

        ssLang = (Spinner) findViewById(R.id.rd_ss_planguage);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterParents.this,
                android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ssLang.setAdapter(adapter);
        ssLang.setOnItemSelectedListener(this);

        parBloodGroup = (Spinner) findViewById(R.id.rd_ss_pbloodgroup);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(RegisterParents.this,
                android.R.layout.simple_spinner_item, bloodgroups);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parBloodGroup.setAdapter(adapter1);
        parBloodGroup.setOnItemSelectedListener(this);

        parRiskStatus = (Spinner) findViewById(R.id.rd_ss_priskstatus);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(RegisterParents.this,
                android.R.layout.simple_spinner_item, parRiskStatuses);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parRiskStatus.setAdapter(adapter2);
        parRiskStatus.setOnItemSelectedListener(this);

        babyGender = (Spinner) findViewById(R.id.rd_ss_baby_gender);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(RegisterParents.this,
                android.R.layout.simple_spinner_item, babyGenders);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        babyGender.setAdapter(adapter3);
        babyGender.setOnItemSelectedListener(this);

        babyRiskStatus = (Spinner) findViewById(R.id.rd_ss_briskstatus);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(RegisterParents.this,
                android.R.layout.simple_spinner_item, babyRiskStatuses);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        babyRiskStatus.setAdapter(adapter4);
        babyRiskStatus.setOnItemSelectedListener(this);

    }

    /*Calendar myCalendar = Calendar.getInstance();

    EditText duedateControl= (EditText) findViewById(R.id.rd_ss_duedate);

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        duedateControl.setText(sdf.format(myCalendar.getTime()));
    }

    OnDateSetListener date = new OnDateSetListener() {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

        public void onClick(View v) {
            // TODO Auto-generated method stub
            new DatePickerDialog(this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
*/
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

    public void PersonDetails (View view)
    {
        LinearLayout personLayout=(LinearLayout) findViewById(R.id.rd_ss_pparentpersondetails);
        personLayout.setVisibility(View.VISIBLE);
        LinearLayout personotherLayout=(LinearLayout) findViewById(R.id.rd_ss_pparentotherdetails);
        personotherLayout.setVisibility(View.GONE);
        LinearLayout healthLayout=(LinearLayout) findViewById(R.id.rd_ss_phealthdetails_layout);
        healthLayout.setVisibility(View.GONE);
        LinearLayout healthcareLayout=(LinearLayout) findViewById(R.id.rd_ss_phealtcaredetails_layout);
        healthcareLayout.setVisibility(View.GONE);
        LinearLayout babyLayout=(LinearLayout) findViewById(R.id.rd_ss_baby_layout);
        babyLayout.setVisibility(View.GONE);
    }
    public void OtherDetails (View view)
    {
        LinearLayout personLayout=(LinearLayout) findViewById(R.id.rd_ss_pparentpersondetails);
        personLayout.setVisibility(View.GONE);
        LinearLayout personotherLayout=(LinearLayout) findViewById(R.id.rd_ss_pparentotherdetails);
        personotherLayout.setVisibility(View.VISIBLE);
        LinearLayout healthLayout=(LinearLayout) findViewById(R.id.rd_ss_phealthdetails_layout);
        healthLayout.setVisibility(View.GONE);
        LinearLayout healthcareLayout=(LinearLayout) findViewById(R.id.rd_ss_phealtcaredetails_layout);
        healthcareLayout.setVisibility(View.GONE);
        LinearLayout babyLayout=(LinearLayout) findViewById(R.id.rd_ss_baby_layout);
        babyLayout.setVisibility(View.GONE);
    }

    public void ExpParentOrNewParent (View view)
    {
        RadioGroup parentType=(RadioGroup) findViewById(R.id.rd_ss_parent_type);
        int selectedParentType=parentType.getCheckedRadioButtonId();
        RadioButton expParent=(RadioButton) findViewById(R.id.rd_ss_ptype_exp);
        RadioButton newParent=(RadioButton) findViewById(R.id.rd_ss_ptype_new);
        if (selectedParentType==expParent.getId()){
            LinearLayout personLayout=(LinearLayout) findViewById(R.id.rd_ss_pparentpersondetails);
            personLayout.setVisibility(View.GONE);
            LinearLayout personotherLayout=(LinearLayout) findViewById(R.id.rd_ss_pparentotherdetails);
            personotherLayout.setVisibility(View.GONE);
            LinearLayout healthLayout=(LinearLayout) findViewById(R.id.rd_ss_phealthdetails_layout);
            healthLayout.setVisibility(View.VISIBLE);
            LinearLayout healthcareLayout=(LinearLayout) findViewById(R.id.rd_ss_phealtcaredetails_layout);
            healthcareLayout.setVisibility(View.GONE);
            LinearLayout babyLayout=(LinearLayout) findViewById(R.id.rd_ss_baby_layout);
            babyLayout.setVisibility(View.GONE);
        }
        else if (selectedParentType==newParent.getId()){
            //SaveParent(null);
            LinearLayout personLayout=(LinearLayout) findViewById(R.id.rd_ss_pparentpersondetails);
            personLayout.setVisibility(View.GONE);
            LinearLayout personotherLayout=(LinearLayout) findViewById(R.id.rd_ss_pparentotherdetails);
            personotherLayout.setVisibility(View.GONE);
            LinearLayout healthLayout=(LinearLayout) findViewById(R.id.rd_ss_phealthdetails_layout);
            healthLayout.setVisibility(View.GONE);
            LinearLayout healthcareLayout=(LinearLayout) findViewById(R.id.rd_ss_phealtcaredetails_layout);
            healthcareLayout.setVisibility(View.GONE);
            LinearLayout babyLayout=(LinearLayout) findViewById(R.id.rd_ss_baby_layout);
            babyLayout.setVisibility(View.VISIBLE);
        }
    }

    public void HealthDetails (View view)
    {

       LinearLayout personLayout=(LinearLayout) findViewById(R.id.rd_ss_pparentpersondetails);
        personLayout.setVisibility(View.GONE);
        LinearLayout personotherLayout=(LinearLayout) findViewById(R.id.rd_ss_pparentotherdetails);
        personotherLayout.setVisibility(View.GONE);
        LinearLayout healthLayout=(LinearLayout) findViewById(R.id.rd_ss_phealthdetails_layout);
        healthLayout.setVisibility(View.VISIBLE);
        LinearLayout healthcareLayout=(LinearLayout) findViewById(R.id.rd_ss_phealtcaredetails_layout);
        healthcareLayout.setVisibility(View.GONE);
        LinearLayout babyLayout=(LinearLayout) findViewById(R.id.rd_ss_baby_layout);
        babyLayout.setVisibility(View.GONE);
    }
    public void HealthCareDetails (View view)
    {

        LinearLayout personLayout=(LinearLayout) findViewById(R.id.rd_ss_pparentpersondetails);
        personLayout.setVisibility(View.GONE);
        LinearLayout personotherLayout=(LinearLayout) findViewById(R.id.rd_ss_pparentotherdetails);
        personotherLayout.setVisibility(View.GONE);
        LinearLayout healthLayout=(LinearLayout) findViewById(R.id.rd_ss_phealthdetails_layout);
        healthLayout.setVisibility(View.GONE);
        LinearLayout healthcareLayout=(LinearLayout) findViewById(R.id.rd_ss_phealtcaredetails_layout);
        healthcareLayout.setVisibility(View.VISIBLE);
        LinearLayout babyLayout=(LinearLayout) findViewById(R.id.rd_ss_baby_layout);
        babyLayout.setVisibility(View.GONE);
    }
    public void BabyDetails (View view)
    {

        LinearLayout personLayout=(LinearLayout) findViewById(R.id.rd_ss_pparentpersondetails);
        personLayout.setVisibility(View.GONE);
        LinearLayout personotherLayout=(LinearLayout) findViewById(R.id.rd_ss_pparentotherdetails);
        personotherLayout.setVisibility(View.GONE);
        LinearLayout healthLayout=(LinearLayout) findViewById(R.id.rd_ss_phealthdetails_layout);
        healthLayout.setVisibility(View.GONE);
        LinearLayout healthcareLayout=(LinearLayout) findViewById(R.id.rd_ss_phealtcaredetails_layout);
        healthcareLayout.setVisibility(View.GONE);
        LinearLayout babyLayout=(LinearLayout) findViewById(R.id.rd_ss_baby_layout);
        babyLayout.setVisibility(View.VISIBLE);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void  SaveParent(View view){
        Long newRowId;
        TextView viewSSID = (TextView) findViewById(R.id.rd_ss_parentid);

        //SQLiteDatabase database1 = new ParentDBContract.ParentDBSQLiteHelper(this).getWritableDatabase();
        SQLiteDatabase database1 = new SampleDBContract.SampleDBSQLiteHelper(this).getWritableDatabase();
                ContentValues values = new ContentValues();
        EditText rd_ss_pfather_name = (EditText) findViewById(R.id.rd_ss_pfather_name);
        values.put(ParentDBContract.ParentDB.father_name, rd_ss_pfather_name.getText().toString());
        EditText rd_ss_pfather_age = (EditText) findViewById(R.id.rd_ss_pfather_age);
        values.put(ParentDBContract.ParentDB.father_age, rd_ss_pfather_age.getText().toString());
        EditText rd_ss_pmother_name = (EditText) findViewById(R.id.rd_ss_pmother_name);
        values.put(ParentDBContract.ParentDB.mother_name, rd_ss_pmother_name.getText().toString());
        EditText rd_ss_pmother_age = (EditText) findViewById(R.id.rd_ss_pmother_age);
        values.put(ParentDBContract.ParentDB.mother_age, rd_ss_pmother_age.getText().toString());
        EditText rd_ss_pphone = (EditText) findViewById(R.id.rd_ss_pphone);
        values.put(ParentDBContract.ParentDB.par_phone, rd_ss_pphone.getText().toString());
        EditText rd_adm_ss_pemail = (EditText) findViewById(R.id.rd_adm_ss_pemail);
        values.put(ParentDBContract.ParentDB.par_email, rd_adm_ss_pemail.getText().toString());
        EditText rd_adm_ss_ppassword = (EditText) findViewById(R.id.rd_par_password);
        values.put(ParentDBContract.ParentDB.par_password, rd_adm_ss_ppassword.getText().toString());
        EditText rd_ss_pemcontact_name = (EditText) findViewById(R.id.rd_ss_pemcontact_name);
        values.put(ParentDBContract.ParentDB.par_em_contact_name, rd_ss_pemcontact_name.getText().toString());
        EditText rd_ss_pemcontact_phone = (EditText) findViewById(R.id.rd_ss_pemcontact_phone);
        values.put(ParentDBContract.ParentDB.par_em_contact_phone, rd_ss_pemcontact_phone.getText().toString());
        Spinner rd_ss_planguage = (Spinner) findViewById(R.id.rd_ss_planguage);
        values.put(ParentDBContract.ParentDB.par_lang, rd_ss_planguage.getSelectedItem().toString());
        EditText rd_ss_pnum_children = (EditText) findViewById(R.id.rd_ss_pnum_children);
        values.put(ParentDBContract.ParentDB.child_num, rd_ss_pnum_children.getText().toString());
        EditText rd_ss_poccupation = (EditText) findViewById(R.id.rd_ss_poccupation);
        values.put(ParentDBContract.ParentDB.par_occupation, rd_ss_poccupation.getText().toString());
        EditText rd_ss_pannual_income = (EditText) findViewById(R.id.rd_ss_pannual_income);
        values.put(ParentDBContract.ParentDB.par_annual_income, rd_ss_pannual_income.getText().toString());
        EditText rd_ss_pvillage = (EditText) findViewById(R.id.rd_ss_pvillage);
        values.put(ParentDBContract.ParentDB.par_village, rd_ss_pvillage.getText().toString());
        EditText rd_ss_ppostalcode = (EditText) findViewById(R.id.rd_ss_ppostalcode);
        values.put(ParentDBContract.ParentDB.par_postalcode, rd_ss_ppostalcode.getText().toString());
        EditText rd_ss_ppregnancy_months = (EditText) findViewById(R.id.rd_ss_ppregnancy_months);
        values.put(ParentDBContract.ParentDB.preg_months, rd_ss_ppregnancy_months.getText().toString());
        EditText rd_ss_duedate = (EditText) findViewById(R.id.rd_ss_duedate);
        values.put(ParentDBContract.ParentDB.due_dt, rd_ss_duedate.getText().toString());
        Spinner rd_ss_pbloodgroup = (Spinner) findViewById(R.id.rd_ss_pbloodgroup);
        values.put(ParentDBContract.ParentDB.mat_blood_group, rd_ss_pbloodgroup.getSelectedItem().toString());
        CheckBox rd_ss_diabetes=(CheckBox) findViewById(R.id.rd_ss_diabetes);
        if (rd_ss_diabetes.isChecked()){
            values.put(ParentDBContract.ParentDB.diabetes_yn, "Y");
        }else{
            values.put(ParentDBContract.ParentDB.diabetes_yn, "N");
        }
        CheckBox rd_ss_hypertension =(CheckBox) findViewById(R.id.rd_ss_hypertension);
        if (rd_ss_hypertension.isChecked()){
            values.put(ParentDBContract.ParentDB.hypertension_yn, "Y");
        }else{
            values.put(ParentDBContract.ParentDB.hypertension_yn, "N");
        }
        CheckBox rd_ss_fibroids =(CheckBox) findViewById(R.id.rd_ss_fibroids);
        if (rd_ss_fibroids.isChecked()){
            values.put(ParentDBContract.ParentDB.fibroid_yn, "Y");
        }else{
            values.put(ParentDBContract.ParentDB.fibroid_yn, "N");
        }
        CheckBox rd_ss_obesity =(CheckBox) findViewById(R.id.rd_ss_obesity);
        if (rd_ss_obesity.isChecked()){
            values.put(ParentDBContract.ParentDB.obesity_yn, "Y");
        }else{
            values.put(ParentDBContract.ParentDB.obesity_yn, "N");
        }
        CheckBox rd_ss_hormonalissues =(CheckBox) findViewById(R.id.rd_ss_hormonalissues);
        if (rd_ss_hormonalissues.isChecked()){
            values.put(ParentDBContract.ParentDB.hormonal_yn, "Y");
        }else{
            values.put(ParentDBContract.ParentDB.hormonal_yn, "N");
        }
        CheckBox rd_ss_miscarriage =(CheckBox) findViewById(R.id.rd_ss_miscarriage);
        if (rd_ss_miscarriage.isChecked()){
            values.put(ParentDBContract.ParentDB.prior_miscarriage_yn, "Y");
        }else{
            values.put(ParentDBContract.ParentDB.prior_miscarriage_yn, "N");
        }
        EditText rd_ss_pobgyn_name = (EditText) findViewById(R.id.rd_ss_pobgyn_name);
        values.put(ParentDBContract.ParentDB.gyn_name, rd_ss_pobgyn_name.getText().toString());
        EditText rd_ss_phospital_name = (EditText) findViewById(R.id.rd_ss_phospital_name);
        values.put(ParentDBContract.ParentDB.hosp_name, rd_ss_phospital_name.getText().toString());
        EditText rd_ss_pinsurance_provider = (EditText) findViewById(R.id.rd_ss_pinsurance_provider);
        values.put(ParentDBContract.ParentDB.insurance_provider, rd_ss_pinsurance_provider.getText().toString());
        Spinner rd_ss_priskstatus = (Spinner) findViewById(R.id.rd_ss_priskstatus);
        values.put(ParentDBContract.ParentDB.risk_status, rd_ss_priskstatus.getSelectedItem().toString());
        EditText rd_ss_pnxtapptmt = (EditText) findViewById(R.id.rd_ss_pnxtapptmt);
        values.put(ParentDBContract.ParentDB.next_apptmt_dt, rd_ss_pnxtapptmt.getText().toString());
       if (viewSSID.getText().toString() == "") {
            newRowId = database1.insert(SampleDBContract.ParentDB.ParentTbl, null, values);
            viewSSID.setText(Objects.toString(newRowId, null));
            Toast.makeText(this, database1.getVersion()+" The new Row Id is " + newRowId, Toast.LENGTH_LONG).show();
        } else {
            database1.update(SampleDBContract.ParentDB.ParentTbl, values, "parent_id=" + viewSSID.getText().toString(), null);
            Toast.makeText(this, "Row Id:" + viewSSID.getText().toString() + " updated", Toast.LENGTH_LONG).show();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void  SaveBaby(View view) {
        Long newRowId;
        TextView viewBabyID = (TextView) findViewById(R.id.rd_ss_babyid);

        //SQLiteDatabase database = new BabyDBContract.BabyDBSQLiteHelper(this).getWritableDatabase();
        SQLiteDatabase database = new SampleDBContract.SampleDBSQLiteHelper(this).getWritableDatabase();
                ContentValues values = new ContentValues();

        TextView viewParentID = (TextView) findViewById(R.id.rd_ss_parentid);
        values.put(BabyDBContract.BabyDB.parent_id, viewParentID.getText().toString());
        EditText rd_ss_babyDOB = (EditText) findViewById(R.id.rd_ss_babyDOB);
        values.put(BabyDBContract.BabyDB.baby_dob, rd_ss_babyDOB.getText().toString());
        Spinner rd_ss_baby_gender = (Spinner) findViewById(R.id.rd_ss_baby_gender);
        values.put(BabyDBContract.BabyDB.baby_gender, rd_ss_baby_gender.getSelectedItem().toString());
        EditText rd_ss_baby_name = (EditText) findViewById(R.id.rd_ss_baby_name);
        values.put(BabyDBContract.BabyDB.baby_name, rd_ss_baby_name.getText().toString());
        EditText rd_ss_babybirthwt = (EditText) findViewById(R.id.rd_ss_babybirthwt);
        values.put(BabyDBContract.BabyDB.baby_birthwt, rd_ss_babybirthwt.getText().toString());
        EditText rd_ss_babycurrentwt = (EditText) findViewById(R.id.rd_ss_babycurrentwt);
        values.put(BabyDBContract.BabyDB.baby_wtnow, rd_ss_babycurrentwt.getText().toString());
        EditText rd_ss_babybirthheight = (EditText) findViewById(R.id.rd_ss_babybirthheight);
        values.put(BabyDBContract.BabyDB.baby_birthht, rd_ss_babybirthheight.getText().toString());
        EditText rd_ss_babycurrentht = (EditText) findViewById(R.id.rd_ss_babycurrentht);
        values.put(BabyDBContract.BabyDB.baby_htnow, rd_ss_babycurrentht.getText().toString());

        CheckBox rd_ss_lowwt =(CheckBox) findViewById(R.id.rd_ss_lowwt);
        if (rd_ss_lowwt.isChecked()){
            values.put(BabyDBContract.BabyDB.lowwt_yn, "Y");
        }else{
            values.put(BabyDBContract.BabyDB.lowwt_yn, "N");
        }
        CheckBox rd_ss_stuntedgrowth =(CheckBox) findViewById(R.id.rd_ss_stuntedgrowth);
        if (rd_ss_stuntedgrowth.isChecked()){
            values.put(BabyDBContract.BabyDB.stunt_growth_yn, "Y");
        }else{
            values.put(BabyDBContract.BabyDB.stunt_growth_yn, "N");
        }
        CheckBox rd_ss_autism =(CheckBox) findViewById(R.id.rd_ss_autism);
        if (rd_ss_autism.isChecked()){
            values.put(BabyDBContract.BabyDB.autism_yn, "Y");
        }else{
            values.put(BabyDBContract.BabyDB.autism_yn, "N");
        }
        CheckBox rd_ss_downs =(CheckBox) findViewById(R.id.rd_ss_downs);
        if (rd_ss_downs.isChecked()){
            values.put(BabyDBContract.BabyDB.downs_yn, "Y");
        }else{
            values.put(BabyDBContract.BabyDB.downs_yn, "N");
        }

        EditText rd_ss_bhealth_other = (EditText) findViewById(R.id.rd_ss_bhealth_other);
        values.put(BabyDBContract.BabyDB.baby_other, rd_ss_bhealth_other.getText().toString());
        Spinner rd_ss_briskstatus = (Spinner) findViewById(R.id.rd_ss_briskstatus);
        values.put(BabyDBContract.BabyDB.baby_health_sts, rd_ss_briskstatus.getSelectedItem().toString());
        EditText rd_ss_baby_doc = (EditText) findViewById(R.id.rd_ss_baby_doc);
        values.put(BabyDBContract.BabyDB.baby_doc_name, rd_ss_baby_doc.getText().toString());
        EditText rd_ss_baby_hospital_name = (EditText) findViewById(R.id.rd_ss_baby_hospital_name);
        values.put(BabyDBContract.BabyDB.baby_hosp_name, rd_ss_baby_hospital_name.getText().toString());
        EditText rd_ss_baby_insurance_provider = (EditText) findViewById(R.id.rd_ss_baby_insurance_provider);
        values.put(BabyDBContract.BabyDB.baby_health_ins, rd_ss_baby_insurance_provider.getText().toString());
        EditText rd_ss_bnxtapptmt = (EditText) findViewById(R.id.rd_ss_bnxtapptmt);
        values.put(BabyDBContract.BabyDB.baby_nxt_apptmt, rd_ss_bnxtapptmt.getText().toString());
        if (viewBabyID.getText().toString() == "") {
            Button buttonSaveParent=(Button)findViewById(R.id.rd_ss_save_parent);
            buttonSaveParent.performClick();
            values.put(BabyDBContract.BabyDB.parent_id, viewParentID.getText().toString());
            newRowId = database.insert(SampleDBContract.BabyDB.BabyTbl, null, values);
            viewBabyID.setText(Objects.toString(newRowId, null));
            Toast.makeText(this, database.getVersion()+" The new Row Id is " + newRowId, Toast.LENGTH_LONG).show();
        } else {
            database.update(SampleDBContract.BabyDB.BabyTbl, values, "baby_id=" + viewBabyID.getText().toString(), null);
            Toast.makeText(this, "Row Id:" + viewBabyID.getText().toString() + " updated", Toast.LENGTH_LONG).show();
        }

    }
}
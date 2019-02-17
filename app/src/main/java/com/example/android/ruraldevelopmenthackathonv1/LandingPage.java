package com.example.android.ruraldevelopmenthackathonv1; /**
 * Created by psivapra on 2/16/2018.
 */

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ruraldevelopmenthackathonv1.MainActivity;
import com.example.android.ruraldevelopmenthackathonv1.R;

public class LandingPage  extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landingpage);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean Islogin = prefs.getBoolean("Islogin", false); // get value of last login status
        //boolean Islogin = prefs.getBoolean("Islogin", true); // get value of last login status
        Islogin=true;
        if (((RDGlobal) this.getApplication()).getCurrentRole()=="Admin" && Islogin==true){

            Intent admin = new Intent(this,AdmLandingPage.class);
            startActivity(admin);
        }
        if (((RDGlobal) this.getApplication()).getCurrentRole()=="SS" && Islogin==true){
            Intent SS = new Intent(this,SSLandingPage.class);
            startActivity(SS);
        }
        if (((RDGlobal) this.getApplication()).getCurrentRole()=="Parent" && Islogin==true){
            Intent Parent = new Intent(this,ParentScreen.class);
            startActivity(Parent);
        }
    }
    public void TransferToAdmin(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();
        if(button_text.equals("Administrator"))
        {
        /*    SQLiteDatabase database = new SampleDBContract.SampleDBSQLiteHelper(this).getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(SampleDBContract.AdminDB.email, "first.1000days@gmail.com");
            values.put(SampleDBContract.AdminDB.password, "test123");
            values.put(SampleDBContract.AdminDB.phone, "9980855766");
            Long newRowId;
            //SQLiteDatabase db = table.getWritableDatabase();
            String count = "SELECT count(*) FROM rd_admin";
            Cursor mcursor = database.rawQuery(count, null);
            mcursor.moveToFirst();
            int icount = mcursor.getInt(0);
            if(icount>0){
                Toast.makeText(getApplicationContext(),
                        "Admin login already exists: Please use existing login.",
                        Toast.LENGTH_LONG).show();
            }
            else
            {
                newRowId = database.insert(SampleDBContract.AdminDB.AdmTbl, null, values);
            }*/
            ((RDGlobal) this.getApplication()).setCurrentRole("Admin");
            Intent adm = new Intent(this,LoginActivity.class);
            startActivity(adm);
        }

    }
    public void TransferToSS(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();
        if(button_text.equals("Swasthya Saathi"))
        {
            ((RDGlobal) this.getApplication()).setCurrentRole("SS");
            Intent ss = new Intent(this,LoginActivity.class);
            startActivity(ss);
        }

    }
    public void TransferToParent(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();
        if(button_text.equals("Expectant Parent / New Parent"))
        {
            ((RDGlobal) this.getApplication()).setCurrentRole("Parent");
            Intent ss = new Intent(this,LoginActivity.class);
            startActivity(ss);
        }

    }
    public void RegisterAdmin(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();
        if(button_text.equals("Register Admin"))
        {
            SQLiteDatabase database = new SampleDBContract.SampleDBSQLiteHelper(this).getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(SampleDBContract.AdminDB.email, "first.1000days@gmail.com");
            values.put(SampleDBContract.AdminDB.password, "test123");
            values.put(SampleDBContract.AdminDB.phone, "9980855766");
            Long newRowId;
            //SQLiteDatabase db = table.getWritableDatabase();
            String count = "SELECT count(*) FROM rd_admin";
            Cursor mcursor = database.rawQuery(count, null);
            mcursor.moveToFirst();
            int icount = mcursor.getInt(0);
            if(icount>0){
                Toast.makeText(getApplicationContext(),
                        "Admin login already exists: Please use existing login.",
                        Toast.LENGTH_LONG).show();
            }
            else
            {
                newRowId = database.insert(SampleDBContract.AdminDB.AdmTbl, null, values);
            }

            /*if (SampleDBContract.AdminDB.AdmTbl.isEmpty()) {
                newRowId = database.insert(SampleDBContract.AdminDB.AdmTbl, null, values);
            }else{

                Toast.makeText(getApplicationContext(),
                        "Admin login already exists: Please use existing login.",
                        Toast.LENGTH_LONG).show();
            }*/
            ((RDGlobal) this.getApplication()).setCurrentRole("Admin");
            Intent adm = new Intent(this,LoginActivity.class);
            startActivity(adm);
        }

    }

}

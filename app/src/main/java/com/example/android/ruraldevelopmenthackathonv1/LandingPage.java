package com.example.android.ruraldevelopmenthackathonv1; /**
 * Created by psivapra on 2/16/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.ruraldevelopmenthackathonv1.MainActivity;
import com.example.android.ruraldevelopmenthackathonv1.R;

public class LandingPage  extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landingpage);

    }
    public void TransferToAdmin(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();
        if(button_text.equals("Administrator"))
        {
//            Intent admin = new Intent(this,MainActivity.class);
            ((RDGlobal) this.getApplication()).setCurrentRole("Admin");

            Intent admin = new Intent(this,LoginActivity.class);
            startActivity(admin);
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

}

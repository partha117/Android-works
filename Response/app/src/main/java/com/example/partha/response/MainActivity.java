package com.example.partha.response;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.MediaType;

public class MainActivity extends AppCompatActivity {

    final String url = "https://docs.google.com/forms/d/1QF8Glfz89n7qHDsu4wh2CoSEqkyn3Dzcj9d8wIW54TM/formResponse";
    final MediaType FORM_DATA_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    final String[] keyset = new String[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CheckBox Yes = (CheckBox) findViewById(R.id.checkBox);
        final CheckBox No = (CheckBox) findViewById(R.id.checkBox2);
        final TextView drug_name = (TextView) findViewById(R.id.editText);
        final Button submit = (Button) findViewById(R.id.button2);
        final Button next = (Button) findViewById(R.id.button);
        submit.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);
        setkey();
        Yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    submit.setVisibility(View.VISIBLE);
                    next.setVisibility(View.INVISIBLE);
                    No.setChecked(false);
                } else {
                    submit.setVisibility(View.INVISIBLE);
                }
            }
        });
        No.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    next.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.INVISIBLE);
                    Yes.setChecked(false);

                } else {
                    next.setVisibility(View.INVISIBLE);
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable(getApplicationContext())) {
                    if (drug_name.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Drug name is empty !!!!", Toast.LENGTH_LONG).show();
                    } else {
                        PostDataTask postDataTask = new PostDataTask(getApplicationContext(), FORM_DATA_TYPE);
                        postDataTask.setKeys(keyset);
                        postDataTask.execute(url, drug_name.getText().toString(), "Yes", "No", "No", "No", "No", "No", "No", "No", "No");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please turn on Internet", Toast.LENGTH_LONG).show();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable(getApplicationContext())) {
                    if (drug_name.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Drug name is empty !!!!", Toast.LENGTH_LONG).show();
                    } else {
                        Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
                        myIntent.putExtra("drug_name", drug_name.getText().toString()); //Optional parameter
                        MainActivity.this.startActivity(myIntent);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please turn on Internet", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public  void setkey()
    {
        keyset[0]="entry.1200141413";
        keyset[1]="entry.1417124466";
        keyset[2]="entry.1632422022";
        keyset[3]="entry.366756274";
        keyset[4]="entry.409328300";
        keyset[5]="entry.1562627753";
        keyset[6]="entry.194093888";
        keyset[7]="entry.1113533652";
        keyset[8]="entry.89910473";
        keyset[9]="entry.60723686";
        return;
    }
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

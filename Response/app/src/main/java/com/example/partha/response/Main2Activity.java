package com.example.partha.response;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CheckBox;
import android.widget.Toast;

import okhttp3.MediaType;

public class Main2Activity extends AppCompatActivity {
     String  status[]=new String[8];
    final String url="https://docs.google.com/forms/d/1QF8Glfz89n7qHDsu4wh2CoSEqkyn3Dzcj9d8wIW54TM/formResponse";
    final MediaType FORM_DATA_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    final  String[] keyset=new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        initialize();
        setkey();
        final String drug_name = intent.getStringExtra("drug_name");
        CheckBox rashes=(CheckBox)findViewById(R.id.checkBox3);
        CheckBox itching=(CheckBox)findViewById(R.id.checkBox4);
        CheckBox Nausea=(CheckBox)findViewById(R.id.checkBox5);
        CheckBox vomiting=(CheckBox)findViewById(R.id.checkBox6);
        CheckBox diarrhoea=(CheckBox)findViewById(R.id.checkBox7);
        CheckBox headache=(CheckBox)findViewById(R.id.checkBox8);
        CheckBox blurred_vision=(CheckBox)findViewById(R.id.checkBox9);
        CheckBox other=(CheckBox)findViewById(R.id.checkBox10);
        Button submit=(Button)findViewById(R.id.button3);
        rashes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    status[0]="Yes";
                }
                else
                {
                    status[0]="No";
                }
            }
        });
        itching.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    status[1] = "Yes";
                } else {
                    status[1] = "No";
                }
            }
        });
        Nausea.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    status[2] = "Yes";
                } else {
                    status[2] = "No";
                }
            }
        });
        vomiting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    status[3] = "Yes";
                } else {
                    status[3] = "No";
                }
            }
        });
        diarrhoea.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    status[4] = "Yes";
                } else {
                    status[4] = "No";
                }
            }
        });
        headache.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    status[5] = "Yes";
                } else {
                    status[5] = "No";
                }
            }
        });
        blurred_vision.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    status[6] = "Yes";
                } else {
                    status[6] = "No";
                }
            }
        });
        other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    status[7] = "Yes";
                } else {
                    status[7] = "No";
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable(getApplicationContext())) {
                    PostDataTask postDataTask = new PostDataTask(getApplicationContext(), FORM_DATA_TYPE);
                    postDataTask.setKeys(keyset);
                    postDataTask.execute(url, drug_name, "No", status[0], status[1], status[2], status[3], status[4], status[5], status[6], status[7]);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please turn on Internet", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    public  void initialize()

    {
        for(int i=0;i<=7;i++)
        {
            status[i]="No";
        }
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

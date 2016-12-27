package com.rongchut.shuvo.image;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Boolean partial=getIntent().getBooleanExtra("PARTIAL",false);
        Boolean full=getIntent().getBooleanExtra("FULL",false);
        Boolean not_color_blind=getIntent().getBooleanExtra("NOT_COLOR_BLIND",false);
        int slide14=getIntent().getIntExtra("14TH",0);
        int slide15=getIntent().getIntExtra("15TH",0);

        TextView textView=(TextView)findViewById(R.id.textView4);
        Button button=(Button)findViewById(R.id.button) ;
        String  text="";
        if(not_color_blind)
        {
            text="আপনি বর্ণান্ধ নন।";
        }
        else
        {
            if(partial)
            {
                text="আপনি আংশিকভাবে বর্ণান্ধ";
            }
            else if(full)
            {
                text="আপনি পুরোপুরি বর্ণান্ধ";
            }
            if((slide14==2)||(slide15==4))
            {
                text+="\\n"+" এবং আপনার সবুজ রঙের প্রতি বিশেষ সংবেদনশীলতা রয়েছে";
            }
            else if((slide14==6)||(slide15==2))
            {
                text+="\\n"+" এবং আপনার লাল রঙের প্রতি বিশেষ সংবেদনশীলতা রয়েছে";
            }

        }
        textView.setText(text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

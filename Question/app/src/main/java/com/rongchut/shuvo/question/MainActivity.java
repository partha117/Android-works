package com.rongchut.shuvo.question;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private boolean checks[]=new boolean[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list=(ListView)findViewById(R.id.list);
        ArrayList<String> arrayList=populate();
        QuestionAdapter adapter= new QuestionAdapter(this, R.layout.list_cell, R.id.textView3,R.id.Yes, arrayList) {
            @Override
            public void onStatusChange(int position, boolean status) {
                checks[position]=status;
                Toast.makeText(MainActivity.this, "Got it", Toast.LENGTH_LONG).show();
                String temp="";
                for(int i=0;i<20;i++)
                {
                    temp+=checks[i]+" ,";
                }
                Log.e("FROM TAG",temp);

            }
        };
        list.setAdapter(adapter);


    }

    private ArrayList<String >populate()
    {

        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("আপনি যদি কোন দিকে আঙ্গুল দিয়ে দিক নির্দেশ করেন সে কি ঐ দিকে তাকায়?");
        arrayList.add("আপনি কি কোন সময় চিন্তা করেছেন সে বধির কি না?");
        arrayList.add("সে কি এমন কোন খেলা খেলে যেখানে অভিনয় করতে হয়(যেমন পুতুল কে খাওয়ানো,ফোনে কথা বলার ভান করা)");
        arrayList.add("সে কি চেয়ার ,সিঁড়ি অথবা অন্য কিছু চড়ার চেষ্টা করে?");
        arrayList.add("সে কি তার আঙ্গুল নিয়ে অস্বাভাবিক আচরণ করে?");
        arrayList.add("সে কি কোন সময় আঙ্গুল দিয়ে কোন দিক নির্দেশ করে(যেমন আঙ্গুল দিয়ে কোন খেলনা দেখান)?");
        arrayList.add("সে কি কোন মজার বিষয়ে আপনার মনোযোগ আকর্ষণ করার চেষ্টা করে(যেমন পাখি দেখলে আপনাকে দেখানোর চেষ্টা করা)?");
        arrayList.add("সে কি অন্য বাচ্চাদের সাথে খেলতে যায় অথবা অন্য বাচ্চা দেখলে হাসে বা কথা বলার চেষ্টা করে?");
        arrayList.add("সে কি কোন সময় আপনার  মনোযোগ আকর্ষণ করে আপনার সাথে কোন কিছু ভাগাভাগি করার জন্য(যেমন খেলনা নিয়ে  আপনার সাথে খেলার চেষ্টা)?");
        arrayList.add("আপনি নাম ধরে ডাকলে কি সাড়া দেয়?");
        arrayList.add("আপনি হাসলে কি সে ও হাসে?");
        arrayList.add("সে কি চারপাশের শব্দ দ্বারা খুব বিরক্ত হয়?");
        arrayList.add("সে কি হাটতে পারে?");
        arrayList.add("সে কি আপানার চোখের দিকে সরাসরি তাকায় যখন আপনি তার সাথে কথা বলেন বা তার সাথে খেলেন?");
        arrayList.add("সে কি অনুকরণের চেষ্টা করে?");
        arrayList.add("আপনি যদি মাথা ঘুরিয়ে দেখার চেষ্টা করেন সে ও কি তখন আপনি কি দেখছেন তা দেখার চেষ্টা করে?");
        arrayList.add("সে কি কোন সময় আপনার দৃষ্টি আকর্ষণ করে শুধুমাত্র তাকে দেখার জন্য(যেমন বলা \"আমাকে দেখ\")?");
        arrayList.add("আপনি কিছু করতে বললে বা নিষেধ করলে সে কি বুঝতে পারে?");
        arrayList.add("সে কি কোনসময় আপনার অনুভুতি বা মনোভাব বোঝার জন্য আপনার মুখের দিকে তাকায়?");
        arrayList.add("সে কি ঘুরতে পছন্দ করে বা নড়াচড়া করতে হয় এমন খেলা বা কাজ পছন্দ করে?");

        return arrayList;
    }
    /*for only 2,5,12 if yes then count 1
      for others if no count1
       if 2&5&12 yes then asd
       if 1&3&4&6&7&9&10&11&13&14&15&16&17&18&19&20 no then ASD
       point 0-2 low risk if age<24 months needs a 2nd check after 2nd birthday
       point  3-7 needs to consult a health worker or doctor
       point 8-20 high risk it is as good as fast they consult doctor
       */
}

package com.rongchut.shuvo.question;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ASUS on 27-Dec-16.
 */

public abstract class QuestionAdapter extends ArrayAdapter {
    Activity activity;
    ArrayList<String> arrayList;
    int resource;
    int textResource;
    TextView textView;
    TextView serial;

    int yes;



    public QuestionAdapter(Context context, int resource, int textViewResourceId,int radioYes, ArrayList<String> objects) {
        super(context, resource, textViewResourceId, objects);
        activity= (Activity) context;
        arrayList=objects;
        this.resource=resource;
        textResource=textViewResourceId;
        yes=radioYes;





    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            // generate a view and return
            LayoutInflater inflater=activity.getLayoutInflater();
            v = inflater.inflate(resource, null);
            textView=(TextView)v.findViewById(textResource);
            RadioButton yes=(RadioButton)v.findViewById(this.yes) ;
            yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    onStatusChange(position,b);

                }
            });



            String  e=getText(position)+arrayList.get(position);
            textView.setText(e);



        } else {

            textView=(TextView)v.findViewById(textResource);


            String  e=getText(position)+arrayList.get(position);
            textView.setText(e);


        }
        return v;
    }
    private String getText(int pos)
    {
        int position=pos;
        position+=1;
        String text="";
        if(position==1)
        {
            text="#১.";
        }
        else if(position==2)
        {
            text="#২.";
        }
        else if(position==3)
        {
            text="#৩.";

        }
        else if(position==4)
        {
            text="#৪.";

        }
        else if(position==5)
        {
            text="#৫.";

        }
        else if(position==6)
        {
            text="#৬.";

        }
        else if(position==7)
        {
            text="#৭.";

        }
        else if(position==8)
        {
            text="#৮.";

        }
        else if(position==9)
        {
            text="#৯.";

        }
        else if(position==10)
        {
            text="#১০.";

        }
        else if(position==11)
        {
            text="#১১.";

        }
        else if(position==12)
        {
            text="#১২.";

        }
        else if(position==13)
        {
            text="#১৩.";

        }
        else if(position==14)
        {
            text="#১৪.";

        }
        else if(position==15)
        {
            text="#১৫.";

        }
        else if(position==16)
        {
            text="#১৬.";

        }
        else if(position==17)
        {
            text="#১৭.";

        }
        else if(position==18)
        {
            text="#১৮.";

        }
        else if(position==19)
        {
            text="#১৯.";

        }
        else if(position==20)
        {
            text="#২০.";

        }
        return text+"\n";
    }
    public abstract void onStatusChange(int position, boolean status);
}


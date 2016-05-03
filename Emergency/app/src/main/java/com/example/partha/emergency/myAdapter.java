package com.example.partha.emergency;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Partha on 4/29/2016.
 */
public class myAdapter extends ArrayAdapter<AddressBook> {
    Activity activity;
    ArrayList<AddressBook> arrayList;
    int resource;
    int textResource;
    TextView textView;


    public myAdapter(Context context, int resource, int textViewResourceId, ArrayList<AddressBook> objects) {
        super(context, resource, textViewResourceId, objects);
        activity= (Activity) context;
        arrayList=objects;
        this.resource=resource;
        textResource=textViewResourceId;




    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;
        if (convertView == null) {
            // generate a view and return
            LayoutInflater inflater=activity.getLayoutInflater();
            v = inflater.inflate(resource, null);
            textView=(TextView)v.findViewById(textResource);


            AddressBook e=arrayList.get(position);
            textView.setText(e.Organization_name);

        } else {
            v = convertView;
            textView=(TextView)v.findViewById(textResource);


            AddressBook e=arrayList.get(position);
            textView.setText(e.Organization_name);

        }
        return v;
    }
}

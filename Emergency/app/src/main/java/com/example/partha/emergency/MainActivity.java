package com.example.partha.emergency;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String area_selection="Dhaka";
     String organization_selection="Hospital";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner area_spinner=(Spinner)findViewById(R.id.spinner2);
        final Spinner organization_spinner=(Spinner)findViewById(R.id.spinner);
        final ListView listView=(ListView)findViewById(R.id.listView);

        final DBOpenHelper database=new DBOpenHelper(this);
        context=this;







        List<String> area_category = new ArrayList<String>();
        area_category.add("Dhaka");
        area_category.add("Mymensingh");
        area_category.add("Chittagong");
        area_category.add("Sylhet");
        area_category.add("Barisal");
        area_category.add("Rajshahi");
        area_category.add("Rangpur");
        area_category.add("Khulna");


        // Creating adapter for spinner
        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, area_category);

        // Drop down layout style - list view with radio button
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area_spinner.setAdapter(area_adapter);
        area_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                area_selection = parent.getItemAtPosition(position).toString();
                if (organization_selection.matches("Hospital")) {
                    myAdapter adapter = new myAdapter(context, R.layout.hospital, R.id.textView3, database.getAllAdress(area_selection, organization_selection));
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.dialog);
                            TextView Title=(TextView)dialog.findViewById(R.id.textView6);
                            TextView Name=(TextView)dialog.findViewById(R.id.textView7);
                            TextView Location=(TextView)dialog.findViewById(R.id.textView8);
                            TextView Contact=(TextView)dialog.findViewById(R.id.textView9);

                            Title.setText(organization_selection);
                            Name.setText("Name : "+((AddressBook)parent.getItemAtPosition(position)).Organization_name);
                            Location.setText("Location : "+((AddressBook)parent.getItemAtPosition(position)).Location);
                            Contact.setText("Contact : "+((AddressBook)parent.getItemAtPosition(position)).Contact_number);

                            dialog.show();





                        }
                    });
                }
                else if (organization_selection.matches("Blood bank")) {
                    myAdapter adapter = new myAdapter(context, R.layout.blood_bank, R.id.textView4, database.getAllAdress(area_selection, organization_selection));
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.dialog);
                            TextView Title=(TextView)dialog.findViewById(R.id.textView6);
                            TextView Name=(TextView)dialog.findViewById(R.id.textView7);
                            TextView Location=(TextView)dialog.findViewById(R.id.textView8);
                            TextView Contact=(TextView)dialog.findViewById(R.id.textView9);

                            Title.setText(organization_selection);
                            Name.setText("Name : "+((AddressBook)parent.getItemAtPosition(position)).Organization_name);
                            Location.setText("Location : "+((AddressBook)parent.getItemAtPosition(position)).Location);
                            Contact.setText("Contact : "+((AddressBook)parent.getItemAtPosition(position)).Contact_number);

                            dialog.show();

                        }
                    });
                }
                else  {
                    myAdapter adapter = new myAdapter(context, R.layout.ambulance, R.id.textView5, database.getAllAdress(area_selection, organization_selection));
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.dialog);
                            TextView Title=(TextView)dialog.findViewById(R.id.textView6);
                            TextView Name=(TextView)dialog.findViewById(R.id.textView7);
                            TextView Location=(TextView)dialog.findViewById(R.id.textView8);
                            TextView Contact=(TextView)dialog.findViewById(R.id.textView9);
                            ;
                            Title.setText(organization_selection);
                            Name.setText("Name : "+((AddressBook)parent.getItemAtPosition(position)).Organization_name);
                            Location.setText("Location : "+((AddressBook)parent.getItemAtPosition(position)).Location);
                            Contact.setText("Contact : "+((AddressBook)parent.getItemAtPosition(position)).Contact_number);

                            dialog.show();

                        }
                    });
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                area_spinner.setSelection(0);

            }
        });


        List<String> organization_category = new ArrayList<String>();
        organization_category.add("Hospital");
        organization_category.add("Blood bank");
        organization_category.add("Ambulance");
        final ArrayAdapter<String> organization_adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, organization_category);


        organization_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        organization_spinner.setAdapter(organization_adapter);
        organization_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                organization_selection=parent.getItemAtPosition(position).toString();
                if (organization_selection.matches("Hospital")) {
                    myAdapter adapter = new myAdapter(context, R.layout.hospital, R.id.textView3, database.getAllAdress(area_selection, organization_selection));
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.dialog);
                            TextView Title=(TextView)dialog.findViewById(R.id.textView6);
                            TextView Name=(TextView)dialog.findViewById(R.id.textView7);
                            TextView Location=(TextView)dialog.findViewById(R.id.textView8);
                            TextView Contact=(TextView)dialog.findViewById(R.id.textView9);

                            Title.setText(organization_selection);
                            Name.setText("Name : "+((AddressBook)parent.getItemAtPosition(position)).Organization_name);
                            Location.setText("Location : "+((AddressBook)parent.getItemAtPosition(position)).Location);
                            Contact.setText("Contact : "+((AddressBook)parent.getItemAtPosition(position)).Contact_number);

                            dialog.show();
                        }
                    });
                }
                else if (organization_selection.matches("Blood bank")) {
                    myAdapter adapter = new myAdapter(context, R.layout.blood_bank, R.id.textView4, database.getAllAdress(area_selection, organization_selection));
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.dialog);
                            TextView Title=(TextView)dialog.findViewById(R.id.textView6);
                            TextView Name=(TextView)dialog.findViewById(R.id.textView7);
                            TextView Location=(TextView)dialog.findViewById(R.id.textView8);
                            TextView Contact=(TextView)dialog.findViewById(R.id.textView9);

                            Title.setText(organization_selection);
                            Name.setText("Name : "+((AddressBook)parent.getItemAtPosition(position)).Organization_name);
                            Location.setText("Location : "+((AddressBook)parent.getItemAtPosition(position)).Location);
                            Contact.setText("Contact : "+((AddressBook)parent.getItemAtPosition(position)).Contact_number);

                            dialog.show();
                        }
                    });
                }
                else  {
                    myAdapter adapter = new myAdapter(context, R.layout.ambulance, R.id.textView5, database.getAllAdress(area_selection, organization_selection));
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.dialog);
                            TextView Title=(TextView)dialog.findViewById(R.id.textView6);
                            TextView Name=(TextView)dialog.findViewById(R.id.textView7);
                            TextView Location=(TextView)dialog.findViewById(R.id.textView8);
                            TextView Contact=(TextView)dialog.findViewById(R.id.textView9);

                            Title.setText(organization_selection);
                            Name.setText("Name : "+((AddressBook)parent.getItemAtPosition(position)).Organization_name);
                            Location.setText("Location : "+((AddressBook)parent.getItemAtPosition(position)).Location);
                            Contact.setText("Contact : "+((AddressBook)parent.getItemAtPosition(position)).Contact_number);

                            dialog.show();
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                organization_spinner.setSelection(0);

            }
        });



    }

}

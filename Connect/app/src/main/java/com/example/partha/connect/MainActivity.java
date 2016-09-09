package com.example.partha.connect;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText name1=(EditText)findViewById(R.id.editText);
        final EditText price1=(EditText)findViewById(R.id.editText2);
        final EditText description1=(EditText)findViewById(R.id.editText3);

        Button submit=(Button)findViewById(R.id.button);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }

            @Override
            public void onClick(View v) {


                final String name=name1.getText().toString();
                final double price= Double.parseDouble(price1.getText().toString());
                final String description=description1.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("http")
                        .authority("192.168.0.100")
                        .appendPath("webservice")
                        .appendPath("create_product.php")
                        .appendQueryParameter("name", name)
                        .appendQueryParameter("price",  String.valueOf(price))
                        .appendQueryParameter("description", description);

                String url = builder.build().toString();

                StringRequest sr=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    Toast toast= Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG);
                        toast.show();
                        Log.e("From Volley",response);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("From Volley", error.toString());

                    }
                });

                Log.d("From Volley",sr.getUrl()+"   "+sr.toString());

                queue.add(sr);

            }
        });

    }
}
//// caution remember use get params method for post
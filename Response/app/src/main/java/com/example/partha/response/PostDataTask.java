package com.example.partha.response;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Partha on 4/19/2016.
 */
public class PostDataTask extends AsyncTask<String, Void, Boolean> {
    Context context;
    MediaType FORM_DATA_TYPE;
    private String Drug_name_key;
    private String Cured_key;
    private String Rashes_key;
    private String Itching_key;
    private String Nausea_key;
    private String Vomiting_key;
    private String Diarrhoea_key;
    private String Headaches_key;
    private String Blurred_Vision_key;
    private String Others_key;


    public PostDataTask(Context context, MediaType mediaType)
    {
        this.context=context;
        FORM_DATA_TYPE=mediaType;
    }



    public void setKeys(String[]keys)
     {
         Drug_name_key=keys[0];
         Cured_key=keys[1];
         Rashes_key=keys[2];
         Itching_key=keys[3];
         Nausea_key=keys[4];
         Vomiting_key=keys[5];
         Diarrhoea_key=keys[6];
         Headaches_key=keys[7];
         Blurred_Vision_key=keys[8];
         Others_key=keys[9];

     }

    @Override
    protected Boolean doInBackground(String... contactData) {
        Boolean result = true;
        String url               = contactData[0];
        String  Drug_name        = contactData[1];
        String  Cured            = contactData[2];
        String  Rashes           = contactData[3];
        String  Itching          = contactData[4];
        String  Nausea           = contactData[5];
        String  Vomiting         = contactData[6];
        String  Diarrhoea        = contactData[7];
        String  Headaches        = contactData[8];
        String  Blurred_Vision   = contactData[9];
        String  Others           = contactData [10];

        String postBody="";

        try {
            //all values must be URL encoded to make sure that special characters like & | ",etc.
            //do not cause problems
            postBody = Drug_name_key+"=" + URLEncoder.encode(Drug_name, "UTF-8") +
                    "&" + Cured_key + "=" + URLEncoder.encode(Cured,"UTF-8")+"&"+Rashes_key+"="+URLEncoder.encode(Rashes,"UTF-8")+
                    "&"+Itching_key+"="+URLEncoder.encode(Itching,"UTF-8")+"&"+Nausea_key+"="+URLEncoder.encode(Nausea,"UTF-8")
                    +"&"+Vomiting_key+"="+URLEncoder.encode(Vomiting,"UTF-8")+"&"+Diarrhoea_key+"="+URLEncoder.encode(Diarrhoea,"UTF-8")
                    +"&"+Headaches_key+"="+URLEncoder.encode(Headaches,"UTF-8")+"&"+Blurred_Vision_key+"="+URLEncoder.encode(Blurred_Vision,"UTF-8")
                    +"&"+Others_key+"="+URLEncoder.encode(Others,"UTF-8");
        } catch (UnsupportedEncodingException ex) {
            result=false;
        }

            /*
            //If you want to use HttpRequest class from http://stackoverflow.com/a/2253280/1261816
            try {
			HttpRequest httpRequest = new HttpRequest();
			httpRequest.sendPost(url, postBody);
		}catch (Exception exception){
			result = false;
		}
            */

        try{
            //Create OkHttpClient for sending request
            OkHttpClient client = new OkHttpClient();
            //Create the request body with the help of Media Type
            RequestBody body = RequestBody.create(FORM_DATA_TYPE, postBody);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            //Send the request
            Response response = client.newCall(request).execute();
        }catch (IOException exception){
            result=false;
        }
        return result;
    }

    @Override
    protected void onPostExecute(Boolean result){
        //Print Success or failure message accordingly
        Toast.makeText(context, result ? "Message successfully sent!" : "There was some error in sending message. Please try again after some time.", Toast.LENGTH_LONG).show();
    }

}
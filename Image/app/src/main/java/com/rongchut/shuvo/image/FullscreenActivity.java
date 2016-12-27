package com.rongchut.shuvo.image;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private  boolean slide[]=new boolean [16];
    int slide14=0;
    int slide15=0;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible=true;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);
        final ImageView imageView=(ImageView)findViewById(R.id.image);
        Button button=(Button)findViewById(R.id.button2) ;
        final TextView show=(TextView)findViewById(R.id.textView);
        final CheckBox checkBox=(CheckBox)findViewById(R.id.checkBox3);
        final EditText number=(EditText)findViewById(R.id.editText2);
        final int[] currentSlide = {1};
        imageView.setImageBitmap(getBitmapFromAssets(currentSlide[0] + ".png"));
        show.setText(generate(currentSlide[0]));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentSlide[0]<=15)
                {
                    currentSlide[0]++;
                    imageView.setImageBitmap(getBitmapFromAssets(currentSlide[0] + ".png"));
                    show.setText(generate(currentSlide[0]));
                    int num=0;
                    try {
                         num= Integer.parseInt(number.getText().toString());
                    }catch (Exception ex)
                    {

                    }
                    Checker(currentSlide[0]-1,checkBox.isEnabled(),num );
                    number.setText("");
                    checkBox.setChecked(false);
                    if(currentSlide[0]==16)
                    {
                        int counter=0;
                        for(int i=1;i<=15;i++)
                        {
                            if(!slide[i])
                            {
                                counter++;
                            }
                        }
                        Intent intent=new Intent(FullscreenActivity.this,Main2Activity.class);
                        if(counter==0)
                        {
                            intent.putExtra("NOT_COLOR_BLIND",true);
                            intent.putExtra("PARTIAL",false);
                            intent.putExtra("FULL",false);
                            Toast.makeText(FullscreenActivity.this, "আপনি বর্ণান্ধ নন।", Toast.LENGTH_SHORT).show();
                        }
                        else if(counter<=2)
                        {
                            intent.putExtra("PARTIAL",true);
                            intent.putExtra("FULL",false);
                            intent.putExtra("NOT_COLOR_BLIND",false);
                            Toast.makeText(FullscreenActivity.this, "আপনি আংশিক বর্ণান্ধ", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            intent.putExtra("FULL",true);
                            intent.putExtra("PARTIAL",false);
                            intent.putExtra("NOT_COLOR_BLIND",false);
                            Toast.makeText(FullscreenActivity.this, "আপনি বর্ণান্ধ", Toast.LENGTH_SHORT).show();
                        }
                        intent.putExtra("14TH",slide14);
                        intent.putExtra("15TH",slide15);
                        startActivity(intent);
                        finish();

                    }
                }

            }
        });




        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
       // findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

    private void Checker(int slideNumber, boolean enabled, int number)
    {
        if((slideNumber==1)&&(number==12))
        {
            slide[slideNumber]=true;
        }
        else if((slideNumber==2)&&(number==8))
        {
            slide[slideNumber]=true;
        }
        else if((slideNumber==3)&&(number==29))
        {
            slide[slideNumber]=true;
        }
        else if((slideNumber==4)&&(number==5))
        {
            slide[slideNumber]=true;
        }
        else if((slideNumber==5)&&(number==3))
        {
            slide[slideNumber]=true;
        }
        else if((slideNumber==6)&&(number==15))
        {
            slide[slideNumber]=true;
        }
        else if((slideNumber==7)&&(number==74))
        {
            slide[slideNumber]=true;
        }
        else if((slideNumber==8)&&(number==6))
        {
            slide[slideNumber]=true;
        }
        else if((slideNumber==9)&&(number==45))
        {
            slide[slideNumber]=true;
        }
        else if((slideNumber==10)&&(number==5))
        {
            slide[slideNumber]=true;
        }
        else if((slideNumber==11)&&(number==73))
        {
            slide[slideNumber]=true;
        }
        else if((slideNumber==12)&&(enabled))
        {
            slide[slideNumber]=true;
        }
        else if((slideNumber==13)&&(enabled))
        {
            slide[slideNumber]=true;
        }
        else if((slideNumber==14)&&(number==26))
        {
            slide[slideNumber]=true;
        }
        else if((slideNumber==15)&&(number==42))
        {
            slide[slideNumber]=true;
        }
        else if((slideNumber==14)&&(number==2))
        {
            slide14=2;
        }
        else if((slideNumber==14)&&(number==6))
        {
            slide14=6;
        }
        else if((slideNumber==15)&&(number==4))
        {
            slide15=4;
        }
        else if((slideNumber==15)&&(number==2))
        {
            slide15=2;
        }
    }
    String generate(int number)
    {
        String  text="মোট ১৫ টি ধাপের মধ্যে আপনি এখন ";
        String i="";
        if(number==1)
        {
            i="১";
        }
        else if(number==2)
        {
            i="২";
        }
        else if(number==3)
        {
            i="৩";
        }
        else if(number==4)
        {
            i="৪";

        }
        else if(number==5)
        {
            i="৫";

        }
        else if(number==6)
        {
            i="৬";

        }
        else if(number==7)
        {
            i="৭";

        }
        else if(number==8)
        {
            i="৮";

        }
        else if(number==9)
        {
            i="৯";

        }
        else if(number==10)
        {
            i="১০";

        }
        else if(number==11)
        {
            i="১১";

        }
        else if(number==12)
        {
            i="১২";

        }
        else if(number==13)
        {
            i="১৩";

        }
        else if(number==14)
        {
            i="১৪";

        }
        else if(number==15)
        {
            i="১৫";

        }
        text=text+i+" নং ধাপে ";
        return text;

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
    private Bitmap getBitmapFromAssets(String fileName){
        /*
            AssetManager
                Provides access to an application's raw asset files.
        */

        /*
            public final AssetManager getAssets ()
                Retrieve underlying AssetManager storage for these resources.
        */
        AssetManager am = getAssets();
        InputStream is = null;
        try{
            /*
                public final InputStream open (String fileName)
                    Open an asset using ACCESS_STREAMING mode. This provides access to files that
                    have been bundled with an application as assets -- that is,
                    files placed in to the "assets" directory.

                    Parameters
                        fileName : The name of the asset to open. This name can be hierarchical.
                    Throws
                        IOException
            */
            is = am.open("Color_Blind/"+fileName);
        }catch(IOException e){
            e.printStackTrace();
        }

        /*
            BitmapFactory
                Creates Bitmap objects from various sources, including files, streams, and byte-arrays.
        */

        /*
            public static Bitmap decodeStream (InputStream is)
                Decode an input stream into a bitmap. If the input stream is null, or cannot
                be used to decode a bitmap, the function returns null. The stream's
                position will be where ever it was after the encoded data was read.

                Parameters
                    is : The input stream that holds the raw data to be decoded into a bitmap.
                Returns
                    The decoded bitmap, or null if the image data could not be decoded.
        */
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return bitmap;
    }
}

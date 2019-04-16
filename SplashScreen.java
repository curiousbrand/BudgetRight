
package com.example.bpear.budgetright;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashScreen extends Activity {

ImageView logo;
TextView appname;
Animation animation1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        logo = findViewById(R.id.logo);
        appname = findViewById(R.id.studentsavename);
        animation1 = AnimationUtils.loadAnimation(this,R.anim.animation_1);

        logo.startAnimation(animation1);
        appname.startAnimation(animation1);


        final Intent startMain = new Intent(SplashScreen.this, MainActivity.class);

        Thread timer = new Thread() {
            public void run(){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    startActivity(startMain);
                    finish();
                }
            }
        };
            timer.start();
    }


}


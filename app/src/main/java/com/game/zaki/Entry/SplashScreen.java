package com.game.zaki.Entry;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.game.zaki.main.MainActivity;
import com.game.zaki.R;
import com.game.zaki.utility.GS;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Show splash screen for the amount of time and start the needed Activity
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                decideInitialActivity();
            }
        }, GS.splashLength);
    }

    private void decideInitialActivity(){
        boolean isLoggedIn = GS.auth().getCurrentUser() != null;

        Intent nextIntent;
        if(isLoggedIn){
            nextIntent = new Intent(SplashScreen.this, MainActivity.class);
        }
        else{
            nextIntent = new Intent(SplashScreen.this, LoginActivity.class);
        }
        SplashScreen.this.startActivity(nextIntent);
        SplashScreen.this.finish();
    }
}
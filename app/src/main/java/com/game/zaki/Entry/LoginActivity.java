package com.game.zaki.Entry;

import android.content.Intent;
import android.widget.Toast;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.game.zaki.main.MainActivity;
import com.game.zaki.R;
import com.game.zaki.utility.GS;

public class LoginActivity extends FragmentActivity {

    enum person {
        ICE,
        CLOUD
    }

    public person isHere;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ChooseFragment chooseFragment = new ChooseFragment();
        fragmentTransaction.add(R.id.containerLogin, chooseFragment);
        fragmentTransaction.commit();
    }

    public void callConfirmFragment(){
        if(isHere != person.CLOUD  && isHere != person.ICE)
            return;

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ConfirmFragment confirmFragment = new ConfirmFragment();
        fragmentTransaction.replace(R.id.containerLogin, confirmFragment);
        fragmentTransaction.commit();
    }
    public void callChoooseFragment() {
        if (isHere != person.CLOUD && isHere != person.ICE)
            return;

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ChooseFragment chooseFragment = new ChooseFragment();
        fragmentTransaction.replace(R.id.containerLogin, chooseFragment);
        fragmentTransaction.commit();
    }


    public void goToMainScreen(){
        if(GS.auth().getCurrentUser() == null) {
            Toast.makeText(this, "Could not login properly", Toast.LENGTH_LONG).show();
            this.callChoooseFragment();
            return;
        }
        startMainActivity();
    }
    private void startMainActivity(){
        Intent nextIntent = new Intent(LoginActivity.this, MainActivity.class);
        LoginActivity.this.startActivity(nextIntent);
        LoginActivity.this.finish();
    }
}
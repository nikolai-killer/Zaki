package com.game.zaki.main;

import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.game.zaki.R;
import com.game.zaki.main.chat.ChatFragment;
import com.game.zaki.main.gps.GPSFragment;
import com.game.zaki.main.meals.MealsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        bottomNavigationView = findViewById(R.id.main_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            loadFragmentItem(menuItem.getItemId());
            return true;
        });

        bottomNavigationView.setSelectedItemId(R.id.nav_chat);
    }


    public void loadFragmentItem(int menuItemId){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (menuItemId){
            case R.id.nav_gps:
                fragmentTransaction.replace(R.id.main_host_fragment, GPSFragment.newInstance());
                break;
            case R.id.nav_chat:
                fragmentTransaction.replace(R.id.main_host_fragment, ChatFragment.newInstance());
                break;
            case R.id.nav_meals:
                fragmentTransaction.replace(R.id.main_host_fragment, MealsFragment.newInstance());
                break;
        }
        fragmentTransaction.commit();
    }
}
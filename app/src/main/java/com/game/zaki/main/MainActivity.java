package com.game.zaki.main;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import com.game.zaki.R;
import com.game.zaki.utility.PageAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;
    public FragmentManager fragmentManager;
    public ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        viewPager = findViewById(R.id.pager);
        bottomNavigationView = findViewById(R.id.main_bottom_nav);
        PageAdapter pageAdapter = new PageAdapter(this);


        viewPager.setAdapter(pageAdapter);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            loadFragmentItem(menuItem.getItemId());
            return true;
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.nav_gps).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.nav_chat).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.nav_meals).setChecked(true);
                        break;
                    default:
                        bottomNavigationView.getMenu().findItem(R.id.nav_chat).setChecked(true);
                        Log.d("nav", "Default this is not intended!!");
                }
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.nav_chat);
    }




    public void loadFragmentItem(int menuItemId){
        switch (menuItemId){
            case R.id.nav_gps:
                viewPager.setCurrentItem(0);
                break;
            case R.id.nav_chat:
                viewPager.setCurrentItem(1);
                break;
            case R.id.nav_meals:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
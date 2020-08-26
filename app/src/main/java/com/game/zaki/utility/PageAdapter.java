package com.game.zaki.utility;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.game.zaki.main.chat.ChatFragment;
import com.game.zaki.main.gps.GPSFragment;
import com.game.zaki.main.meals.MealsFragment;

public class PageAdapter extends FragmentStateAdapter {

    private final int count = GS.amountPages;

    public PageAdapter(FragmentActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return GPSFragment.newInstance();
            case 1: return ChatFragment.newInstance();
            case 2: return MealsFragment.newInstance();
            default: System.out.println("value:" + position);
        }
        return ChatFragment.newInstance();
    }

    @Override
    public int getItemCount() {
        return this.count;
    }
}



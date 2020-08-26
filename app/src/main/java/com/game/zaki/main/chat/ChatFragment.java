package com.game.zaki.main.chat;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.game.zaki.R;
import com.game.zaki.main.MainActivity;
import com.game.zaki.utility.OnSwipeTouchListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {

    View root;
    MainActivity mainActivity;

    public ChatFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ChatFragment.
     */
    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_chat, container, false);
        mainActivity = (MainActivity) getActivity();
        setSwipeListeners();

        return root;
    }

    private void setSwipeListeners(){
        root.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
            @Override
            public void onSwipeLeft() {
                mainActivity.bottomNavigationView.setSelectedItemId(R.id.nav_meals);
            }

            @Override
            public void onSwipeRight() {
                mainActivity.bottomNavigationView.setSelectedItemId(R.id.nav_gps);
            }
        });
    }
}
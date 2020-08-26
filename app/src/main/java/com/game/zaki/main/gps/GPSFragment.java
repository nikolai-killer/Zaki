package com.game.zaki.main.gps;

import android.os.Bundle;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.game.zaki.R;
import com.game.zaki.main.MainActivity;
import com.game.zaki.utility.OnSwipeTouchListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GPSFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GPSFragment extends Fragment {

    private View root;
    private MainActivity mainActivity;

    public GPSFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GPSFragment.
     */
    public static GPSFragment newInstance() {
        return new GPSFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root =  inflater.inflate(R.layout.fragment_g_p_s, container, false);
        mainActivity = (MainActivity) getActivity();

        setSwipeListeners();


        return root;
    }

    private void setSwipeListeners(){
        root.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
            public void onSwipeLeft() {
                mainActivity.bottomNavigationView.setSelectedItemId(R.id.nav_chat);
            }
        });
    }
}
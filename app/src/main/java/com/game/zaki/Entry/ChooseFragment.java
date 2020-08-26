package com.game.zaki.Entry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.game.zaki.R;

public class ChooseFragment extends Fragment implements View.OnClickListener {
    public LoginActivity loginActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_choose_person, container, false);
        loginActivity = (LoginActivity) getActivity();

        ImageView iceView = root.findViewById(R.id.iceImageChooser);
        ImageView cloudView = root.findViewById(R.id.cloudImageChooser);

        iceView.setOnClickListener(this);
        cloudView.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iceImageChooser:
                loginActivity.isHere = LoginActivity.person.ICE;
                loginActivity.callConfirmFragment();
                break;
            case R.id.cloudImageChooser:
                loginActivity.isHere = LoginActivity.person.CLOUD;
                loginActivity.callConfirmFragment();
                break;
        }
    }
}

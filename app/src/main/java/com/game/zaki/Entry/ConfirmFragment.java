package com.game.zaki.Entry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.game.zaki.R;
import com.game.zaki.utility.GS;
import com.game.zaki.utility.LoadingSpinnerDialog;
import com.google.android.material.textfield.TextInputLayout;
import org.w3c.dom.Text;

import java.util.Objects;

public class ConfirmFragment extends Fragment implements View.OnClickListener {
    public LoginActivity loginActivity;
    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root =  inflater.inflate(R.layout.fragment_confirm_person, container, false);
        loginActivity = (LoginActivity) getActivity();

        View backButton = root.findViewById(R.id.confirm_fragment_back_button);
        backButton.setOnClickListener(this);

        Button loginButton = root.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginButton:
                tryToLogin();
                break;
            case R.id.confirm_fragment_back_button:
                loginActivity.callChoooseFragment();
                break;
        }
    }

    private void tryToLogin(){
        String password = getPassword();
        if(!passwordIsOkay(password)){
            Toast.makeText(loginActivity, "Enter your password! " + ("\ud83d\ude18"),Toast.LENGTH_LONG).show();
            return;
        }
        loginAtFirebase(password);
    }

    private boolean passwordIsOkay(String password){
        return password != null && !password.equals("");
    }

    private String getPassword(){
        return Objects.requireNonNull(((TextInputLayout) root.findViewById(R.id.enterPasswordInputLayout)).getEditText()).getText().toString();
    }

    private void loginAtFirebase(String password){
        String email = getEmail();
        LoadingSpinnerDialog loadingSpinnerDialog = LoadingSpinnerDialog.newInstance();
        loadingSpinnerDialog.show(loginActivity.fragmentManager, "loading");
        GS.auth().signInWithEmailAndPassword(email, password).addOnCompleteListener(t -> {
            loadingSpinnerDialog.dismiss();
            if(!t.isSuccessful() || GS.auth().getCurrentUser() == null){
                Toast.makeText(loginActivity, "Login not successful " + ("\ud83d\ude14")
                        + System.getProperty("line.separator")
                        + "Maybe you forgot your password? " + ("\ud83d\ude18")
                        ,Toast.LENGTH_LONG).show();
                return;
            }
            loginActivity.goToMainScreen();
        });
    }

    private String getEmail(){
        if(loginActivity.isHere == LoginActivity.person.CLOUD){
            return GS.kiwoEmail;
        }
        return GS.schokiEmail;
    }
}

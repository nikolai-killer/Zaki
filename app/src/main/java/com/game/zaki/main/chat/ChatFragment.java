package com.game.zaki.main.chat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.game.zaki.R;
import com.game.zaki.main.MainActivity;
import com.game.zaki.utility.GS;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {

    View root;
    MainActivity mainActivity;
    RecyclerView mRecyclerView;
    ImageView sendImageView;
    TextInputLayout messageLayout;

    private MyRecyclerAdapter myRecyclerAdapter;

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
        mRecyclerView = root.findViewById(R.id.messageRecyclerView);
        sendImageView = root.findViewById(R.id.sendIconChat);
        messageLayout = root.findViewById(R.id.chatInputTextLayout);

//        putTestData();

        setUpFirebaseAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(myRecyclerAdapter);

        sendImageView.setOnClickListener(t -> sendMessage());
        return root;
    }

    private void sendMessage(){
        String msg =  Objects.requireNonNull(messageLayout.getEditText()).getText().toString();
        if(msg.equals("")){
            Toast.makeText(getActivity(), "Enter a message " + ("\ud83d\ude18"), Toast.LENGTH_LONG).show();
            return;
        }
        Message message = new Message(GS.auth().getUid(), msg, System.currentTimeMillis());
        GS.db().getReference().child(GS.chatsChild).push().setValue(message);
    }

    private void setUpFirebaseAdapter(){

        MessageSnapParser messageSnapParser = new MessageSnapParser();

        Query query = GS.db()
                .getReference()
                .child(GS.chatsChild)
                .orderByChild(GS.sendDateChild)
                .limitToLast(50);

        FirebaseRecyclerOptions<Message> options =
                new FirebaseRecyclerOptions.Builder<Message>()
                        .setQuery(query, messageSnapParser)
                        .build();

        myRecyclerAdapter = new MyRecyclerAdapter(options);
    }


    @Override
    public void onStart() {
        super.onStart();
        myRecyclerAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        myRecyclerAdapter.stopListening();
    }


    //TEST
    private void putTestData(){
        for(int i = 0; i < 60; i++){
            Message message = new Message("me", "messagetext " + i, System.currentTimeMillis());
            GS.db().getReference().child(GS.chatsChild).push().setValue(message);  //.child(message.getMessId()).setValue(message);
        }
    }
}
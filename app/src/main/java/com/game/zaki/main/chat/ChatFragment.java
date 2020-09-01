package com.game.zaki.main.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.game.zaki.R;
import com.game.zaki.main.MainActivity;
import com.game.zaki.utility.GS;
import com.google.android.material.textfield.TextInputLayout;
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

    private static int limitedMessagesAmount = 50;
    public static final int limitedMessagesAmountAddition = 50;

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

        setUpFirebaseAdapter();

        sendImageView.setOnClickListener(t -> sendMessage());
        return root;
    }

    private void sendMessage(){
        String msg =  Objects.requireNonNull(messageLayout.getEditText()).getText().toString();
        if(msg.equals("")){
            Toast.makeText(getActivity(), "Enter a message " + ("\ud83d\ude18"), Toast.LENGTH_LONG).show();
            return;
        }
        Message message = new Message(GS.auth().getUid(), msg, System.currentTimeMillis(), false, false);

        String key = GS.db().getReference().child(GS.chatsChild).push().getKey();
        message.setKey(key);
        assert key != null;
        GS.db().getReference().child(GS.chatsChild).child(key).setValue(message).addOnSuccessListener(unused -> {
            Map<String, Object> uploadUpdate = new HashMap<>();
            uploadUpdate.put("uploaded",  true);
            GS.db().getReference().child(GS.chatsChild).child(key).updateChildren(uploadUpdate);
        });

        Objects.requireNonNull(messageLayout.getEditText()).setText("");
    }

    private void setUpFirebaseAdapter(){

        MessageSnapParser messageSnapParser = new MessageSnapParser();

        Query query = getQuery();

        FirebaseRecyclerOptions<Message> options = getMessageFirebaseRecyclerOptions(messageSnapParser, query);

        myRecyclerAdapter = new MyRecyclerAdapter(options, mRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(myRecyclerAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(-1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    ChatFragment.limitedMessagesAmount += ChatFragment.limitedMessagesAmountAddition;
                    Query query = getQuery();
                    FirebaseRecyclerOptions<Message> options = getMessageFirebaseRecyclerOptions(messageSnapParser, query);
                    myRecyclerAdapter.updateOptions(options);
                    myRecyclerAdapter.scrollToBottom = false;
                }
            }
        });
    }

    @NonNull
    private FirebaseRecyclerOptions<Message> getMessageFirebaseRecyclerOptions(MessageSnapParser messageSnapParser, Query query) {
        return new FirebaseRecyclerOptions.Builder<Message>()
                .setQuery(query, messageSnapParser)
                .build();
    }

    @NonNull
    private Query getQuery() {
        Query query = GS.db()
                .getReference()
                .child(GS.chatsChild)
                .orderByChild(GS.sendDateChild)
                .limitToLast(ChatFragment.limitedMessagesAmount);
        query.keepSynced(true);
        return query;
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
}
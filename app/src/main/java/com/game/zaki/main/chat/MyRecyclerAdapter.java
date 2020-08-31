package com.game.zaki.main.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.game.zaki.R;
import com.game.zaki.utility.GS;

public class MyRecyclerAdapter extends FirebaseRecyclerAdapter<Message, MessageViewHolder> {


    public static final int MyMessage = 0;
    public static final int OtherMessage = 1;

    /**
     * Initialize a Adapter that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     */
    public MyRecyclerAdapter(@NonNull FirebaseRecyclerOptions<Message> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MessageViewHolder holder, int position, @NonNull Message message) {
        View mView = holder.mView;
        TextView messageTextView = mView.findViewById(R.id.messageTextView);
        TextView timeTextView = mView.findViewById(R.id.timeTextViewChatItem);


        boolean displayDate = displayDateOnMessage(message, position);

        String displayTime;
        if(displayDate){
            displayTime = MessageViewHolder.getDate(message.getSendDate(), "HH:mm, d.MM");
        }
        else{
            displayTime = MessageViewHolder.getDate(message.getSendDate(), "HH:mm");
        }

        messageTextView.setText(message.getMessage());
        timeTextView.setText(displayTime);
    }


    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int id;
        if(viewType == MyRecyclerAdapter.MyMessage){
            id = R.layout.message_right_item;
        }
        else{
            id = R.layout.message_left_item;
        }
        return new MessageViewHolder(inflater.inflate(id, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        Message message = getItem(position);
        if(message.getSender().equals(GS.auth().getUid())){
            return MyRecyclerAdapter.MyMessage;
        }
        return MyRecyclerAdapter.OtherMessage;
    }

    private boolean displayDateOnMessage(Message message, int position) {
        if(position == 0){
            return true;
        }
        String messageDate = MessageViewHolder.getDate(message.getSendDate(), "d.MM");

        Message prevMessage = getItem(position-1);
        String prevMessageDate = MessageViewHolder.getDate(prevMessage.getSendDate(), "d.MM");

        return !messageDate.equals(prevMessageDate);
    }

    @Override
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
        // TODO somehow scroll here to the bottom of the recycler view
//        observer.
    }
}

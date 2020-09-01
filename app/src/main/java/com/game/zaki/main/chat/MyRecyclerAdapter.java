package com.game.zaki.main.chat;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.game.zaki.R;
import com.game.zaki.utility.GS;

import java.util.HashMap;
import java.util.Map;

public class MyRecyclerAdapter extends FirebaseRecyclerAdapter<Message, MessageViewHolder> {

    private final RecyclerView recyclerView;

    public boolean scrollToBottom = true;

    public static final int messageSeenColor = R.color.colorWhite;
    public static final int MyMessage = 0;
    public static final int OtherMessage = 1;

    /**
     * Initialize a Adapter that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     */
    public MyRecyclerAdapter(@NonNull FirebaseRecyclerOptions<Message> options, RecyclerView recyclerView) {
        super(options);
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onBindViewHolder(@NonNull MessageViewHolder holder, int position, @NonNull Message message) {
        View mView = holder.mView;
        TextView messageTextView = mView.findViewById(R.id.messageTextView);
        TextView timeTextView = mView.findViewById(R.id.timeTextViewChatItem);


        boolean displayDate = displayDateOnMessage(message, position);
        String displayTime = getDateString(message, displayDate);

        messageTextView.setText(message.getMessage());
        timeTextView.setText(displayTime);

        setPossibleCheckIcons(message, mView);
    }

    private void setPossibleCheckIcons(@NonNull Message message, View mView) {
        int itemType = getMessageType(message);
        System.out.println(
                "Message: " + message.getMessage()
                        + " of me: "+ (itemType == MyRecyclerAdapter.MyMessage)
                        + " is uploaded: " + message.isUploaded()
                        + " is received: " + message.isReceived());
        if(itemType == MyRecyclerAdapter.MyMessage && message.isUploaded()){
            ImageView leftIcon = mView.findViewById(R.id.messageLeftCheckIcon);
            leftIcon.setVisibility(ImageView.VISIBLE);
            if(message.isReceived()){
                ImageView rightIcon = mView.findViewById(R.id.messageRightCheckIcon);
                setImageViewToSeenColor(rightIcon);
                setImageViewToSeenColor(leftIcon);
            }
        }
        else if(itemType == MyRecyclerAdapter.OtherMessage && !message.isReceived()){
            Map<String, Object> receivedUpdate = new HashMap<>();
            receivedUpdate.put("received",  true);
            GS.db().getReference().child(GS.chatsChild).child(message.getKey()).updateChildren(receivedUpdate);
        }
    }

    @NonNull
    private String getDateString(@NonNull Message message, boolean displayDate) {
        String displayTime;
        if(displayDate){
            displayTime = GS.getDateFromMillis(message.getSendDate(), "HH:mm, d.MM");
        }
        else{
            displayTime = GS.getDateFromMillis(message.getSendDate(), "HH:mm");
        }
        return displayTime;
    }

    private void setImageViewToSeenColor(ImageView rightIcon) {
        ImageViewCompat
                .setImageTintList(rightIcon,
                        ColorStateList.valueOf(ContextCompat.getColor(recyclerView.getContext(), MyRecyclerAdapter.messageSeenColor)));
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
        return getMessageType(message);
    }

    private int getMessageType(Message message){
        if(message.getSender().equals(GS.auth().getUid())){
            return MyRecyclerAdapter.MyMessage;
        }
        return MyRecyclerAdapter.OtherMessage;
    }

    private boolean displayDateOnMessage(Message message, int position) {
        if(position == 0){
            return true;
        }
        String messageDate = GS.getDateFromMillis(message.getSendDate(), "d.MM");

        Message prevMessage = getItem(position-1);
        String prevMessageDate = GS.getDateFromMillis(prevMessage.getSendDate(), "d.MM");

        return !messageDate.equals(prevMessageDate);
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        if(scrollToBottom){
            recyclerView.scrollToPosition(getItemCount()-1);
        }
        else{
            if(this.getItemCount() < ChatFragment.limitedMessagesAmountAddition - 2){
                recyclerView.scrollToPosition(0);
            }
            else{
                recyclerView.scrollToPosition(ChatFragment.limitedMessagesAmountAddition - 2);
            }
        }
        scrollToBottom = true;
    }
}

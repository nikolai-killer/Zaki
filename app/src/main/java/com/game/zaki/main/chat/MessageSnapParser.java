package com.game.zaki.main.chat;

import androidx.annotation.NonNull;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;

public class MessageSnapParser implements SnapshotParser<Message> {
    @NonNull
    @Override
    public Message parseSnapshot(@NonNull DataSnapshot snapshot) {
        Message message = snapshot.getValue(Message.class);
        assert message != null;
        return message;
    }
}

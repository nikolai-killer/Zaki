package com.game.zaki.main.chat;

import java.sql.Timestamp;

public class Message {

    private String sender;
    private String message;
    private Long sendDate;

    public Message() {} // Needed for firebase

    public Message(String sender, String message, Long sendDate) {
        this.sender = sender;
        this.message = message;
        this.sendDate = sendDate;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSendDate() {
        return sendDate;
    }

    public void setSendDate(Long sendDate) {
        this.sendDate = sendDate;
    }

}

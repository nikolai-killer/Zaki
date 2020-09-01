package com.game.zaki.main.chat;

public class Message {

    private String key;
    private String sender;
    private String message;
    private Long sendDate;
    private boolean received;
    private boolean uploaded;

    public Message() {} // Needed for firebase

    public Message(String sender, String message, Long sendDate, boolean received, boolean uploaded) {
        this.sender = sender;
        this.message = message;
        this.sendDate = sendDate;
        this.received = received;
        this.uploaded = uploaded;
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

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

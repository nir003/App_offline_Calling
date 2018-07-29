package com.example.nirjhor.offlinecalling;

public class SingleUserMessage {
    private int messageID;
    private String myIp;
    private String ConnectedIp;
    private String message;
    private int userRoll;

    public SingleUserMessage(int messageID, String myIp, String connectedIp, String message,int userRoll) {
        this.messageID = messageID;
        this.myIp = myIp;
        ConnectedIp = connectedIp;
        this.message = message;
        this.userRoll = userRoll;
    }

    public int getUserRoll() {
        return userRoll;
    }

    public SingleUserMessage(String myIp, String connectedIp, String message,int userRoll) {
        this.myIp = myIp;
        ConnectedIp = connectedIp;
        this.message = message;
        this.userRoll = userRoll;

    }

    public int getMessageID() {
        return messageID;
    }

    public String getMyIp() {
        return myIp;
    }

    public String getConnectedIp() {
        return ConnectedIp;
    }

    public String getMessage() {
        return message;
    }
}

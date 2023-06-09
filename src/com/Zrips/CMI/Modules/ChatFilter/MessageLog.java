package com.Zrips.CMI.Modules.ChatFilter;

import java.util.HashMap;

public class MessageLog {

    private HashMap<Long, String> messages = new HashMap<Long, String>();
    private int range = 5;

    public MessageLog(int range) {
        this.range = range;
    }

    public void addMessage(String message) {
        messages.put(System.currentTimeMillis(), message);
    }

    private void removeOld() {
    }

    public HashMap<Long, String> getMessages() {
        removeOld();
        return messages;
    }

}

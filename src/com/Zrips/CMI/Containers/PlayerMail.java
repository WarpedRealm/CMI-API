package com.Zrips.CMI.Containers;

public class PlayerMail {

    private String sender;
    private Long time;
    private Long keepFor;
    private String message;

    public PlayerMail(String sender, Long time, String message) {
	this(sender, time, message, 0L);
    }

    public PlayerMail(String sender, Long time, String message, Long keepForSeconds) {
	this.sender = sender;
	this.time = time;
	this.message = message;
	keepFor = keepForSeconds;
    }

    public String getSender() {
	return sender;
    }

    public void setSender(String sender) {
	this.sender = sender;
    }

    public Long getTime() {
	return time;
    }

    public void setTime(Long time) {
	this.time = time;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public Long getKeepFor() {
	return keepFor == null ? 0L : keepFor;
    }

    public void setKeepFor(Long keepFor) {
	this.keepFor = keepFor;
    }

}

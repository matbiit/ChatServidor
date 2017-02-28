package model;

import java.sql.Timestamp;

public class StoredMessage {

	private String to;
	private String from;
	private String msg;
	private String uid;
	private Timestamp time;

	public StoredMessage(String uid, String to, String from, String msg) {
		this.uid = uid;
		this.to = to;
		this.from = from;
		this.msg = msg;
		this.time = new Timestamp(System.currentTimeMillis());
	}

}

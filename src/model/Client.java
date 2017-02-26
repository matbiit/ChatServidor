package model;

import java.net.Socket;

public class Client {
	
	private Socket connection;
	private String id;
	
	
	public Client(Socket socket){
		this.connection = socket;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Socket getConnection() {
		return this.connection;
	}
	
	
}

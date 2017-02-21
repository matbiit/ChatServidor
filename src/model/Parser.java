package model;

import com.google.gson.Gson;

public class Parser {
	
	private Gson jsonParser;
	
	public Parser(){
		this.jsonParser = new Gson();
	}
	
	public String getMessage(String requestData) {
		RequestProtocol request = this.jsonParser.fromJson(requestData, RequestProtocol.class);
		return this.sendResponse(request);
	}

	private String sendResponse(RequestProtocol request) {
		// lógica para tratamento dos comandos
		return this.jsonParser.toJson(request);
	}
	
}

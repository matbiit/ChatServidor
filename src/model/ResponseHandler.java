package model;

import java.util.Set;

public class ResponseHandler {
	
	private Parser parser;
	
	public ResponseHandler(){
		this.parser = new Parser();
	}

	public String doLogin(String id, Set<String> set) {
		// TODO verifica se há mensagens pelo id;
		ResponseProtocol response = new ResponseProtocol();
		response.setId("0");
		response.setUserLoggedIn(set);
		return this.parser.parseResponseToJson(response);
	}

	public String sendFeedback() {
		ResponseProtocol response = new ResponseProtocol();
		response.setId("0");
		return this.parser.parseResponseToJson(response);
	}
	
}

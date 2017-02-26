package model;

public class ResponseHandler {
	
	private Parser parser;
	
	public ResponseHandler(){
		this.parser = new Parser();
	}
	
	public String buildResponseFrom(RequestProtocol request) {
		switch (request.getCmd()) {
		case "login":
			return this.doLogin(request.getId());
		default:
			return "";
		}
	}

	public String doLogin(String id) {
		// TODO verifica se há mensagens pelo id;
		ResponseProtocol response = new ResponseProtocol();
		response.setId("0");
		return this.parser.parseResponseToJson(response);
	}
	
}

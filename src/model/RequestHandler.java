package model;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class RequestHandler implements Runnable {

	private Socket cliente;
	private Servidor servidor;
	private Parser parser;
	
	public RequestHandler(Socket cliente, Servidor servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
		this.parser = new Parser();
	}

	public void run() {
		try(Scanner s = new Scanner(this.cliente.getInputStream())) {
			ResponseHandler response = new ResponseHandler();
			while (s.hasNextLine()) {
				RequestProtocol request = parser.parseToRequest(s.nextLine());
				servidor.distribuiMensagem(this.cliente, response.buildResponseFrom(request));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
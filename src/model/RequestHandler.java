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
			while (s.hasNextLine()) {
				RequestProtocol request = parser.parseToRequest(s.nextLine());
				this.checkCommand(request);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void checkCommand(RequestProtocol request) {
		
		ResponseHandler response = new ResponseHandler();
		String msg = null;
		
		switch (request.getCmd().toLowerCase()) {
		case "login":
			if(!this.servidor.clientes.containsKey(request.getId()))
				this.servidor.clientes.put(request.getId(), this.cliente);
			servidor.enviaMensagemAoCliente(cliente, 
					response.doLogin(request.getId(), servidor.usersLoggedIn()));
			break;
		case "enviar":
			if(request.getDst().equals(servidor.ID))
				servidor.doKnocKnoc(cliente, request.getData());
			else {
				Socket dest = servidor.clientes.get(request.getDst());
				if(dest != null){
					servidor.enviaMensagemAoCliente(dest, request.getData());					
				}
				else {
					servidor.registerMessage(request.getMsgNr(), request.getId(), 
							request.getDst(), request.getData());
				}
				servidor.enviaMensagemAoCliente(cliente, response.sendFeedback());
			}
			break;
		case "receber":
			break;
		default:
			break;
		}		
	}
}
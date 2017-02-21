package model;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class TratadorDeMensagemDoCliente implements Runnable {

	private Socket cliente;
	private Servidor servidor;
	private Parser parser;
	
	public TratadorDeMensagemDoCliente(Socket cliente, Servidor servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
		this.parser = new Parser();
	}

	public void run() {
		try(Scanner s = new Scanner(this.cliente.getInputStream())) {
			while (s.hasNextLine()) {
				servidor.distribuiMensagem(this.cliente, this.parser.getMessage(s.nextLine()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
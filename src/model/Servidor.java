package model;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Servidor {

	private int porta;
	HashMap<String, Socket> clientes;
	final String ID = "0";
	
	public Servidor(int porta) {
		this.porta = porta;
		this.clientes = new HashMap<>();
	}

	public void execute() throws IOException  {
		try(ServerSocket servidor = new ServerSocket(this.porta)){
			System.out.println("Server is running!");
			while (true) {
				Socket cliente = servidor.accept();
				System.out.println("Nova conexão com o cliente " + 
						cliente.getInetAddress().getHostAddress());
				RequestHandler tc = new RequestHandler(cliente, this);
				new Thread(tc).start();
			}
		}
	}
	
	public void enviaMensagemAoCliente(Socket clienteQueEnviou, String msg){
		try {
			PrintStream ps = new PrintStream(clienteQueEnviou.getOutputStream());
			ps.println(msg);
			ps.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void distribuiMensagem(Socket clienteQueEnviou, String msg) {
		for (Socket cliente : this.clientes.values()) {
			try {
				PrintStream ps = new PrintStream(cliente.getOutputStream());
				ps.println(msg);				
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	public void doKnocKnoc(Socket cliente, String msg) {
		if(msg.equals("piada")){
			this.enviaMensagemAoCliente(cliente, "Knoc knoc");
		}
		else if(msg.equals("quem é?")){
			this.enviaMensagemAoCliente(cliente, "É o sunda");
		}
		else if(msg.equals("que sunda?")){
			this.enviaMensagemAoCliente(cliente, "O que comeu sua bunda! hahahaha");
		}
		else {
			this.enviaMensagemAoCliente(cliente, "Entendi não filhão :/");
		}
		
	}
	
}
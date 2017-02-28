package model;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Servidor {

	private int porta;
	HashMap<String, Socket> clientes;
	private Joke joke;
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
			ps.print(msg); //se o cliente for java, println
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
		if(joke == null || joke.isEnd())
			this.joke = new JokeRepository().getJoke();
		System.out.println(joke.isEnd());
		this.enviaMensagemAoCliente(cliente, this.joke.tell(msg));
	}
	
	public int usersLoggedIn(){
		return this.clientes.size() -1;
	}

	public void registerMessage(String src, String msg) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
	}
	
}
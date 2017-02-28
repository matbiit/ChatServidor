package model;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Servidor {

	private int porta;
	private Joke joke;
	final String ID = "0";
	HashMap<String, Socket> clientes;
	ArrayList<StoredMessage> messages;
	
	public Servidor(int porta) {
		this.porta = porta;
		this.clientes = new HashMap<>();
		this.messages = new ArrayList<>();
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
	
	public Set<String> usersLoggedIn(){
		return this.clientes.keySet();
	}

	public void registerMessage(String uid, String to, String from, String msg) {
		StoredMessage message = new StoredMessage(uid, to, from, msg);
		this.messages.add(message);
		
	}
	
}
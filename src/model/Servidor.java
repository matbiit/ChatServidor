package model;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Servidor {
	 
   private ArrayList<PrintStream> clientes = new ArrayList<PrintStream>();
   
   public void iniciarNaPorta(String porta){
	 
    ServerSocket servidor;
	try {
		servidor = new ServerSocket(Integer.parseInt(porta));
		System.out.println("O servidor está ouvindo na porta " + servidor.getLocalPort());
		
		while (true) {
			Socket cliente = servidor.accept();
		    System.out.println("Cliente " + cliente.getInetAddress().getHostAddress() + " conectado!");
		       
		    PrintStream psCliente = new PrintStream(cliente.getOutputStream());
		    this.clientes.add(psCliente);
		       
		    new Thread(new Runnable() {
				
			    @Override
			    public void run() {
			    	Scanner s;
					try {
						s = new Scanner(cliente.getInputStream());
						while (s.hasNextLine()) {
				    		System.out.println(s.nextLine());
				    		psCliente.println("recebi");
				        }
				    	s.close();
					} catch (IOException e) {
						System.out.println("Erro ao ler mensagens do cliente");
						e.printStackTrace();
					}
			    }
		    }).start();
		}
	} catch (NumberFormatException | IOException e) {
		System.out.println("Erro ao estabelecer conexão");
		e.printStackTrace();
	}
 
   }
	 
}
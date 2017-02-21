package main;

import java.io.IOException;

import model.Servidor;

public class Core {

	public static void main(String[] args) {
		
		Servidor servidor = new Servidor(8000);
		try {
			servidor.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}

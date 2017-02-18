package main;

import model.Servidor;

public class Core {

	public static void main(String[] args) {
		
		Servidor servidor = new Servidor();
		servidor.iniciarNaPorta("777");
//		servidor.iniciarNaPorta(args[0]);
		
	}
	
}

package chat;

import java.util.Scanner;

public class EnviarMsg extends Thread {
	
	private Cliente cliente;
	
	public EnviarMsg(Cliente c) {
		cliente = c;
	}
	
	public void run() {
		
		Scanner s = new Scanner(System.in);
		
		while(!cliente.getUltimaFrase().equals("###")) {
			cliente.setUltimaFrase(s.nextLine());
			cliente.enviarMensagem();
		}
		
		cliente.close();
		s.close();
		System.out.println("=> Voce parou de enviar mensagens");
	}

}

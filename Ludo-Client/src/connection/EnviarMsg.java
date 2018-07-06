package connection;

import java.util.Scanner;

public class EnviarMsg extends Thread {
	
	private Cliente cliente;
	
	public EnviarMsg(Cliente c) {
		cliente = c;
	}
	
	public void run() {
		
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		
		while(!cliente.getUltimaFraseEnviada().equals("###")) {
			String msg = s.nextLine();
			
			if(!cliente.getUltimaFraseEnviada().equals("###")) {
				
				if(!msg.equals("###"))
					cliente.setUltimaFraseEnviada("C" + msg);
				else
					cliente.setUltimaFraseEnviada(msg);

				cliente.enviarMensagem();
			}
			
		}
		
		cliente.close();
		//System.out.println("=> Voce parou de enviar mensagens");
	}

}

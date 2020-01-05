package chat;

public class ReceberMsg extends Thread {
	
	private Cliente cliente;
	
	public ReceberMsg(Cliente c) {
		cliente = c;
	}
	
	public void run() {
		
		while(!cliente.getUltimaFrase().equals("###")) {
			cliente.esperarMensagem();
		}
		
		System.out.println("=> Voce parou de receber mensagens");
	}
	
	

}
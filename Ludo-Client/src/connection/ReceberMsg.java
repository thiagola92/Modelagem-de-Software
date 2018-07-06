package connection;

public class ReceberMsg extends Thread {
	
	private Cliente cliente;
	
	public ReceberMsg(Cliente c) {
		cliente = c;
	}
	
	public void run() {
		
		while(!cliente.getUltimaFraseEnviada().equals("###")) {
			cliente.esperarMensagem();
		}
		
		//System.out.println("=> Voce parou de receber mensagens");
	}
	
	

}
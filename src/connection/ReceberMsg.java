package connection;

public class ReceberMsg extends Thread {
	
    private ClienteN cliente;
	
    public ReceberMsg(ClienteN c) {
        cliente = c;
    }
    
    @Override
    public void run() {
        while(!cliente.getUltimaFraseRecebida().equals("###")) {
            cliente.esperarMensagem();
        }
        
        System.out.println("=> Voce parou de receber mensagens do Cliente " + cliente.getNumeroDoCliente());
    }

}

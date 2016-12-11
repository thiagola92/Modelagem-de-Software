package connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.channels.IllegalBlockingModeException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class Server extends Thread implements Observer {
    
    private int numeroDeConexoes;
    private int numeroMaxDeConexoes;
    private int porta;
    
    private ServerSocket server;
    private HashMap<Integer,ClienteN> conexoes;
    private boolean isClosed;
    
    private Timer timer;
    
    public Server()  {
    	
        numeroDeConexoes = 0;
        numeroMaxDeConexoes = 4;
        porta = 8;
        
        try {
            
            server = new ServerSocket(porta);
            conexoes = new HashMap<Integer, ClienteN>();
            isClosed = false;
            
        } catch (IOException e) {
        	
        	isClosed = true;
        	
            System.out.println("=> Server > Server");
            System.out.println("=> IOException - if an I/O error occurs when waiting for a connection.");
            //e.printStackTrace();
        }
        
        System.out.println("=> Server criado");
    }
    
    public void receberConexao() {
        
        try {
            
            Socket conexao = server.accept();
            ClienteN cliente = new ClienteN(numeroDeConexoes, conexao);
            cliente.addObserver(this);
            conexoes.put(numeroDeConexoes, cliente);
            numeroDeConexoes++;
            
            if (numeroDeConexoes == 1) {
            	
            	System.out.println("=> Contando tempo para todos se conectarem");
            	
            	timer = new Timer();
            	timer.schedule(new TimerTask() {
            		  @Override
            		  public void run() {
            			  if (numeroDeConexoes != numeroMaxDeConexoes) {
            				  System.out.println("=> Acabou o tempo");
            				  for (int i = 0; i < conexoes.size(); i++)
            					  enviarMensagem("C=> Conexao finalizada, tempo maximo excedido", i);
            				  close();
            			  }
            		  }
            		}, 1*60*1000);
            }
            
            System.out.println("=> Uma conexao foi estabelecida com Cliente " + (numeroDeConexoes-1));
            
        } catch (SocketTimeoutException e) {
        	
            System.out.println("=> Server > receberConexao");
            System.out.println("=> SocketTimeoutException - if a timeout was previously set with setSoTimeout and the timeout has been reached.");
            //e.printStackTrace();
        } catch (IOException e) {
        	
            System.out.println("=> Server > receberConexao");
            System.out.println("=> IOException - if an I/O error occurs when waiting for a connection.");
            //e.printStackTrace();
        } catch (SecurityException e) {
        	
            System.out.println("=> Server > receberConexao");
            System.out.println("=> SecurityException - if a security manager exists and its checkAccept method doesn't allow the operation.");
            //e.printStackTrace();
        } catch (IllegalBlockingModeException e) {
        	
            System.out.println("=> Server > receberConexao");
            System.out.println("=> IllegalBlockingModeException - if this socket has an associated channel, the channel is in non-blocking mode, and there is no connection ready to be accepted.");
            //e.printStackTrace();
        }   
        
    }
        
    public void enviarMensagem(String mensagem, int indice) {
        
        try {
            
            ClienteN cliente = conexoes.get(indice);
            cliente.enviarMensagem(mensagem);
            
        } catch (IndexOutOfBoundsException e) {
        	
            System.out.println("=> Server > enviarMensagem");
            System.out.println("=> IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())");
            //e.printStackTrace();
        } catch (SecurityException e) {
        	
            System.out.println("=> Server > enviarMensagem");
            System.out.println("=> SecurityException - If a security manager is present and checkWrite(file.getPath()) denies write access to the file");
            //e.printStackTrace();
        }
    }
    
    public void close() {
    	
        try {
            System.out.println("=> Server finalizando");
            
            for(int i=0; i < conexoes.size(); i++) {
            	
                ClienteN temp = conexoes.get(i);
                
                if (temp != null) {
                    temp.close();
                }
                
            }
            
            if(server.isClosed() == false) {
            	timer.cancel();
            	server.close();
            	isClosed = true;
            }

            System.out.println("=> Server finalizado");
            
        } catch (IOException e) {
        	
            System.out.println("=> Server > close");
            System.out.println("=> IOException - if an I/O error occurs when closing the socket.");
            //e.printStackTrace();
        }
    }
    
    ///////////////////////////////
    
    @Override
    public void update(Observable obs, Object obj) {
        ClienteN cliente = (ClienteN)obs;
        
        for(int i=0; i < conexoes.size(); i++) {
            if(i != cliente.getNumeroDoCliente())
                enviarMensagem(cliente.getUltimaFraseRecebida(), i);
        }
        
        System.out.println("=> Mensagem enviada a todos");
    }
    
    public void run() {
        while(numeroDeConexoes != numeroMaxDeConexoes && server.isClosed() == false) {
            receberConexao();
        }

        for(int i = 0; i < numeroMaxDeConexoes && server.isClosed() == false; i++ )
        	enviarMensagem("N", i);
        
        System.out.println("=> Nao esta recebendo mais conexoes");
    }
    
    ////////////////////////////////
    
    public boolean isClosed() {
    	return isClosed;
    }
}

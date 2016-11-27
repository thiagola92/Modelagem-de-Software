package connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.channels.IllegalBlockingModeException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class Server extends Thread implements Observer {
    
    private int numeroDeConexoes;
    private int porta;
    private String ultimoComando;
    
    private ServerSocket server;
    private HashMap<Integer,ClienteN> conexoes;
    
    
    public Server()  {
        
        ultimoComando = "";
        numeroDeConexoes = 0;
        porta = 8;
        
        try {
            
            server = new ServerSocket(porta);
            conexoes = new HashMap();
            
        } catch (IOException e) {
            // TODO
            System.out.println("=> IOException - if an I/O error occurs when waiting for a connection.");
            e.printStackTrace();
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
            
            System.out.println("=> Uma conexao foi estabelecida com Cliente " + (numeroDeConexoes-1));
            
        } catch (SocketTimeoutException e) {
            // TODO
            System.out.println("=> SocketTimeoutException - if a timeout was previously set with setSoTimeout and the timeout has been reached.");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO
            System.out.println("=> IOException - if an I/O error occurs when waiting for a connection.");
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO
            System.out.println("=> SecurityException - if a security manager exists and its checkAccept method doesn't allow the operation.");
            e.printStackTrace();
        } catch (IllegalBlockingModeException e) {
            // TODO
            System.out.println("=> IllegalBlockingModeException - if this socket has an associated channel, the channel is in non-blocking mode, and there is no connection ready to be accepted.");
            e.printStackTrace();
        }   
        
    }
        
    public void enviarMensagem(String mensagem, int indice) {
        
        try {
            
            ClienteN cliente = conexoes.get(indice);
            cliente.enviarMensagem(mensagem);
            
        } catch (IndexOutOfBoundsException e) {
            // TODO
            System.out.println("=> IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())");
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO
            System.out.println("=> SecurityException - If a security manager is present and checkWrite(file.getPath()) denies write access to the file");
            e.printStackTrace();
        }
    }
    
    public void close() {
        try {
            server.close();
            
            for(int i=0; i < conexoes.size(); i++) {
                ClienteN temp = conexoes.get(i);
                if (temp != null)
                    temp.close();
            }
        } catch (IOException e) {
            System.out.println("=> IOException - if an I/O error occurs when closing the socket.");
            e.printStackTrace();
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
        while(!ultimoComando.equals("###")) {
            receberConexao();
        }
        
        System.out.println("=> Nao esta recebendo mais conexoes");
    }
    
    ////////////////////////////////

    public int getNumeroDeConexoes() {
        return numeroDeConexoes;
    }

    public void setNumeroDeConexoes(int numeroDeConexoes) {
        this.numeroDeConexoes = numeroDeConexoes;
    }
    
    public String getUltimoComando() {
        return ultimoComando;
    }
    
    public void setUltimoComando(String ultimoComando) {
        this.ultimoComando = ultimoComando;
    }
    
}

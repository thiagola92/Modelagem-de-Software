/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Scanner;

public class ClienteN extends Observable {
    
    private int numeroDoCliente;
    
    // Informacoes do usuario
    private String nickname;
    private String ultimaFraseRecebida;
    
    // Conexao
    private Socket conexao;
    
    // Ler e escrever
    private Scanner entrada;
    private PrintStream saida;
    
    // Threads
    private ReceberMsg rm;
    
    public ClienteN(int i, Socket c) {
        
        numeroDoCliente = i;
        ultimaFraseRecebida = "";
        conexao = c;
        
        try {
            
            entrada = new Scanner(conexao.getInputStream());
            saida = new PrintStream(conexao.getOutputStream());
            
            esperarMensagem();
            nickname = ultimaFraseRecebida;
            
            System.out.println("=> Nickname do cliente: " + nickname);
            
            enviarMensagem("R" + numeroDoCliente);
            
            rm = new ReceberMsg(this);
            rm.start();
            
            System.out.println("=> Cliente " + numeroDoCliente + " criado");
            
        } catch (IndexOutOfBoundsException e) {

            System.out.println("=> ClientN > ClientN");
            System.out.println("=> IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())");
            //e.printStackTrace();
        } catch (FileNotFoundException e) {

            System.out.println("=> ClientN > ClientN");
            System.out.println("=> FileNotFoundException - If the given file object does not denote an existing, writable regular file and a new regular file of that name cannot be created, or if some other error occurs while opening or creating the file");
            //e.printStackTrace();
        } catch (SecurityException e) {

            System.out.println("=> ClientN > ClientN");
            System.out.println("=> SecurityException - If a security manager is present and checkWrite(file.getPath()) denies write access to the file");
            //e.printStackTrace();
        } catch (IOException e) {
        	
            System.out.println("=> ClientN > ClientN");
            System.out.println("=> IOException - if an I/O error occurs when creating the input stream, the socket is closed, the socket is not connected, or the socket input has been shutdown using shutdownInput()");
            System.out.println("=> IOException - if an I/O error occurs when creating the output stream or if the socket is not connected.");
            //e.printStackTrace();
        }
    }
    
    public void esperarMensagem() {
        System.out.println("=> Aguardando mensagem");

        try {
            
            ultimaFraseRecebida = entrada.nextLine();
        	System.out.println("=> Mensagem recebida (com prefixo): " + ultimaFraseRecebida);
            
        	if(ultimaFraseRecebida.charAt(0) == 'C') {
        		
            	ultimaFraseRecebida = (ultimaFraseRecebida.split("C", 2))[1];
            	
            	if(!ultimaFraseRecebida.equals("###"))
            		ultimaFraseRecebida = "C" + nickname + " >> " + ultimaFraseRecebida;
            }
            
            setChanged();
            notifyObservers();
            
            if(ultimaFraseRecebida.equals("###"))
            	close();
            
        } catch (NoSuchElementException e) {
            
            ultimaFraseRecebida = "###";

            System.out.println("=> ClientN > esperarMensagem");
            System.out.println("=> NoSuchElementException - if no line was found");
            //e.printStackTrace();
        } catch (IllegalStateException e) {
            
            ultimaFraseRecebida = "###";

            System.out.println("=> ClientN > esperarMensagem");
            System.out.println("=> IllegalStateException - if this scanner is closed");
            //e.printStackTrace();
        } catch (Exception e) {
            
            ultimaFraseRecebida = "###";

            System.out.println("=> ClientN > esperarMensagem");
            System.out.println("=> Exception - ");
            //e.printStackTrace();
        }

    }
    
    public void enviarMensagem(String mensagem) {
        saida.println(mensagem);
        
        System.out.println("=> Mensagem enviada ao Cliente " + numeroDoCliente);
    }
    
    public void close() {
    	
        try {
        	System.out.println("=> Cliente finalizando");
			
        	if(conexao.isClosed() == false) {
        		conexao.close();
        		entrada.close();
            	saida.close();
        	}
            
        	System.out.println("=> Cliente finalizado");
			
        } catch (IOException e) {
        	
            System.out.println("=> ClientN > close");
            System.out.println("=> IOException - If an I/O error occurs.");
            System.out.println("=> IOException - if an I/O error occurs when closing this socket.");
            //e.printStackTrace();
        }
		
    }

    ////////////////////////////////////
    
    public String getId() {
        return conexao.getInetAddress().toString();
    }
    
    public int getNumeroDoCliente() {
        return numeroDoCliente;
    }
    
    public String getUltimaFraseRecebida() {
        return ultimaFraseRecebida;
    }
    
    public void setUltimaFraseRecebida (String s) {
    	this.ultimaFraseRecebida = s;
    }
    
}

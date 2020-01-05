package chat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Cliente {

	private String ip;
	private int porta;
	private String ultimaFrase;
	
	// Conexao
	private Socket conexao; 
	
	// Ler e escrever
	private Scanner entrada;
	private PrintStream saida;
	
	// Threads
	private ReceberMsg rm;
	private EnviarMsg em;
	
	public Cliente() {
		
		ip = "192.168.0.3";
		porta = 8;
		ultimaFrase = "";
		
		try {
			
			conexao = new Socket(ip, porta);
			entrada = new Scanner(conexao.getInputStream());
            saida = new PrintStream(conexao.getOutputStream());
    		
    		rm = new ReceberMsg(this);
    		rm.start();
    		
    		em = new EnviarMsg(this);
    		em.start();
    		
    		System.out.println("=> Cliente criado");
			
		} catch (UnknownHostException e) {
			// TODO
			System.out.println("=> UnknownHostException - if the IP address of the host could not be determined.");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
            // TODO
            System.out.println("=> FileNotFoundException - If the given file object does not denote an existing, writable regular file and a new regular file of that name cannot be created, or if some other error occurs while opening or creating the file");
            e.printStackTrace();
        } catch (IOException e) {
			// TODO
			System.out.println("=> IOException - if an I/O error occurs when creating the socket.");
			System.out.println("=> IOException - if an I/O error occurs when creating the input stream, the socket is closed, the socket is not connected, or the socket input has been shutdown using shutdownInput()");
            System.out.println("=> IOException - if an I/O error occurs when creating the output stream or if the socket is not connected.");
            e.printStackTrace();
		} catch (SecurityException e) {
			// TODO
			System.out.println("=> SecurityException - if a security manager exists and its checkConnect method doesn't allow the operation.");
            System.out.println("=> SecurityException - If a security manager is present and checkWrite(file.getPath()) denies write access to the file");
            e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO
			System.out.println("=> IllegalArgumentException - if the port parameter is outside the specified range of valid port values, which is between 0 and 65535, inclusive.");
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
            // TODO
            System.out.println("=> IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())");
            e.printStackTrace();
        }
	}
	
	public void esperarMensagem() {
		System.out.println("=> Aguardando mensagem");

		try {
			entrada.hasNextLine();
			String msg = entrada.nextLine();
			System.out.println(msg);
			if(msg == null)
				ultimaFrase = "###";
			
			System.out.println("=> Mensagem recebida");
			
		} catch (IllegalStateException e) {
			// TODO
			System.out.println("=> IllegalStateException - if this scanner is closed");
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			// TODO
			ultimaFrase = "###";
			System.out.println("=> NoSuchElementException - if no line was found");
			e.printStackTrace();
		}
	}
	
	public void enviarMensagem() {
        saida.println(ultimaFrase);
        
        System.out.println("=> Mensagem enviada");
    }
	
	public void close() {
		try {
			
			conexao.close();
			entrada.close();
			saida.close();

			System.out.println("=> Cliente finalizado");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("=> IOException - If an I/O error occurs.");
			System.out.println("=> IOException - if an I/O error occurs when closing this socket.");
			e.printStackTrace();
		}
	}
	
	/////////////////////////////////////

	public String getUltimaFrase() {
		return ultimaFrase;
	}

	public void setUltimaFrase(String ultimaFrase) {
		this.ultimaFrase = ultimaFrase;
	}
	
	
}

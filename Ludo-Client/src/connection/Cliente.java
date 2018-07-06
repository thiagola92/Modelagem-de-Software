package connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import outros.Link;

public class Cliente {

	// Preencher no construtor
	private String ip;
	private int porta;
	
	// Informações do jogo
	private int seu_round;
	
	// Informacoes do usuario
	private String nickname;
	private String ultimaFraseEnviada;
	
	// Conexao
	private Socket conexao; 
	
	// Ler e escrever
	private Scanner entrada;
	private PrintStream saida;
	
	// Threads
	private ReceberMsg rm;
	private EnviarMsg em;
	
	@SuppressWarnings("resource")
	public Cliente() {
		
		ip = "192.168.0.3";
		porta = 8;
		
		seu_round = 0;
		ultimaFraseEnviada = "";
		
		try {
			
			conexao = new Socket(ip, porta);
			entrada = new Scanner(conexao.getInputStream());
            saida = new PrintStream(conexao.getOutputStream());
    		
    		System.out.println("Digite seu nickname: ");
    		nickname = (new Scanner(System.in)).nextLine();
    		//System.out.println("=> Nickname: " + nickname);
    		
    		ultimaFraseEnviada = nickname;
    		enviarMensagem();
    		
    		// Esse esperarMensagem serve para pegar o round, olhe no método quando recebe uma mensagem começando com R
    		esperarMensagem();
    		
    		rm = new ReceberMsg(this);
    		rm.start();
    		
    		em = new EnviarMsg(this);
    		em.start();
    		
    		//System.out.println("=> Cliente criado");
			
		} catch (UnknownHostException e) {

			//System.out.println("=> Cliente > Cliente");
			//System.out.println("=> UnknownHostException - if the IP address of the host could not be determined.");
			//e.printStackTrace();
		} catch (FileNotFoundException e) {

			//System.out.println("=> Cliente > Cliente");
            //System.out.println("=> FileNotFoundException - If the given file object does not denote an existing, writable regular file and a new regular file of that name cannot be created, or if some other error occurs while opening or creating the file");
            //e.printStackTrace();
        } catch (IOException e) {

			//System.out.println("=> Cliente > Cliente");
			//System.out.println("=> IOException - if an I/O error occurs when creating the socket.");
			//System.out.println("=> IOException - if an I/O error occurs when creating the input stream, the socket is closed, the socket is not connected, or the socket input has been shutdown using shutdownInput()");
            //System.out.println("=> IOException - if an I/O error occurs when creating the output stream or if the socket is not connected.");
            //e.printStackTrace();
		} catch (SecurityException e) {

			//System.out.println("=> Cliente > Cliente");
			//System.out.println("=> SecurityException - if a security manager exists and its checkConnect method doesn't allow the operation.");
            //System.out.println("=> SecurityException - If a security manager is present and checkWrite(file.getPath()) denies write access to the file");
            //e.printStackTrace();
		} catch (IllegalArgumentException e) {

			//System.out.println("=> Cliente > Cliente");
			//System.out.println("=> IllegalArgumentException - if the port parameter is outside the specified range of valid port values, which is between 0 and 65535, inclusive.");
			//e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {

			//System.out.println("=> Cliente > Cliente");
            //System.out.println("=> IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())");
            //e.printStackTrace();
        }
	}
	
	public void esperarMensagem() {
		
		//System.out.println("=> Aguardando mensagem");

		try {
			
			entrada.hasNextLine();
			String msg = entrada.nextLine();
			//System.out.println("=> Mensagem recebida (com prefixo): " + msg);
			
			if(msg.charAt(0) == 'C') {
				// CHAT
				msg = (msg.split("C", 2))[1];
				System.out.println(msg);
			} else if (msg.charAt(0) == 'G') {
				// GAME UPDATE
				msg = (msg.split("G", 2))[1];
				(Link.getInstance()).loadGame(msg);
			} else if (msg.charAt(0) == 'N') {
				// NEW GAME
				(Link.getInstance()).createBoard();
			} else if (msg.charAt(0) == 'R') {
				// ROUND DO SEU CLIENTE
				msg = (msg.split("R", 2))[1];
				seu_round = Integer.parseInt(msg);
				//System.out.println("=> Round do seu cliente eh: " + seu_round);
			}
			
			if(msg == null)
				ultimaFraseEnviada = "###";
			
		} catch (IllegalStateException e) {
			
			ultimaFraseEnviada = "###";
			
			//System.out.println("=> Cliente > esperarMensagem");
			//System.out.println("=> IllegalStateException - if this scanner is closed");
			//e.printStackTrace();
		} catch (NoSuchElementException e) {
			
			ultimaFraseEnviada = "###";
			
			//System.out.println("=> Cliente > esperarMensagem");
			//System.out.println("=> NoSuchElementException - if no line was found");
			//e.printStackTrace();
		}
	}
	
	public void enviarMensagem() {
        saida.println(ultimaFraseEnviada);
        
        //System.out.println("=> Mensagem enviada");
    }
	
	public void close() {
		try {
			
			conexao.close();
			entrada.close();
			saida.close();

			//System.out.println("=> Cliente finalizado");
			
		} catch (IOException e) {
			
			//System.out.println("=> Cliente > close");
			//System.out.println("=> IOException - If an I/O error occurs.");
			//System.out.println("=> IOException - if an I/O error occurs when closing this socket.");
			//e.printStackTrace();
		}
	}
	
	/////////////////////////////////////

	public String getUltimaFraseEnviada() {
		return ultimaFraseEnviada;
	}

	public void setUltimaFraseEnviada(String ultimaFrase) {
		this.ultimaFraseEnviada = ultimaFrase;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSeu_round() {
		return seu_round;
	}
	
	
}

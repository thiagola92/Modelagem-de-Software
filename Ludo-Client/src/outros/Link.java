package outros;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import connection.Cliente;
import view.Board;

public class Link {
	
	private static Link link = new Link();
	
	private Cliente cliente;
	private Board board;
	
	public Link() {
		cliente = new Cliente();
	}
	
	///////////////////////////////////////
	
	public void createBoard() {
		board = new Board();
	}
	
	@SuppressWarnings("resource")
	public void sendGame() {
		
		try {
			//System.out.println("=> Enviando estado do jogo");
			
			File arquivo = new File("src/SaveFileTxT/LudoSavedGame.txt");
			Scanner scan;
			scan = new Scanner(arquivo);
			String msg = "";

			while(scan.hasNextLine())
				msg = msg + scan.nextLine() + " ";
			
			//System.out.println("=> String do jogo: " + msg);
			
			cliente.setUltimaFraseEnviada("G" + msg);
			cliente.enviarMensagem();

		} catch (FileNotFoundException e) {

			//System.out.println("=> Link > sendGame");
			//System.out.println("=> FileNotFoundException - if source is not found");
			//e.printStackTrace();
		} catch (NullPointerException e) {

			//System.out.println("=> Link > sendGame");
			//System.out.println("=> NullPointerException - If the pathname argument is null");
			//e.printStackTrace();
		}
	}
	
	public void loadGame(String msg) {
		
		try {
			//System.out.println("=> Carregando o jogo recebido: " + msg);
			
			String[] pedaco = msg.split(" ");

			ArrayList<Integer> ListOfAllLoadInfo = new ArrayList<Integer>();
			for(int i=0; i < pedaco.length; i++) {
				try {
					ListOfAllLoadInfo.add(Integer.parseInt(pedaco[i]));
				} catch (Exception e) {

					//System.out.println("=> Link > loadGame");
					//System.out.println("=> NumberFormatException - if the string does not contain a parsable integer");
					//e.printStackTrace();
				}
			}
			
			//System.out.println("=> Pegou todos os inteiros");
			
			board.setVisible(false);
			board = new Board(ListOfAllLoadInfo);
			
		} catch(NullPointerException e) {

			//System.out.println("=> Link > sendGame");
			//System.out.println("=> NullPointerException - If the pathname argument is null");
			//e.printStackTrace();
		} catch (SecurityException e) {

			//System.out.println("=> Link > sendGame");
			//System.out.println("=> SecurityException - If a security manager is present and checkWrite(file.getPath()) denies write access to the file");
			//e.printStackTrace();
		}
		
	}
	
	///////////////////////////////////////

	public static Link getInstance() {
		return link;
	}

	public Cliente getCliente() {
		return cliente;
	}
}

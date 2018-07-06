package model;

import java.awt.Color;
import java.util.ArrayList;

public class ConjuntoDePinos {
	PinoEstruturado[] GreenPino = new PinoEstruturado[4];
	PinoEstruturado[] RedPino = new PinoEstruturado[4];
	PinoEstruturado[] BluePino = new PinoEstruturado[4];
	PinoEstruturado[] YellowPino = new PinoEstruturado[4];

	int verde6;
	int vermelho6;
	int azul6;
	int amarelo6;


	private static ConjuntoDePinos cpfirstInstance = null;

	//Singleton da Class -- ConjuntoDePinos
	public static ConjuntoDePinos getInstancce(){
		if(cpfirstInstance == null){

			cpfirstInstance = new ConjuntoDePinos();
		}

		return cpfirstInstance;
	}

	//Esse construtor default de ConjuntoDePinos é usado
	//no caso de um jogo novo e criando os pinos
	//nos lugares iniciais
	public ConjuntoDePinos(){
		verde6=0;
		vermelho6=0;
		azul6=0;
		amarelo6=0;

		for(int i = 1; i < 5; i++){
			this.RedPino[i-1] = new PinoEstruturado();
			this.GreenPino[i-1] = new PinoEstruturado();
			this.BluePino[i-1] = new PinoEstruturado();
			this.YellowPino[i-1] = new PinoEstruturado();
		}

		for(int i = 1; i < 5; i++){
			if( i == 1 ){

				this.RedPino[i-1].setNumero(i);
				this.RedPino[i-1].setColor(Color.red);
				this.RedPino[i-1].setCasa(1);
				this.BluePino[i-1].setNumero(i);
				this.BluePino[i-1].setColor(Color.blue);
				this.BluePino[i-1].setCasa(1);
				this.YellowPino[i-1].setNumero(i);
				this.YellowPino[i-1].setColor(Color.yellow);
				this.YellowPino[i-1].setCasa(1);
				this.GreenPino[i-1].setNumero(i);
				this.GreenPino[i-1].setColor(Color.green);
				this.GreenPino[i-1].setCasa(1);

			}

			else{
				this.RedPino[i-1].setNumero(i);
				this.RedPino[i-1].setColor(Color.red);
				this.RedPino[i-1].setCasa(0);
				this.BluePino[i-1].setNumero(i);
				this.BluePino[i-1].setColor(Color.blue);
				this.BluePino[i-1].setCasa(0);
				this.YellowPino[i-1].setNumero(i);
				this.YellowPino[i-1].setColor(Color.yellow);
				this.YellowPino[i-1].setCasa(0);
				this.GreenPino[i-1].setNumero(i);
				this.GreenPino[i-1].setColor(Color.green);
				this.GreenPino[i-1].setCasa(0);
			}
		}
	}

	public PinoEstruturado getGreen(int numero){
		return this.GreenPino[numero-1];
	}

	public PinoEstruturado getRed(int numero){
		return this.RedPino[numero-1];
	}

	public PinoEstruturado getBlue(int numero){
		return this.BluePino[numero-1];
	}

	public PinoEstruturado getYellow(int numero){
		return this.YellowPino[numero-1];
	}


	//Esse metodo da Classe ConjuntoDePinos é usado
	//no caso de um jogo salvo e criando os pinos
	//nos lugares de acordo com o arquivo salvo
	//Receber uma String com toda as informacoes

	public void LoadGameConjuntoDePinos(ArrayList<Integer> ListOfAllLoadInfo){

		this.RedPino[0].setNumero(ListOfAllLoadInfo.get(0));
		this.RedPino[0].setColor(Color.red);
		this.RedPino[0].setCasa(ListOfAllLoadInfo.get(1));
		this.RedPino[1].setNumero(ListOfAllLoadInfo.get(2));
		this.RedPino[1].setColor(Color.red);
		this.RedPino[1].setCasa(ListOfAllLoadInfo.get(3));
		this.RedPino[2].setNumero(ListOfAllLoadInfo.get(4));
		this.RedPino[2].setColor(Color.red);
		this.RedPino[2].setCasa(ListOfAllLoadInfo.get(5));
		this.RedPino[3].setNumero(ListOfAllLoadInfo.get(6));
		this.RedPino[3].setColor(Color.red);
		this.RedPino[3].setCasa(ListOfAllLoadInfo.get(7));

		this.BluePino[0].setNumero(ListOfAllLoadInfo.get(8));
		this.BluePino[0].setColor(Color.blue);
		this.BluePino[0].setCasa(ListOfAllLoadInfo.get(9));
		this.BluePino[1].setNumero(ListOfAllLoadInfo.get(10));
		this.BluePino[1].setColor(Color.blue);
		this.BluePino[1].setCasa(ListOfAllLoadInfo.get(11));
		this.BluePino[2].setNumero(ListOfAllLoadInfo.get(12));
		this.BluePino[2].setColor(Color.blue);
		this.BluePino[2].setCasa(ListOfAllLoadInfo.get(13));
		this.BluePino[3].setNumero(ListOfAllLoadInfo.get(14));
		this.BluePino[3].setColor(Color.blue);
		this.BluePino[3].setCasa(ListOfAllLoadInfo.get(15));

		this.YellowPino[0].setNumero(ListOfAllLoadInfo.get(16));
		this.YellowPino[0].setColor(Color.yellow);
		this.YellowPino[0].setCasa(ListOfAllLoadInfo.get(17));
		this.YellowPino[1].setNumero(ListOfAllLoadInfo.get(18));
		this.YellowPino[1].setColor(Color.yellow);
		this.YellowPino[1].setCasa(ListOfAllLoadInfo.get(19));
		this.YellowPino[2].setNumero(ListOfAllLoadInfo.get(20));
		this.YellowPino[2].setColor(Color.yellow);
		this.YellowPino[2].setCasa(ListOfAllLoadInfo.get(21));
		this.YellowPino[3].setNumero(ListOfAllLoadInfo.get(22));
		this.YellowPino[3].setColor(Color.yellow);
		this.YellowPino[3].setCasa(ListOfAllLoadInfo.get(23));

		this.GreenPino[0].setNumero(ListOfAllLoadInfo.get(24));
		this.GreenPino[0].setColor(Color.green);
		this.GreenPino[0].setCasa(ListOfAllLoadInfo.get(25));
		this.GreenPino[1].setNumero(ListOfAllLoadInfo.get(26));
		this.GreenPino[1].setColor(Color.green);
		this.GreenPino[1].setCasa(ListOfAllLoadInfo.get(27));
		this.GreenPino[2].setNumero(ListOfAllLoadInfo.get(28));
		this.GreenPino[2].setColor(Color.green);
		this.GreenPino[2].setCasa(ListOfAllLoadInfo.get(29));
		this.GreenPino[3].setNumero(ListOfAllLoadInfo.get(30));
		this.GreenPino[3].setColor(Color.green);
		this.GreenPino[3].setCasa(ListOfAllLoadInfo.get(31));
	}

}

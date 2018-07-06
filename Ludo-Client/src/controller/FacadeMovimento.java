package controller;

import model.MovesAndRules;
import model.Score;
import model.Subject;
import view.Dice;
import view.DrawingBoard;

public class FacadeMovimento{
	private int CoordinateX;
	private int CoordinateY;
	Dice dice;

	private static FacadeMovimento fmfirstInstance = null;

	//Singleton da Class -- FacadeMovimento
	public static FacadeMovimento getInstance(){
		if(fmfirstInstance == null){

			fmfirstInstance = new FacadeMovimento();
		}

		return fmfirstInstance;
	}

	public FacadeMovimento(){
		setCoordinateX(-1);
		setCoordinateY(-1);
		dice = Dice.getInstance();
	}

	public void RollDice(){
		dice.generatingRandomNumberDice();
	}

	public int getCoordinateY() {
		return CoordinateY;
	}

	public void setCoordinateY(int coordinateY) {
		CoordinateY = coordinateY;
	}

	public int getCoordinateX() {
		return CoordinateX;
	}

	public void setCoordinateX(int coordinateX) {
		CoordinateX = coordinateX;
	}

	public boolean setClickedCoordinates(int cX, int cY){
		setCoordinateX(cX);
		setCoordinateY(cY); 
		new MovesAndRules();
		MovesAndRules.getInstancce().InicializaMovesAndRules(cX, cY, dice.getRandNum());

		if(MovesAndRules.getInstancce().JustDoIt() == true){
			//Update os valores de Score
			Score.getInstancce().updateScore();
			return true;
		}

		else{
			return false;
		}

	}

	public static void FacadeRegistraObserver(Subject PinoEstruturado){
		DrawingBoard.getInstancce().addTabuleiro(PinoEstruturado);
	}
}

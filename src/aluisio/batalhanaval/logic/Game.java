package aluisio.batalhanaval.logic;

public class Game {
	private boolean turn;
	private int ganhador;
	
	
	public Game() {
		this.turn = false;
		this.ganhador = 10;
	}
	
	public void start() {
		
	}
	
	public void run() {
		while(this.ganhador != 0 || this.ganhador != 1) {
			
			
			
		}
	}
	
	public void changeTurn() {
		if(this.turn == false) {
			this.turn = true;
		}
		else {
			this.turn = false;
		}
	}
	
	public boolean isTurn() {
		return this.turn;
	}
	
	
}

package aluisio.batalhanaval.logic;

import aluisio.batalhanaval.GUI.GameWindow;
import aluisio.batalhanaval.entities.Enemy;
import aluisio.batalhanaval.entities.Player;

public class Game {
	private boolean turn;
	private GameWindow janela;
	private Player player;
	private Enemy enemy;
	private GAMESTATE state;
	
	public enum GAMESTATE{
		SHIP_PLACEMENT,
		PLAYER_ATTACK,
		ENEMY_ATTACK,
		RESTART,
		ENDED
	}
	
	public Game() {
		this.turn = false;
		this.player = new Player();
		this.enemy = new Enemy();
		this.state = GAMESTATE.SHIP_PLACEMENT;
	}
	
	public void start() {
		this.janela = new GameWindow(600, 1024, player.getGrid(), enemy.getGrid(), this);
		this.janela.display();
	}
	
	public void run() {
		while(this.state != GAMESTATE.ENDED) {
			
			
			
		}
	}
	
	public void restartGame() {
		this.turn = false;
		this.state = GAMESTATE.SHIP_PLACEMENT;
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

	public GAMESTATE getState() {
		return this.state;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

}

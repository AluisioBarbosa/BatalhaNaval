package aluisio.batalhanaval.logic;

import aluisio.batalhanaval.GUI.GameWindow;
import aluisio.batalhanaval.entities.Enemy;
import aluisio.batalhanaval.entities.Player;

public class Game implements Runnable {
	private GameWindow janela;
	private Player player;
	private Enemy enemy;
	private GAMESTATE state;
	private ORIENTATION orientation;
	private Cheats cheats;
	private int gridSize;
	
	public enum GAMESTATE{
		SHIP_PLACEMENT,
		PLAYER_ATTACK,
		ENEMY_ATTACK,
		ENDED
	}
	
	public enum ORIENTATION{
		HORIZONTAL,
		VERTICAL
	}
	
	public Game(int gridSize) {
		this.player = new Player(gridSize);
		this.gridSize = gridSize;
		this.enemy = new Enemy(gridSize);
		this.cheats = new Cheats();
		this.state = GAMESTATE.SHIP_PLACEMENT;
		this.orientation = ORIENTATION.HORIZONTAL;
	}
	
	public void start() {
		setupEnemyShips();
		this.janela = new GameWindow(600, 1024, player.getGrid(), enemy.getGrid(), this, this.gridSize);
		this.janela.display();
		Thread gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() {
		while(this.state != GAMESTATE.ENDED) {
			
			if (janela != null) {
	            int playerPontos = enemy.getGrid().getShipsDestroyed();
	            int inimigoPontos = player.getGrid().getShipsDestroyed();
	            janela.atualizarTextoPlacar(playerPontos, inimigoPontos);
	        }
			
			if (enemy.getGrid().getShipsDestroyed() >= 5) {
	            System.out.println("VITÓRIA DO JOGADOR!");
	            this.showGameOverPopup("VOCE GANHOU");
	        } 
	        else if (player.getGrid().getShipsDestroyed() >= 5) {
	            System.out.println("VITÓRIA DO INIMIGO!");
	            this.showGameOverPopup("VOCE PERDEU");
	        }
			
			
			if (this.state == GAMESTATE.ENEMY_ATTACK) {
				try {
					Thread.sleep(1000); 
					enemy.shoot(player.getGrid());
					this.state = GAMESTATE.PLAYER_ATTACK;
					
					if (janela != null) {
						janela.repaint();
					}
					
					
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try { Thread.sleep(30); } catch (InterruptedException e) {}			
		}
	}
	
	public void restartGame() {
		this.state = GAMESTATE.SHIP_PLACEMENT;
		this.player = new Player(this.gridSize);
		this.enemy = new Enemy(this.gridSize);
		this.orientation = ORIENTATION.HORIZONTAL;
		
		setupEnemyShips();
		
		if (this.janela != null) {
	        this.janela.updateWindowGrids(player.getGrid(), enemy.getGrid());
	    }
		
	}
	
	public void changeTurn() {
		if(this.state == GAMESTATE.PLAYER_ATTACK) {
			this.state = GAMESTATE.ENEMY_ATTACK;
		}
		else if (this.state == GAMESTATE.ENEMY_ATTACK){
			this.state = GAMESTATE.PLAYER_ATTACK;
		}
	}
	
	public void processClick(int col, int lin, boolean isMyGrid) {
		if (state == GAMESTATE.SHIP_PLACEMENT && isMyGrid) {
	        int shipSize = 3; 
	        int currentGridSize = player.getGrid().getSize(); 

	        int finalCol = col;
	        int finalLin = lin;

	        if (this.orientation == ORIENTATION.HORIZONTAL) {
	            if (finalCol > currentGridSize - shipSize) {
	                finalCol = currentGridSize - shipSize;
	            }
	        } else {
	            if (finalLin > currentGridSize - shipSize) {
	                finalLin = currentGridSize - shipSize;
	            }
	        }

	        player.placeShip(finalCol, finalLin, this.orientation);
	        if(player.getTotalShips() >= 5) {
	        	this.state = GAMESTATE.PLAYER_ATTACK;
	        }
	    }
		else if(state == GAMESTATE.PLAYER_ATTACK && isMyGrid == false) {
			Field target = enemy.getGrid().getField(col, lin);
			if(target != null) {
				if(target.isFieldHit() == false) {
					player.shoot(col, lin, enemy.getGrid());
					if(cheats.isInfiniteShots() == false) {
						changeTurn();
					}
				}
				else {
					System.out.println("Voce ja atirou nesse campo");
				}
			}
		}
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
	
	public ORIENTATION getOrientation() {
		return this.orientation;
	}
	
	public Cheats getCheats() {
		return this.cheats;
	}
	
	private void showGameOverPopup(String message) {
	    int response = javax.swing.JOptionPane.showConfirmDialog(
	        null, 
	        message + "\n\nDeseja jogar novamente?", 
	        "Fim de Jogo", 
	        javax.swing.JOptionPane.YES_NO_OPTION
	    );

	    if (response == javax.swing.JOptionPane.YES_OPTION) {
	        this.restartGame();
	    } 
	    else {
	        this.state = GAMESTATE.ENDED;
	    }
	}
	
	public void changeOrientation() {
		if(this.orientation == ORIENTATION.HORIZONTAL) {
			this.orientation = ORIENTATION.VERTICAL;
		}
		else {
			this.orientation = ORIENTATION.HORIZONTAL;
		}
	}
	
	private void setupEnemyShips() {
	    int shipsToPlace = 5;
	    int placedCount = 0;
	    int gridSize = enemy.getGrid().getSize();

	    while (placedCount < shipsToPlace) {
	        int x = (int) (Math.random() * gridSize);
	        int y = (int) (Math.random() * gridSize);
	        

	        ORIENTATION orientation;
	        double randValue = Math.random();
	        
	        if (randValue > 0.5) {
	        	orientation = ORIENTATION.HORIZONTAL;
	        } else {
	        	orientation = ORIENTATION.VERTICAL;
	        }

	        boolean success = enemy.placeShip(x, y, orientation);
	        
	        if (success == true) {
	            placedCount++;
	        }
	    }
	    System.out.println("Inimigo posicionou todos os navios!");
	}
}

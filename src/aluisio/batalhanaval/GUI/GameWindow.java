package aluisio.batalhanaval.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import aluisio.batalhanaval.logic.Game;
import aluisio.batalhanaval.logic.Grid;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	private GridPanel painelPlayer;
	private GridPanel painelEnemy;
	private JLabel lblPlacar;
	private JButton gameRestart;
	
	
	public GameWindow(int height, int width, Grid playerGrid, Grid enemyGrid, Game game, int gridSize) {
	    setTitle("Batalha Naval");
	    setSize(width, height);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    
	    setLayout(new BorderLayout());

	    JPanel containerGrids = new JPanel();
	    containerGrids.setLayout(new GridLayout(1, 2, 10, 2));
	    
	    this.painelPlayer = new GridPanel(playerGrid, game, true, gridSize);
	    game.getPlayer().setGrid(playerGrid);
	    this.painelEnemy = new GridPanel(enemyGrid, game, false, gridSize);
	    game.getEnemy().setGrid(enemyGrid);
	    
	    containerGrids.add(painelPlayer);
	    containerGrids.add(painelEnemy);
	    add(containerGrids, BorderLayout.CENTER);
	    
	    
	    lblPlacar = new JLabel("Player 0 x 0 Inimigo", SwingConstants.CENTER);
	    lblPlacar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
	    add(lblPlacar, BorderLayout.NORTH);
	    
	    gameRestart = new JButton("Restart Game");
	    gameRestart.addActionListener(e -> game.restartGame());
	    JPanel painelInferior = new JPanel();
	    painelInferior.add(gameRestart);
	    add(painelInferior, BorderLayout.SOUTH);
	    
	    this.addKeyListener(new KeyAdapter() {
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_Z) {
	                game.changeOrientation();
	            }
	            else if (e.getKeyCode() == KeyEvent.VK_X) {
	                game.getCheats().changeWallHack();
	            }
	            else if (e.getKeyCode() == KeyEvent.VK_C) {
	                game.getCheats().changeInfiniteShots();
	            }
	        }
	        
	    });
	    this.setFocusable(true);
	    
	}

	public void display() {
		setVisible(true);
	}
	
	public void atualizarTextoPlacar(int playerPoints, int enemyPoints) {
	    lblPlacar.setText("Player " + playerPoints + " x " + enemyPoints + " Inimigo");
	}
	
	public void updateWindowGrids(Grid newPlayerGrid, Grid newEnemyGrid) {
		this.painelPlayer.setGrid(newPlayerGrid);
		this.painelEnemy.setGrid(newEnemyGrid);
		this.requestFocusInWindow();
		this.revalidate();
	    this.repaint();
	}
}

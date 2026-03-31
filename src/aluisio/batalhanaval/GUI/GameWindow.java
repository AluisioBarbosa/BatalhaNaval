package aluisio.batalhanaval.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import aluisio.batalhanaval.logic.Game;
import aluisio.batalhanaval.logic.Grid;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	private GridPanel painelEsquerdo;
	private GridPanel painelDireito;
	
	
	public GameWindow(int height, int width, Grid playerGrid, Grid enemyGrid, Game game) {
	    setTitle("Batalha Naval");
	    setSize(width, height);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    
	    setLayout(new BorderLayout());

	    JPanel containerGrids = new JPanel();
	    containerGrids.setLayout(new GridLayout(1, 2, 10, 2));
	    
	    this.painelEsquerdo = new GridPanel(playerGrid, game);
	    this.painelDireito = new GridPanel(enemyGrid, game);
	    
	    containerGrids.add(painelEsquerdo);
	    containerGrids.add(painelDireito);
	    add(containerGrids, BorderLayout.CENTER);

	    JLabel lblPlacar = new JLabel("Player 0 x 0 Inimigo", SwingConstants.CENTER);
	    lblPlacar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
	    add(lblPlacar, BorderLayout.NORTH);

	    JPanel painelInferior = new JPanel();
	    painelInferior.add(new JButton("Reiniciar Jogo"));
	    painelInferior.add(new JLabel("Sua vez de atirar!"));
	    add(painelInferior, BorderLayout.SOUTH);
	}
	public GridPanel getPainelEsquerdo() {
		return painelEsquerdo;
	}

	public void setPainelEsquerdo(GridPanel painelEsquerdo) {
		this.painelEsquerdo = painelEsquerdo;
	}

	public GridPanel getPainelDireito() {
		return painelDireito;
	}

	public void setPainelDireito(GridPanel painelDireito) {
		this.painelDireito = painelDireito;
	}

	public void display() {
		setVisible(true);
	}
}

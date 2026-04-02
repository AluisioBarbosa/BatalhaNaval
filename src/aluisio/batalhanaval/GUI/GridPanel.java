package aluisio.batalhanaval.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import aluisio.batalhanaval.entities.Ship;
import aluisio.batalhanaval.logic.Field;
import aluisio.batalhanaval.logic.Game;
import aluisio.batalhanaval.logic.Grid;

@SuppressWarnings("serial")
public class GridPanel extends JPanel {
	private Grid grid;
	private int gridSize;
	private Game game;
	private int hoverColuna = -1;
	private int hoverLinha = -1;
	private boolean myGrid;
	
	public GridPanel(Grid grid, Game game, boolean myGrid) {
		this.grid = grid;	
		this.gridSize = 7;
		this.game = game;
		this.myGrid = myGrid;
		
		addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            clicar(e.getX(), e.getY());
	        }
	        
	        @Override
	        public void mouseExited(MouseEvent e) {
	            hoverColuna = -1;
	            hoverLinha = -1;
	            repaint();
	        }
	    });
		
		addMouseMotionListener(new MouseAdapter() {
	        @Override
	        public void mouseMoved(MouseEvent e) {
	            atualizarHover(e.getX(), e.getY());
	        }
	    });
	}
	
	public void clicar(int x, int y) {
		int tamQuadrado = Math.min(getWidth() / gridSize, getHeight() / gridSize);
		int offsetX = (getWidth() - tamQuadrado * gridSize) / 2;
		int offsetY = (getHeight() - tamQuadrado * gridSize) / 2;

		int col = (x - offsetX) / tamQuadrado;
		int lin = (y - offsetY) / tamQuadrado;

		if (col >= 0 && col < gridSize && lin >= 0 && lin < gridSize) {
			if (game.getState() == Game.GAMESTATE.SHIP_PLACEMENT) {
				if(game.getPlayer().getGrid().getField(col, lin).isShip() == null && this.myGrid == true) {
					System.out.println("Posicionando barco em: " + col + "," + lin);
					game.getPlayer().getGrid().getField(col, lin).setShip(new Ship());
				}
			} 
			else if (game.getState() == Game.GAMESTATE.PLAYER_ATTACK) {
				if(this.myGrid == false) {
					System.out.println("Atirando em: " + col + "," + lin);
					game.getPlayer().shoot(col, lin, game.getEnemy().getGrid());
				}
			}
			repaint();
		}
	}
	
	private void atualizarHover(int x, int y) {
	    int tamQuadrado = Math.min(getWidth() / gridSize, getHeight() / gridSize);
	    int offsetX = (getWidth() - tamQuadrado * gridSize) / 2;
	    int offsetY = (getHeight() - tamQuadrado * gridSize) / 2;

	    int col = (x - offsetX) / tamQuadrado;
	    int lin = (y - offsetY) / tamQuadrado;

	    if (col != hoverColuna || lin != hoverLinha) {
	        if (col >= 0 && col < gridSize && lin >= 0 && lin < gridSize) {
	            hoverColuna = col;
	            hoverLinha = lin;
	        } else {
	            hoverColuna = -1;
	            hoverLinha = -1;
	        }
	        repaint();
	    }
	}

	public int getGridSize() {
		return gridSize;
	}

	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    int tamQuadrado = Math.min(getWidth() / gridSize, getHeight() / gridSize);
	    int offsetX = (getWidth() - tamQuadrado * gridSize) / 2;
	    int offsetY = (getHeight() - tamQuadrado * gridSize) / 2;

	    for (int i = 0; i < gridSize; i++) {
	        for (int j = 0; j < gridSize; j++) {
	            Field f = grid.getField(j, i);
	            int x = offsetX + j * tamQuadrado;
	            int y = offsetY + i * tamQuadrado;
	            
	            if (f != null && f.isFieldHit()) {
	                g.setColor(Color.RED);
	            } 
	            else if(f.isShip() != null && this.myGrid == true) {
	            	g.setColor(Color.CYAN);
	            }
	            else {
	                g.setColor(new Color(30, 144, 255));
	            }

	            
	            if (j == hoverColuna && i == hoverLinha) {
	                g.setColor(g.getColor().brighter());
	                g.fillRect(x - 2, y - 2, tamQuadrado + 4, tamQuadrado + 4);
	                g.setColor(Color.YELLOW);
	                g.drawRect(x - 2, y - 2, tamQuadrado + 4, tamQuadrado + 4);
	            } else {
	                g.fillRect(x, y, tamQuadrado, tamQuadrado);
	                g.setColor(Color.WHITE);
	                g.drawRect(x, y, tamQuadrado, tamQuadrado);
	            }
	        }
	    }
	}
	
}

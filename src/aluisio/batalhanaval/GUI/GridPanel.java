package aluisio.batalhanaval.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import aluisio.batalhanaval.logic.Field;
import aluisio.batalhanaval.logic.Game;
import aluisio.batalhanaval.logic.Game.ORIENTATION;
import aluisio.batalhanaval.logic.Grid;

@SuppressWarnings("serial")
public class GridPanel extends JPanel {
	private Grid grid;
	private int gridSize;
	private Game game;
	private int hoverColuna = -1;
	private int hoverLinha = -1;
	private boolean isMyGrid;
	
	public GridPanel(Grid grid, Game game, boolean myGrid, int gridSize) {
		this.grid = grid;	
		this.gridSize = gridSize;
		this.game = game;
		this.isMyGrid = myGrid;
		
		addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            click(e.getX(), e.getY());
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
	
	
	private Point convertPixelToGrid(int x, int y) {
	    int tamQuadrado = Math.min(getWidth() / gridSize, getHeight() / gridSize);
	    int offsetX = (getWidth() - tamQuadrado * gridSize) / 2;
	    int offsetY = (getHeight() - tamQuadrado * gridSize) / 2;

	    int col = (x - offsetX) / tamQuadrado;
	    int lin = (y - offsetY) / tamQuadrado;

	    if (col >= 0 && col < gridSize && lin >= 0 && lin < gridSize) {
	        return new Point(col, lin);
	    }
	    return null; 
	}
	
	public void click(int x, int y) {
		Point p =  convertPixelToGrid(x, y);
		
		int col = p.x;
		int lin = p.y;
		switch (game.getState()) {
			case SHIP_PLACEMENT:
				game.processClick(col, lin, isMyGrid);
				break;
			case PLAYER_ATTACK:
				game.processClick(col, lin, isMyGrid);
				break;
			default:
				System.out.println("Error");
				break;
		}
		repaint();
	}
	
	private void atualizarHover(int x, int y) {
	    Point p = convertPixelToGrid(x, y);

	    if (p != null) {
	        int col = p.x;
	        int lin = p.y;
	        int shipSize = 3;

	        if (game.getState() == Game.GAMESTATE.SHIP_PLACEMENT) {
	            if (game.getOrientation() == Game.ORIENTATION.HORIZONTAL) {
	                if (col > gridSize - shipSize) {
	                    col = gridSize - shipSize;
	                }
	            } 
	            else {
	                if (lin > gridSize - shipSize) {
	                    lin = gridSize - shipSize;
	                }
	            }
	        }

	        if (col != hoverColuna || lin != hoverLinha) {
	            hoverColuna = col;
	            hoverLinha = lin;
	            repaint();
	        }
	    }
	}

	public int getGridSize() {
		return gridSize;
	}

	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}
	
	
	
	public Grid getGrid() {
		return grid;
	}


	public void setGrid(Grid grid) {
		this.grid = grid;
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

	             
	            if(f.hasShip() != null && f.isFieldHit() == true && this.isMyGrid == false) {
	            	g.setColor(Color.RED);
	            }
	            else if (f.hasShip() != null && this.isMyGrid == true && f.isFieldHit() == false) {
	                g.setColor(Color.CYAN);
	            }
	            else if(f.hasShip() != null && this.isMyGrid == true && f.isFieldHit() == true){
	            	g.setColor(Color.RED);
	            }
	            else if (f != null && f.isFieldHit()) {
	                g.setColor(Color.GRAY);
	            }
	            else {
	                g.setColor(new Color(30, 144, 255));
	            }
	            
	            if(f.hasShip() != null && f.isFieldHit() == false && this.isMyGrid == false && game.getCheats().isWallHack() == true) {
	            	g.setColor(game.getCheats().getWallHackColor());
	            }

	            boolean isInHoverRange = false;
	            
	            if (game.getState() == Game.GAMESTATE.SHIP_PLACEMENT && isMyGrid) {
	                ORIENTATION orientation = game.getOrientation();
	                int shipSize = 3;

	                if (orientation == ORIENTATION.HORIZONTAL) {
	                    if (i == hoverLinha && j >= hoverColuna && j < hoverColuna + shipSize) {
	                        isInHoverRange = true;
	                    }
	                } 
	                else {
	                    if (j == hoverColuna && i >= hoverLinha && i < hoverLinha + shipSize) {
	                        isInHoverRange = true;
	                    }
	                }
	            }
	            else if (game.getState() == Game.GAMESTATE.PLAYER_ATTACK && !isMyGrid) {
	                if (j == hoverColuna && i == hoverLinha) {
	                    isInHoverRange = true;
	                }
	            }

	            if (isInHoverRange == true) {
	                g.setColor(g.getColor().brighter());
	                g.fillRect(x - 2, y - 2, tamQuadrado + 4, tamQuadrado + 4);
	                g.setColor(Color.YELLOW);
	                g.drawRect(x - 2, y - 2, tamQuadrado + 4, tamQuadrado + 4);
	            } 
	            else {
	                g.fillRect(x, y, tamQuadrado, tamQuadrado);
	                g.setColor(Color.WHITE);
	                g.drawRect(x, y, tamQuadrado, tamQuadrado);
	            }
	        }
	    }
	}
	
}

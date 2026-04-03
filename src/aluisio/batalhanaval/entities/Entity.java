package aluisio.batalhanaval.entities;

import aluisio.batalhanaval.logic.Game.ORIENTATION;
import aluisio.batalhanaval.logic.Grid;

public class Entity {
	private Grid grid;
	private int totalShips;
	
	public Entity() {
		this.grid = new Grid();
		this.totalShips = 0;
	}

	public Grid getGrid() {
		return grid;
	}
	
	public boolean shoot() {
		return true;
	}
	
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	public boolean placeShip(int x, int y, ORIENTATION orientation) {
	    Ship newShip = new Ship();
	    boolean success = this.grid.addShip(x, y, newShip, orientation);
	    
	    if (success == true) {
	        this.totalShips++;
	        return true;
	    }
	    
	    return false;
	}
	
	public int getTotalShips() {
        return totalShips;
    }
	
	
}

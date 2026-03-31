package aluisio.batalhanaval.entities;

import aluisio.batalhanaval.logic.Grid;

public class Entity {
	private Grid grid;
	
	public Entity() {
		this.grid = new Grid();
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}
}

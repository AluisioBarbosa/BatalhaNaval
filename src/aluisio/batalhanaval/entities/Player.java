package aluisio.batalhanaval.entities;

import aluisio.batalhanaval.logic.Grid;

public class Player extends Entity {
	
	public Player() {
		super();
	}
	public boolean shoot(int x, int y, Grid inimigo) {
		boolean tiro = inimigo.updateGrid(inimigo.getField(x, y));
		
		if(tiro == false) {
			return false;
		}
		return true;
	}
}

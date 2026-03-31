package aluisio.batalhanaval.entities;

import java.util.Random;
import aluisio.batalhanaval.logic.Grid;

public class Enemy extends Entity {
	public Enemy() {
		super();
	}
	
	public void shoot(Grid grid, int gridSize){
		Random random = new Random();
		boolean tiroAleatorio = false;
		do{
			int tiroAleatorioX = random.nextInt(gridSize);
			int tiroAleatorioY = random.nextInt(gridSize);
			tiroAleatorio = grid.updateGrid(grid.getField(tiroAleatorioX, tiroAleatorioY));
		}while(tiroAleatorio != true);
	}
}

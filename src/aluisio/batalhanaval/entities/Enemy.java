package aluisio.batalhanaval.entities;

import java.util.Random;
import aluisio.batalhanaval.logic.Grid;

public class Enemy extends Entity {
	public void shoot(Grid grid, int gridSize){
		Random random = new Random();
		int tiroAleatorioX = random.nextInt(gridSize);
		int tiroAleatorioY = random.nextInt(gridSize);
		grid.updateGrid(grid.getField(tiroAleatorioX, tiroAleatorioY));
		
		
	}
}

package aluisio.batalhanaval.entities;

import java.util.Random;
import aluisio.batalhanaval.logic.Grid;

public class Enemy extends Entity {
	public Enemy(int gridSize) {
		super(gridSize);
	}
	
	public boolean shoot(Grid gridPlayer){
		Random random = new Random();
		int gridSize = gridPlayer.getSize();
		boolean tiroAleatorio = false;
		do{
			int tiroAleatorioX = random.nextInt(gridSize);
			int tiroAleatorioY = random.nextInt(gridSize);
			tiroAleatorio = gridPlayer.updateGrid(gridPlayer.getField(tiroAleatorioX, tiroAleatorioY));
		}while(tiroAleatorio != true);
		return true;
	}
}

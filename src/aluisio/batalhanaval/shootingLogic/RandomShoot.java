package aluisio.batalhanaval.shootingLogic;

import java.util.Random;

import aluisio.batalhanaval.logic.Field;
import aluisio.batalhanaval.logic.Grid;

public class RandomShoot implements Shooting {

	@Override
	public boolean shoot(Grid gridPlayer) {
		Random random = new Random();
		int gridSize = gridPlayer.getSize();
		int tiroAleatorioX;
		int tiroAleatorioY;
		boolean tiroAleatorio = false;
		do{
			tiroAleatorioX = random.nextInt(gridSize);
			tiroAleatorioY = random.nextInt(gridSize);
			Field field = gridPlayer.getField(tiroAleatorioX, tiroAleatorioY);
			tiroAleatorio = gridPlayer.updateGrid(field);
		}while(tiroAleatorio != true);
		
		if(gridPlayer.getField(tiroAleatorioX, tiroAleatorioY).hasShip() != null) {
			return true;
		}
		
		return false;
	}

}

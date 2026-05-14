package aluisio.batalhanaval.shootingLogic;

import aluisio.batalhanaval.logic.DIFICULTY;

public class ShootingFactory {
	
	public static Shooting create(DIFICULTY dificulty, int gridSize) {
		switch(dificulty) {
		case EASY:
			return new RandomShoot();
		case MEDIUM:
			return new InteligentShoot();
		case HARD:
			return new HuntAndTarget(gridSize);
		default:
			throw new IllegalArgumentException("This difficulty doesnt exists");
		}
	}
}

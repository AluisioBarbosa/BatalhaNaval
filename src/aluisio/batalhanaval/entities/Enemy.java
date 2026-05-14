package aluisio.batalhanaval.entities;

import aluisio.batalhanaval.logic.DIFICULTY;
import aluisio.batalhanaval.logic.Grid;
import aluisio.batalhanaval.shootingLogic.Shooting;
import aluisio.batalhanaval.shootingLogic.ShootingFactory;

public class Enemy extends Entity {
	
	private Shooting shootStrategy;
	
	public Enemy(int gridSize, DIFICULTY dificulty) {
		super(gridSize);
		this.shootStrategy = ShootingFactory.create(dificulty, gridSize);
	}
	
	
	public boolean shoot(Grid gridPlayer){
		return shootStrategy.shoot(gridPlayer);
	}
	
	
}

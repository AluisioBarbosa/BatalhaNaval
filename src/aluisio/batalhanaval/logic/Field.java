package aluisio.batalhanaval.logic;

import aluisio.batalhanaval.entities.Ship;

public class Field {
	private Ship shipPresent;
	private boolean fieldHit;
	
	public Field(){
		this.fieldHit = false;
	}

	public Ship hasShip() {
		// se nao tiver navio vai retornar null
		return shipPresent;
	}

	public void setShip(Ship ship) {
		this.shipPresent = ship;
	}

	public boolean isFieldHit() {
		return fieldHit;
	}

	public void setFieldHit() {
		this.fieldHit = true;
	}
	
}

package aluisio.batalhanaval.entities;

public class Ship {
	private int size;
	private int hits;
	
	public Ship() {
		this.size = 3;
		this.hits = 0;
	}
	
	public void takeHit() {
		this.hits +=1;
	}
	
	public boolean isDestroyed() {
		if(this.hits >= size) {
			return true;
		}
		return false;
	}
}

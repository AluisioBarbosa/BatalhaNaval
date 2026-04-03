package aluisio.batalhanaval.entities;

public class Ship {
	private int size;
	private int hits;
	
	public Ship(int size) {
		this.size = size;
		this.hits = 0;

	}
	
	public Ship() {
		this.size = 3;
		this.hits = 0;
	}
	
	public void takeHit() {
		if(this.isDestroyed() == false) {
			this.hits +=1;
		}
	}
	
	public boolean isDestroyed() {
		if(this.hits >= size) {
			return true;
		}
		return false;
	}
	public int getSize() {
        return size;
    }

    public int getHits() {
        return hits;
    }
    
}

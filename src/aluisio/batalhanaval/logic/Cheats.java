package aluisio.batalhanaval.logic;

import java.awt.Color;

public class Cheats {
	private boolean infiniteShots;
	private boolean wallHack;
	private Color wallHackColor;
	
	public Cheats() {
		this.infiniteShots = false;
		this.wallHack = false;
		this.wallHackColor = Color.WHITE;
	}

	public boolean isInfiniteShots() {
		return infiniteShots;
	}

	public void changeInfiniteShots() {
		if(this.infiniteShots == false) {
			this.infiniteShots = true;
		}
		else {
			this.infiniteShots = false;
		}
	}

	public boolean isWallHack() {
		return wallHack;
	}

	public void changeWallHack() {
		if(this.wallHack == false) {
			this.wallHack = true;
		}
		else {
			this.wallHack = false;
		}
	}

	public Color getWallHackColor() {
		return wallHackColor;
	}


	
	
}

package aluisio.batalhanaval.shootingLogic;

import java.awt.Point;
import java.util.ArrayList;

import aluisio.batalhanaval.entities.Ship;

public class Hit {
	public enum SHOOTORIENTATION{
		UP,
		LEFT,
		RIGHT,
		DOWN
	}
	private Ship shipHit;
	private ArrayList <SHOOTORIENTATION> shootOrientation = new ArrayList<>();
	private Point firstHit;
	private Point lastHit;
	
	public Hit(Ship ship, Point firstHit) {
		this.shipHit = ship;
		this.firstHit = firstHit;
		this.lastHit = firstHit;
		shootOrientation.add(SHOOTORIENTATION.RIGHT);
		shootOrientation.add(SHOOTORIENTATION.LEFT);
		shootOrientation.add(SHOOTORIENTATION.UP);
		shootOrientation.add(SHOOTORIENTATION.DOWN);
	}
	
	public boolean isDestroyed() {
		return this.shipHit.isDestroyed();
	}
	
	public Point getFirstHit(){
		return this.firstHit;
	}
	
	public Point getLastHit() {
		return this.lastHit;
	}
	
	public void setLastHit(Point lastHit) {
		this.lastHit = lastHit;
	}
	
	public SHOOTORIENTATION getOrientation() {
		return shootOrientation.getFirst();
	}
	
	public boolean hasOrientation() {
		return !shootOrientation.isEmpty();
	}

	public void removeOrientation() {
		shootOrientation.removeFirst();
	}
	
}

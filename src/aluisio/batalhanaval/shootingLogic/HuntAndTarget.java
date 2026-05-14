package aluisio.batalhanaval.shootingLogic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import aluisio.batalhanaval.logic.Field;
import aluisio.batalhanaval.logic.Grid;
import aluisio.batalhanaval.shootingLogic.Hit.SHOOTORIENTATION;

public class HuntAndTarget implements Shooting {

	private ArrayList<Point> inteligentShootCoords = new ArrayList<>();
	private ArrayList<Hit> hits = new ArrayList<>();
	private Shooting fallback = new RandomShoot();
	
	
	public HuntAndTarget(int gridSize) {
		for(int col = 0; col < gridSize; col++) {
			for(int lin = 0; lin < gridSize; lin++) {
				if (col % 2 == 0 && lin % 2 == 1) {
					inteligentShootCoords.add(new Point(col, lin));
				}
				else if(col % 2 == 1 && lin % 2 == 0) {
					inteligentShootCoords.add(new Point(col, lin));
				}
			}
		}
	}
	
	@Override
	public boolean shoot(Grid gridPlayer) {
		while (!hits.isEmpty()) {
	        Hit hit = hits.getFirst();

	        if (hit.isDestroyed()) {
	            hits.removeFirst();
	            continue;
	        }

	        while (hit.hasOrientation()) {
	            SHOOTORIENTATION orientation = hit.getOrientation();
	            Point target = new Point(hit.getLastHit().x, hit.getLastHit().y);

	            switch (orientation) {
	                case RIGHT: 
	                	target.x += 1;
	                	break;
	                case LEFT:
	                	target.x -= 1; 
	                	break;
	                case UP:    
	                	target.y -= 1;
	                	break;
	                case DOWN:  
	                	target.y += 1; 
	                	break;
	            }
	            
	            // se a posicao nao for valida, vai pra prox
	            if (gridPlayer.checkValidPosition(target) == false) {
	                hit.removeOrientation();
	                hit.setLastHit(hit.getFirstHit()); 
	                continue;
	            }

	            Field field = gridPlayer.getField(target.x, target.y);

	            // se ja tiver atirado, vai pro outro lado
	            if (field.isFieldHit()) {
	                hit.removeOrientation();
	                hit.setLastHit(hit.getFirstHit());
	                continue;
	            }

	            gridPlayer.updateGrid(field);

	            if (field.hasShip() != null) {
	                // acertou o tiro
	                hit.setLastHit(target);
	                if (hit.isDestroyed()) {
	                    hits.removeFirst();
	                }
	                return true; 
	            } else {
	                // errou o tiro, volta pro primeiro hit que deu
	                hit.removeOrientation();
	                hit.setLastHit(hit.getFirstHit());
	                return false; 
	            }
	        }

	        // se por algum motivo acabar as 4 direções e nao tiver destruido, remove
	        hits.removeFirst();
	    }

	    // Atira nas "coordenadas inteligentes" se nao ta no modo de caça
	    while (!inteligentShootCoords.isEmpty()) {
	        Random random = new Random();
	        int aleatorio = random.nextInt(inteligentShootCoords.size());
	        Point coord = inteligentShootCoords.remove(aleatorio);
	        
	        Field field = gridPlayer.getField(coord.x, coord.y);
	        
	        // Se ja tomou um tiro aqui, pega outro
	        if (field.isFieldHit()) {
	        	continue;
	        }
	        
	        gridPlayer.updateGrid(field);
	        
	        if (field.hasShip() != null) {
	            hits.add(new Hit(field.hasShip(), coord));
	            return true;
	        }
	        return false;
	    }

	    // Caso acabe as coordenadas inteligentes e nao tenha hit, atira aleatorio
	    return fallback.shoot(gridPlayer);
	}

}

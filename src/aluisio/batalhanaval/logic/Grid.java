package aluisio.batalhanaval.logic;

import aluisio.batalhanaval.entities.Ship;
import aluisio.batalhanaval.logic.Game.ORIENTATION;

public class Grid {
	private Field grid[][];
	private int size;
	private int shipsDestroyed;
	
	public Grid(int size) {
		this.size = size;
		this.grid = new Field[size][size];
		this.shipsDestroyed = 0;
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.grid[i][j] = new Field();
            }
        }
	}
	
	public boolean updateGrid(Field field) {
		if(field == null) {
			return false;
		}
		
		if(field.isFieldHit() == true) {
			System.out.println("Campo já foi destruido");
			return false;
		}
		
		field.setFieldHit();
		Ship ship = field.hasShip();
        if (ship != null) {
            ship.takeHit();
            System.out.println("Fogo! O navio recebeu " + ship.getHits() + " danos.");

            if (ship.isDestroyed()) {
                shipsDestroyed++;
                System.out.println("Navio destruido com sucesso");
            }
        } 
        else {
            System.out.println("Água!");
        }
        
        return true;
	}
	
	public Field getField(int x, int y){
		if (x >= 0 && x < size && y >= 0 && y < size) {
            return this.grid[y][x];
        }
        return null;
	}
	
	public boolean addShip(int x, int y, Ship ship, ORIENTATION orientation ) {
	    int shipSize = ship.getSize();

	    // --- 1. VALIDAR SE O NAVIO CABE NO TABULEIRO ---
	    if (orientation == ORIENTATION.HORIZONTAL) {
	        if (x + shipSize > size) {
	            System.out.println("Erro: Navio ultrapassa o limite horizontal!");
	            return false;
	        }
	    } 
	    else {
	        if (y + shipSize > size) {
	            System.out.println("Erro: Navio ultrapassa o limite vertical!");
	            return false;
	        }
	    }


	    for (int i = 0; i < shipSize; i++) {
	        int checkX = x;
	        int checkY = y;

	        if (orientation == ORIENTATION.HORIZONTAL) {
	            checkX = x + i;
	        } else {
	            checkY = y + i;
	        }

	        if (hasShipAt(checkX, checkY)) {
	            System.out.println("Erro: Já existe um navio na posição " + checkX + "," + checkY);
	            return false;
	        }
	    }

	    for (int i = 0; i < shipSize; i++) {
	        int targetX = x;
	        int targetY = y;

	        if (orientation == ORIENTATION.HORIZONTAL) {
	            targetX = x + i;
	        } else {
	            targetY = y + i;
	        }

	        Field field = getField(targetX, targetY);
	        if (field != null) {
	            field.setShip(ship);
	        }
	    }
	    
	    System.out.println("Navio de tamanho " + shipSize + " adicionado com sucesso!");
	    System.out.println("Pos X" + x + " posY" + y);
	    return true;
	}
	
	public boolean hasShipAt(int x, int y) {
		Field field = getField(x, y);
		
		if(field != null && field.hasShip() != null) {
			System.out.println("Tem navio");
			return true;
		}
		return false;
	}
	
	public int getSize() {
		return this.size;
	}

	public int getShipsDestroyed() {
		return shipsDestroyed;
	}

	public void setShipsDestroyed(int shipsDestroyed) {
		this.shipsDestroyed = shipsDestroyed;
	}
	
	
	
	
	
}

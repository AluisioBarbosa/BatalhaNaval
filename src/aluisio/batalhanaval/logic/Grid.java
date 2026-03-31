package aluisio.batalhanaval.logic;

public class Grid {
	private Field grid[][];
	private int size;
	
	public Grid() {
		this.size = 7;
		this.grid = new Field[size][size];
		
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
		return true;
	}
	
	public Field getField(int x, int y){
		if (x >= 0 && x < size && y >= 0 && y < size) {
            return this.grid[y][x];
        }
        return null;
	}
	
	
}

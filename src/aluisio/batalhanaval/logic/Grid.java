package aluisio.batalhanaval.logic;

public class Grid {
	private Field grid[][];
	
	public Grid() {
		this.grid = new Field[7][7];
	}
	
	public boolean updateGrid(Field field) {
		if(field.isFieldHit() == true) {
			System.out.println("Campo já foi destruido");
			return false;
		}
		
		field.setFieldHit();
		return true;
	}
	
	public Field getField(int x, int y){
		return this.grid[x][y];
	}
	
}

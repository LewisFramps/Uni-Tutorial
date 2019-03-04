import java.util.Arrays;
import java.util.Objects;

public class GameGrid{
	public static final int GRID_DIM = 9;
	public static final int SUBGRID_DIM = GRID_DIM / 3;
	public static final int MAX_VAL = 9;
	public static final int MIN_VAL = 1;
	public static final int EMPTY_VAL = 0;
	private Field[][] grid = new Field[GRID_DIM][GRID_DIM]; 
	
	public GameGrid(int[][] grid) {
        Objects.requireNonNull(grid);
        int[][] temp_grid = grid;
        initialiseGrid(temp_grid);
		
    }
	
	public GameGrid(String path){
		Objects.requireNonNull(path);
		int[][] temp_grid = IOUtils.loadFromFile(path);
		initialiseGrid(temp_grid);
	}
	
	public GameGrid(GameGrid passedInGrid) {
		for(int i = 0; i < GRID_DIM; i++) {
			for(int n = 0; n < GRID_DIM; n++) {
				this.grid[i][n] = new Field(passedInGrid.getField(n, i), passedInGrid.isInitial(n, i));
			}
		}
	}
	
	private void initialiseGrid(int[][] int_grid){
		for(int i = 0; i < int_grid.length; i++) {
			for(int n = 0; n < int_grid[0].length; n++) {
				if(int_grid[i][n] != 0) {
					this.grid[i][n] = new Field(int_grid[i][n], true);
				} else {
					this.grid[i][n] = new Field(0, false);
				}
			}
		}
	}
	
	public boolean isInitial(int x, int y) {
		//if (x < 0 || x >= GRID_DIM || y < 0 || y >= GRID_DIM) throw new IllegalArgumentException("Given Coordinate invalid: (" + x + "," + y + ")");
		return this.grid[y][x].getInitial();
	}
	
	public int getField(int x, int y){
		if (x < 0 || x >= GRID_DIM || y < 0 || y >= GRID_DIM) throw new IllegalArgumentException("Given dimensions invalid: " + x + "x" + y);
		return grid[y][x].getValue();
	}

	public boolean setField(int x, int y, int newVal){
		if (x < 0 || x >= GRID_DIM || y < 0 || y >= GRID_DIM) throw new IllegalArgumentException("Given dimensions invalid: " + x + "x" + y);
		if (newVal < MIN_VAL || newVal > MAX_VAL) throw new IllegalArgumentException("Given value invalid: " + newVal);
		if(isValid(x, y, newVal)){
			this.grid[y][x].setValue(newVal);
			return true;
		} 
		else{
			System.out.println("Invalid Posistion!");
			return false;
		}
	}

	private boolean checkRow(int y, int newVal) {
		if (y < 0 || y >= GRID_DIM) throw new IllegalArgumentException("Given row invalid: "+ y);
		for (Field checkVal : this.grid[y]) {
			if (checkVal.getValue() == newVal) return false;
		}
		return true;
	}

	public void clearField(int x, int y){
		if (x < 0 || x >= GRID_DIM || y < 0 || y >= GRID_DIM) throw new IllegalArgumentException("Given dimensions invalid: " + x + "x" + y);
		this.grid[y][x].setValue(EMPTY_VAL);
	}
	
	private boolean checkColumn(int x, int newVal) {
		if (x < 0 || x >= GRID_DIM) throw new IllegalArgumentException("Given column invalid: "+ x);
		for (int i = 0; i < 9; i++) {
			if (this.grid[i][x].getValue() == newVal)
				return false;
		}
		return true;
	}

	private boolean checkSubGrid(int x, int y, int newVal) {
		while (x % 3 != 0) {
			x--;
		}
		while (y % 3 != 0) {
			y--;
		}
		for (int i = y; i < y + 3; i++) {
			for (int n = x; n < x + 3; n++) {
				if (this.grid[i][n].getValue() == newVal)
					return false;
			}
		}
		return true;
	}

	public boolean isValid(int x, int y, int newVal) {
		return (checkRow(y, newVal)	&&	 checkColumn(x, newVal) && checkSubGrid(x, y, newVal) && !isInitial(x, y));
	}

	public void printGrid() {
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				System.out.print(this.grid[y][x].getValue() + " ");
				if (x == 2 || x == 5)
					System.out.print(" ");
			}
			System.out.println();
			if (y == 2 || y == 5)
				System.out.println();
		}
		System.out.println();
	}
}
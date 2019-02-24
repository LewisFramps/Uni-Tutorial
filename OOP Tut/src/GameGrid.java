import java.util.Arrays;
import java.util.Objects;

public class GameGrid{
	private int[][] grid;
	public static final int GRID_DIM = 9;
	public static final int SUBGRID_DIM = GRID_DIM / 3;
	public static final int MAX_VAL = 9;
	public static final int MIN_VAL = 1;
	public static final int EMPTY_VAL = 0;
	
	public GameGrid(int[][] grid) {
        Objects.requireNonNull(grid);
        this.grid = grid;
    }
	
	public GameGrid(String path){
		Objects.requireNonNull(path);
		this.grid = IOUtils.loadFromFile(path);
	}
	
	public int getField(int x, int y){
		if (x < 0 || x >= GRID_DIM || y < 0 || y >= GRID_DIM) throw new IllegalArgumentException("Given dimensions invalid: " + x + "x" + y);
		return grid[y][x];
	}

	public boolean setField(int x, int y, int newVal){
		if (x < 0 || x >= GRID_DIM || y < 0 || y >= GRID_DIM) throw new IllegalArgumentException("Given dimensions invalid: " + x + "x" + y);
		if (newVal < MIN_VAL || newVal > MAX_VAL) throw new IllegalArgumentException("Given value invalid: " + newVal);
		if(isValid(x, y, newVal)){
			this.grid[y][x] = newVal;
			return true;
		} 
		else{
			System.out.println("Invalid Posistion!");
			return false;
		}
	}

	private boolean checkRow(int y, int newVal) {
		if (y < 0 || y >= GRID_DIM) throw new IllegalArgumentException("Given row invalid: "+ y);
		for (int checkVal : this.grid[y]) {
			if (checkVal == newVal)
				return false;
		}
		return true;
	}

	public void clearField(int x, int y){
		if (x < 0 || x >= GRID_DIM || y < 0 || y >= GRID_DIM) throw new IllegalArgumentException("Given dimensions invalid: " + x + "x" + y);
		this.grid[y][x] = EMPTY_VAL;
	}
	
	private boolean checkColumn(int x, int newVal) {
		if (x < 0 || x >= GRID_DIM) throw new IllegalArgumentException("Given column invalid: "+ x);
		for (int i = 0; i < 9; i++) {
			if (this.grid[i][x] == newVal)
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
				if (this.grid[i][n] == newVal)
					return false;
			}
		}
		return true;
	}

	public boolean isValid(int x, int y, int newVal) {
		return ( checkRow(y, newVal)	&&	 checkColumn(x, newVal) && checkSubGrid(x, y, newVal));
	}

	public void printGrid() {
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				System.out.print(this.grid[y][x] + " ");
				if (x == 2 || x == 5)
					System.out.print(" ");
			}
			System.out.println();
			if (y == 2 || y == 5)
				System.out.println();
		}
	}
}
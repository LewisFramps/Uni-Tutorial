import java.util.ArrayList;

public class solver {

	public solver() {

	}

	public static void solve(GameGrid game) {
		/*
		 * right so instead of looking at the solution I kept trying to make my own solver, finally got a solution!
		 */
		ArrayList<Cell> cells = new ArrayList<>(); 
		for (int y = 0; y < GameGrid.GRID_DIM; y++) {
			for (int x = 0; x < GameGrid.GRID_DIM; x++) {
				if (!game.isInitial(x, y))
					cells.add(new Cell(x, y));
			}
		}
		int i = 0;
		while (game.getField(GameGrid.GRID_DIM - 1, GameGrid.GRID_DIM - 1) == GameGrid.EMPTY_VAL) {
			boolean valid = false;
			for (int num = cells.get(i).getVal(); num < GameGrid.EMPTY_VAL + 1; num++) {
				if (game.isValid(cells.get(i).getX(), cells.get(i).getY(), num)) {
					game.setField(cells.get(i).getX(), cells.get(i).getY(), num);
					cells.get(i).setVal(num);
					i++;
					valid = true;
					break;
				}
			}
			if (!valid) {
				cells.get(i).setVal(1);
				game.clearField(cells.get(i).getX(), cells.get(i).getY());
				i--;
			}
		}
		game.printGrid();
	}
}

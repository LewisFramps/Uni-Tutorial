import java.util.Stack;


public class solver {

	public solver(){
		
	}
	
	
	public static void solve(GameGrid game) {
		Stack<Cell> posStack = new Stack<>();
		for(int y = 0; y < GameGrid.GRID_DIM; y++) {
			for(int x = 0; x < GameGrid.GRID_DIM; x++) {
				if(!game.isInitial(x, y)) {
					Cell temp = new Cell(x, y);
					temp.setVal(game.getField(x, y));
					temp.iterateVal();
					while(temp.getVal() < 9 && !game.isValid(x, y, temp.getVal())) {
						temp.iterateVal();
					}
					System.out.printf("(%d, %d) : %d : ", x, y, temp.getVal());
					System.out.println(game.isValid(x, y, temp.getVal()));
					game.printGrid();
					if(game.isValid(x, y, temp.getVal()) && temp.getVal() < 10) {
						System.out.println("YES");
						posStack.push(temp);
						game.setField(x, y, posStack.peek().getVal());
					} else {
						System.out.println("NO");
						posStack.pop();
						x = posStack.peek().getX();
						y = posStack.peek().getY();
					}
				}
			}
		}
	}
}

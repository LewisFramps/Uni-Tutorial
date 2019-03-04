
public class Cell {
	int x;
	int y;
	int val;
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		this.val = 1;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getVal() {
		return this.val;
	}
	public void setVal(int newVal) {
		this.val = newVal;
	}
	public void iterateVal() {
		this.val++;
	}
}

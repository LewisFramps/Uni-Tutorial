
public class Field {
	int value;
	final boolean initial;
	
	public Field() {
		value = 0;
		initial = false;
	}
	
	public Field(int value, boolean initial) {
		this.value = value;
		this.initial = initial;
	}
	
	public String toString() {
		return(value + ", " + initial);
	}
	
	public void setValue(int newVal) {
		this.value = newVal;
	}
	
	public int getValue() {
		return this.value;
	}
}

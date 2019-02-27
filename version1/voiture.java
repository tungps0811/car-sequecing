package version1;

import java.util.Arrays;
import java.util.Vector;

public class voiture {
	public int rang;
	public int color;
	public Vector<Boolean> options;
	
	
	public voiture(int rang, int color, Vector<Boolean> options) {
		super();
		this.rang = rang;
		this.color = color;
		this.options = options;
	}


	@Override
	public String toString() {
		return "voiture [rang=" + rang + ", color=" + color + ", options=" + options + "]";
	}



	
	
	
	
}

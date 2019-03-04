package version2;

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


	public int getRang() {
		return rang;
	}


	public void setRang(int rang) {
		this.rang = rang;
	}


	public int getColor() {
		return color;
	}


	public void setColor(int color) {
		this.color = color;
	}


	public Vector<Boolean> getOptions() {
		return options;
	}


	public void setOptions(Vector<Boolean> options) {
		this.options = options;
	}


	@Override
	public String toString() {
		return "voiture [rang=" + rang + ", color=" + color + ", options=" + options + "]";
	}



	
	
	
	
}

package version2;

public class ratio {
	public int r1;
	public int r2;
	
	public boolean priorite;
	public String identite;
	
	
	@Override
	public String toString() {
		return "ratio [r1=" + r1 + ", r2=" + r2 + ", priorite=" + priorite + ", identite=" + identite + "]";
	}


	public ratio(int r1, int r2, boolean priorite, String identite) {
		super();
		this.r1 = r1;
		this.r2 = r2;
		this.priorite = priorite;
		this.identite = identite;
	}
	
	
	
	
}

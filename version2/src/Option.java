public class Option {
	public int r1;
	public int r2;
	
	public boolean priorite;
	public String nomOption;
	public int optionIndex;

	public Option(int r1, int r2, boolean priorite, String nomOption, int optionIndex) {
		this.r1 = r1;
		this.r2 = r2;
		this.priorite = priorite;
		this.nomOption= nomOption;
		this.optionIndex = optionIndex;
	}
	
	@Override
	public String toString() {
		return "ratio [r1=" + r1 + ", r2=" + r2 + ", priorite=" + priorite + ", identite=" + nomOption + "]";
	}
	
}

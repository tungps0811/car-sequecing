
public class InfoFenetre {
	private Fenetre fenetre;
	private int info; // info est le nombre des voitures qui possedent l'option.
	
	public InfoFenetre(Fenetre fenetre, int info) {
		this.fenetre = fenetre;
		this.info = info;
	}
	
	public Fenetre getFenetre() {
		return fenetre;
	}
	
	public int getInfo() {
		return info;
	}

}

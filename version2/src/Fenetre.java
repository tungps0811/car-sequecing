import java.util.Objects;
import java.util.Vector;

public class Fenetre {
	private int debut;
	private int taille;
	//private Option option;
	
	
	public Fenetre(int debut, int taille) {
		this.debut = debut;
		this.taille= taille;
		//this.option = option;
	}
	
	public int getDebut() {
		return debut;
	}
	
	public int getTaille() {
		return taille;
	}
	
//	public Option getOption() {
//		return option;
//	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != getClass())
			return false;
		Fenetre fenetre = (Fenetre) obj;
		//return (super.equals(obj) && Objects.equals(debut, fenetre.debut) && Objects.equals(taille, fenetre.getTaille()) && Objects.equals(option, fenetre.option));
		return (super.equals(obj) && Objects.equals(debut, fenetre.debut) && Objects.equals(taille, fenetre.getTaille()));
	}
	
	
}

import java.util.Objects;
import java.util.Vector;

public class Fenetre {
	private int debut;
	private int taille;
	private Option option;
	
	
	public Fenetre(int debut, int taille, Option option ) {
		this.debut = debut;
		this.taille= taille;
		this.option = option;
	}
	
	public int getDebut() {
		return debut;
	}
	
	public int getTaille() {
		return taille;
	}
	
	public int nbrOption(Vector<Voiture> listVoitures, Vector<Option> listOptions) {
		//nbr Voitures qui ont l'option dans la fenetre;
		int nbr = 0;
		for (int index = debut; index <debut + taille; index ++) {
			if (listVoitures.get(index).getOptionMap().get(listOptions.indexOf((Object)option)));
				nbr++;
		}
		return nbr;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != getClass())
			return false;
		Fenetre fenetre = (Fenetre) obj;
		return (super.equals(obj) && Objects.equals(debut, fenetre.debut) && Objects.equals(taille, fenetre.getTaille()) && Objects.equals(option, fenetre.option));		
	}
	
	
}

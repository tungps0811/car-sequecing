import java.util.Vector;

public class FenetreOption {
	private Vector<Fenetre> fenetres;
	private Option option;
	
	public FenetreOption(Option option, Vector<Voiture> listeVoitures, int nbrVoitureJourAvant) {
		fenetres = new Vector<>();
		// initiation de liste de fenetre option
		for (int indexFenetre = nbrVoitureJourAvant - (option.r2 - 1); indexFenetre < listeVoitures.size() - 1; indexFenetre++) {
			Fenetre newFenetre = new Fenetre(indexFenetre, option, listeVoitures);
			fenetres.add(newFenetre);
		}				
	}

	public Vector<Fenetre> getFenetres() {
		return fenetres;
	}

	public Option getOption() {
		return option;
	}
	
}

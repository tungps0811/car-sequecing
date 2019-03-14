import java.util.Vector;

public class Ordonnancement {

	// inputs
	private Vector<Option> listOptions;
	private Vector<Voiture> listVoitures;
//	private Vector<Integer> listClassVoitures;
	private int colorMax;
	private Vector<String> objectives;
	private Vector<FenetreOption> listFenetreOption; // une liste qui se trouve les nbrOption dans chaque fenetre pour chaque option

	public Ordonnancement(Vector<Option> listOptions, Vector<Voiture> listVoitures, int colorMax, Vector<String> objectives) {
		this.listOptions = listOptions;
		this.listVoitures = listVoitures;
		this.colorMax = colorMax;
		this.objectives = objectives;
		this.listFenetreOption = initListFenetreOption(listOptions, listVoitures);		
	}
	
	protected Vector<FenetreOption> initListFenetreOption(Vector<Option> listOptions, Vector<Voiture> listVoitures) {
		int nbrVoitureJourAvant = nbrVoitureJourAvant();
		Vector<FenetreOption> listFenetreOption = new Vector<>();
		for (Option option: listOptions) {
			FenetreOption fenetreOption = new FenetreOption(option, listVoitures, nbrVoitureJourAvant);
			listFenetreOption.add(fenetreOption);
		}
		return listFenetreOption; 
	}

	public Vector<Option> getListOptions() {
		return listOptions;
	}

	public Vector<Voiture> getListVoitures() {
		return listVoitures;
	}

	public int getColorMax() {
		return colorMax;
	}

	public Vector<String> getObjectives() {
		return objectives;
	}	
	
	public Vector<FenetreOption> getListFenetreOption() {
		return listFenetreOption;
	}
	
	public Vector<Integer> getListClassVoitures () {
		return null;
	}
	
	public int nbrVoitureJourAvant () {
		int compteur = 0;		
		while (listVoitures.get(compteur).getDate().equals(listVoitures.get(0).getDate()) )
			compteur++;		
		return compteur;
	}		
	
	// pour l'ecriture
	public int nbrContraintsPrio() {
		int res = 0;
		for (Option option : listOptions) {
			if (option.priorite) res++;
		}
		return res;
	}

	// pour l'ecriture
	public int nbrContraintsNonPrio() {
		int res = 0;
		for (Option option : listOptions) {
			if (!option.priorite) res++;
		}
		return res;
	}

	// nombre de violation pour une option
	public int nbrViolationOption (Option option) {
		int res = 0;
		FenetreOption fenetreOption = listFenetreOption.get(option.optionIndex);

		for (Fenetre fenetre : fenetreOption.getFenetres()) {
			if (fenetre.getNombreOption() > option.r1) res++;
		}
		
		return res;
	}
		
	public int penalitePriorite() {
		int res = 0;
		for (int i = 0; i < listOptions.size(); i++) {
			Option option = listOptions.get(i);
			if (option.priorite) res += nbrViolationOption(option);
		}
		return res;
	}
	
	public int penaliteNonPriorite() {
		int res = 0;
		for (int i = 0; i < listOptions.size(); i++) {
			Option option = listOptions.get(i);
			if (!option.priorite) res += nbrViolationOption(option);
		}
		return res;
	}
	
	// calculer les violation de changement de la couleur
	public int nbrChangementCouleur() {
		int nbr_purge = 0;
		// definition couleur premiere voiture
		int current_couleur = listVoitures.get(nbrVoitureJourAvant() - 1).getPainColor();
		int nbr_voiture_caracterise = 1;
		
		for (int i = nbrVoitureJourAvant(); i < listVoitures.size(); i++) {

			if (current_couleur == listVoitures.get(i).getPainColor()) {
				nbr_voiture_caracterise++;
//				System.out.println(nbr_voiture_caracterise);
				if (nbr_voiture_caracterise > getColorMax()) {
					nbr_voiture_caracterise = 1;
					nbr_purge++;
//					System.out.print("(*max*)");
				}
			} else {
				nbr_purge++;
				nbr_voiture_caracterise = 1;
//				System.out.print("*");
				current_couleur = listVoitures.get(i).getPainColor(); // update current paint color
			}
//			System.out.print("("+listVoitures.get(i).getPainColor()+")");
		}
//		System.out.println();
		return nbr_purge;
	}
	
	public int totalPenalite() {
		return 10000*penalitePriorite() + 100*penaliteNonPriorite() + nbrChangementCouleur();
	}
	
	// prepare liste des voitures avec couleur et des options
	public String prepareToWrite() {
		String res = "";
			for (Voiture voiture : listVoitures) {
				res += voiture.getDate() + ";" + voiture.getSeqRank() + ";" + voiture.getIdent() + ";" + voiture.getPainColor();
				for (int i = 0; i < voiture.getOptionMap().size(); i++) {
					if (voiture.getOptionMap().get(i)) res += ";" + 1;
					else res += ";" + 0;
					if (i == voiture.getOptionMap().size()-1) res += "\r\n";
				}
				
			}
		return res;
	}



}

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import com.sun.javafx.geom.Vec2d;

public class Optimisation {
//	private CarSequencing premierCarSeq;
//	private long cout;
	private Vector<Ordonnancement> listeOrdre;

	public Optimisation() {
		listeOrdre = new Vector<>();
	}

	public Vector<Ordonnancement> getListeOrdre() {
		return listeOrdre;
	}

	// donner 2 positions aleatoires
	// calcul le delta en parcourant toutes les fenetres que les 2 position touche
	//
	public int envigagerPriorite(int pos1, int pos2, Ordonnancement ordre) {
		int res = 0;
		// parcourur liste des options
		
		for (Option option: ordre.getListOptions()) {
			if (option.priorite) {
				
				//	delta option des deux voiture
				Voiture voiture1 = ordre.getListVoitures().get(pos1);
				Voiture voiture2 = ordre.getListVoitures().get(pos2);				
				// delta option priorite			
				int optionPos1 = (voiture1.getOptionMap().get(option.optionIndex)) ? 1 : 0;
				int optionPos2 = (voiture2.getOptionMap().get(option.optionIndex)) ? 1 : 0;
				
				// recuperer liste des fenetres
				Vector<Fenetre> listFenetre = ordre.getListFenetreOption().get(option.optionIndex).getFenetres();
				int nbrVoitureJourAvant = ordre.nbrVoitureJourAvant();
				// pour recuperer le bon index de la fenetre dans la liste des fenetre
				int decalage = (nbrVoitureJourAvant - option.r2 + 1); 
												
				// position 1
				// preparer les index des fenetres
				int debut = pos1 - option.r2 + 1;
				int fin = pos1;

				// parcourir toutes les fenetres correspondant position 1
				for (int indexFenetre = debut - decalage; indexFenetre <= fin - decalage; indexFenetre++) {
					// calculer le totalPenalite pour r2 fenetres
					Fenetre fenetre = listFenetre.get(indexFenetre);
					if (fenetre.getNombreOption() <= option.r1 && fenetre.getNombreOption() - optionPos1 + optionPos2 > option.r1)
						res++;
					else if (fenetre.getNombreOption() > option.r1 && fenetre.getNombreOption() - optionPos1 + optionPos2 <= option.r1)
						res--;
				}
				
				
				// position 2
				// preparer les index des fenetres
				int debut2 = pos2 - option.r2 + 1;
				int fin2 = pos2;																

				// parcourir toutes les fenetres correspondant position 1
				for (int indexFenetre = debut2 - decalage; indexFenetre <= fin2 - decalage; indexFenetre++) {
					// calculer le totalPenalite pour r2 fenetres
					Fenetre fenetre = listFenetre.get(indexFenetre);
					if (fenetre.getNombreOption() <= option.r1 && fenetre.getNombreOption() + optionPos1 - optionPos2 > option.r1)
						res++;
					else if (fenetre.getNombreOption() > option.r1 && fenetre.getNombreOption() + optionPos1 - optionPos2 <= option.r1)
						res--;
				}
				
			}
		}
		
		return res;
	}
	
	public int envigagerNonPriorite(int pos1, int pos2, Ordonnancement ordre) {
		int res = 0;
		// parcourur liste des options
		
		for (Option option: ordre.getListOptions()) {
			if (!option.priorite) {
				
				//	delta option des deux voiture
				Voiture voiture1 = ordre.getListVoitures().get(pos1);
				Voiture voiture2 = ordre.getListVoitures().get(pos2);				
				
				// has option??? 			
				int optionPos1 = (voiture1.getOptionMap().get(option.optionIndex)) ? 1 : 0;				
				int optionPos2 = (voiture2.getOptionMap().get(option.optionIndex)) ? 1 : 0;
				
				// recuperer liste des fenetres
				Vector<Fenetre> listFenetre = ordre.getListFenetreOption().get(option.optionIndex).getFenetres();
				int nbrVoitureJourAvant = ordre.nbrVoitureJourAvant();
				// pour recuperer le bon index de la fenetre dans la liste des fenetre
				int decalage = (nbrVoitureJourAvant - option.r2 + 1); 
												
				// position 1
				// preparer les index des fenetres
				int debut = pos1 - option.r2 + 1;
				int fin = pos1;

				// parcourir toutes les fenetres correspondant position 1
				for (int indexFenetre = debut - decalage; indexFenetre <= fin - decalage; indexFenetre++) {
					// calculer le totalPenalite pour r2 fenetres
					Fenetre fenetre = listFenetre.get(indexFenetre);
					if (fenetre.getNombreOption() <= option.r1 && fenetre.getNombreOption() - optionPos1 + optionPos2 > option.r1)
						res++;
					else if (fenetre.getNombreOption() > option.r1 && fenetre.getNombreOption() - optionPos1 + optionPos2 <= option.r1)
						res--;
				}
				
				
				// position 2
				// preparer les index des fenetres
				int debut2 = pos2 - option.r2 + 1;
				int fin2 = pos2;																

				// parcourir toutes les fenetres correspondant position 1
				for (int indexFenetre = debut2 - decalage; indexFenetre <= fin2 - decalage; indexFenetre++) {
					// calculer le totalPenalite pour r2 fenetres
					Fenetre fenetre = listFenetre.get(indexFenetre);
					if (fenetre.getNombreOption() <= option.r1 && fenetre.getNombreOption() + optionPos1 - optionPos2 > option.r1)
						res++;
					else if (fenetre.getNombreOption() > option.r1 && fenetre.getNombreOption() + optionPos1 - optionPos2 <= option.r1)
						res--;
				}
				
			}
		}
		
		return res;
	}
	
	public int totalPenalitePrioriteAutourPosition(int pos, Ordonnancement ordre) {
		int res = 0;
		// parcourir chaque option
		for (Option option : ordre.getListOptions()) {
			if (option.priorite) {
				// recuperer liste des fenetres
				Vector<Fenetre> listFenetre = ordre.getListFenetreOption().get(option.optionIndex).getFenetres();

				// preparer les index des fenetres
				int debut = pos - option.r2 + 1;
				int fin = pos;
								
				int nbrVoitureJourAvant = ordre.nbrVoitureJourAvant();
				int decalage = (nbrVoitureJourAvant - option.r2 + 1); // pour recuperer le bon index de la fenetre dans la liste des fenetre

				// parcourir toutes les fenetres correspondant les positions et
				for (int indexFenetre = debut - decalage; indexFenetre <= fin - decalage; indexFenetre++) {
					// calculer le totalPenalite pour r2 fenetres
					Fenetre fenetre = listFenetre.get(indexFenetre);
					if (fenetre.getNombreOption() > option.r1)
						res++;
				}
			}
		}
		return res;
	}

	public int totalPenaliteNonPrioriteAutourPosition(int pos, Ordonnancement ordre) {
		int res = 0;
		// parcourir chaque option
		for (Option option : ordre.getListOptions()) {
			if (!option.priorite) {
				// recuperer liste des fenetres
				Vector<Fenetre> listFenetre = ordre.getListFenetreOption().get(option.optionIndex).getFenetres();

				// preparer les index des fenetres
				int debut = pos - option.r2 + 1;
				int fin = pos;
								
				int nbrVoitureJourAvant = ordre.nbrVoitureJourAvant();
				int decalage = (nbrVoitureJourAvant - option.r2 + 1); // pour recuperer le bon index de la fenetre dans la liste des fenetre

				// parcourir toutes les fenetres correspondant les positions et
				for (int indexFenetre = debut - decalage; indexFenetre <= fin - decalage; indexFenetre++) {
					// calculer le totalPenalite pour r2 fenetres
					Fenetre fenetre = listFenetre.get(indexFenetre);
					if (fenetre.getNombreOption() > option.r1)
						res++;
				}
			}
		}
		return res;
	}

	public int penaliteCouleurAutourPosition(int pos, Ordonnancement ordre) {
//		TODO
		return 0;
	}

	public int delta(int pos1, int pos2, Ordonnancement oldOrdre, Ordonnancement newOrdre) {
		int penaliteOldOrdre = 10000
				* (totalPenalitePrioriteAutourPosition(pos1, oldOrdre)
						+ totalPenalitePrioriteAutourPosition(pos2, oldOrdre))
				+ 100 * (totalPenaliteNonPrioriteAutourPosition(pos1, oldOrdre)
						+ totalPenaliteNonPrioriteAutourPosition(pos2, oldOrdre));
		int penaliteNewOrdre = 10000
				* (totalPenalitePrioriteAutourPosition(pos1, newOrdre)
						+ totalPenalitePrioriteAutourPosition(pos2, newOrdre))
				+ 100 * (totalPenaliteNonPrioriteAutourPosition(pos1, newOrdre)
						+ totalPenaliteNonPrioriteAutourPosition(pos2, newOrdre));

		return penaliteNewOrdre - penaliteOldOrdre;
	}

	public void changement(int position1, int position2, Ordonnancement ordre) throws IOException {

//		Vec2d vector = choixAleatoire(ordre);
//		int position1 = 59;
//		int position2 = 200;

		// supposons toujours que position1 < position2;
//		while (dansMemeClasse(position1, position2, ordre)) {
//			vector = choixAleatoire(ordre);
//			position1 = (int)vector.x;		
//			position2 = (int)vector.y;
//		}

//		System.out.println("position 1 " + position1 + ", position 2 " + position2);

		Voiture tmp = ordre.getListVoitures().get(position1);
		Voiture voitureAtPos2 = ordre.getListVoitures().get(position2);

		ordre.getListVoitures().set(position1, voitureAtPos2);
		ordre.getListVoitures().set(position2, tmp);
 
		EcritureFichier writer = new EcritureFichier(ordre);
		writer.write("vehicles-ver2.txt");
	}
	
	public void addCarSeq(Ordonnancement ordre) {
//		CarSequencing newCarSeq = new CarSequencing(carSeq.getListOptions(), carSeq.getListVoitures(), carSeq.getColorMax(), carSeq.getObjectives());
		listeOrdre.add(ordre);
	}

	public Vec2d choixAleatoire(Ordonnancement ordre) {
//		CarSequencing carSeq = listeCarSeq.get(listeCarSeq.size()-1);
		int min = ordre.nbrVoitureJourAvant();
		int max = ordre.getListVoitures().size() - 1;
		Random random = new Random();
		int position1 = min + random.nextInt(max - min);
		int position2 = min + random.nextInt(max - min);

		// swap position to make sure position always smaller than position 2
		if (position1 > position2) {
			int tmp = position1;
			position1 = position2;
			position2 = tmp;
		}
//		System.out.println("position 1 " + position1);
//		System.out.println("position 2 " + position2);		
		return new Vec2d(position1, position2);
	}

	public boolean dansMemeClasse(int pos1, int pos2, Ordonnancement ordre) {
		return pos1 == pos2
				|| ordre.getListVoitures().get(pos1).getNumClass() == ordre.getListVoitures().get(pos2).getNumClass();
	}

}

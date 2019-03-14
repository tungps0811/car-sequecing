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
				
				//
				int nbrVoitureJourAvant = ordre.nbrVoitureJourAvant();
				int decalage = (nbrVoitureJourAvant - option.r2 + 1);
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
			if (option.priorite) {
				// preparer les index des fenetres

				int debut = pos - option.r2 + 1;
				int fin = pos;

				// parcourir toutes les fenetres correspondant les positions et
				for (int indexFenetre = debut; indexFenetre <= fin; indexFenetre++) {
					// calculer le totalPenalite pour r2 fenetres
					Fenetre fenetre = new Fenetre(indexFenetre, option, ordre.getListVoitures());
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

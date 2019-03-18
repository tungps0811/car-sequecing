
//import java.util.Random;

import java.util.Random;
import java.util.Timer;
import java.util.Vector;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import com.sun.javafx.geom.Vec2d;

public class MouvementCarSeq {
	private CarSequencing inputCarSeq;
	private Vector<CarSequencing> listCarSequencings = new Vector<CarSequencing>();

	public MouvementCarSeq(CarSequencing inputCarSeq) {
		this.inputCarSeq = inputCarSeq;
		listCarSequencings.add(inputCarSeq);

	}

	public Vec2d choixAleatoire() {
		int min = inputCarSeq.nbrVoitureDateMoins();
		int max = inputCarSeq.getListVoitures().size() - 1;
		Random random = new Random();
		int position1 = min + random.nextInt(max - min);
		int position2 = min + random.nextInt(max - min);
		return new Vec2d(position1, position2);
	}


	
	public CarSequencing changement(int position1, int position2, CarSequencing carSequencingActuelle) {
		CarSequencing res = new CarSequencing(copyListOption(carSequencingActuelle), copyListVoiture(carSequencingActuelle), carSequencingActuelle.getColorMax(),
				carSequencingActuelle.getObjectives());
		Voiture tmp = res.getListVoitures().get(position1);
		res.getListVoitures().set(position1, res.getListVoitures().get(position2));
		res.getListVoitures().set(position2, tmp);
		res.listToutInfoFenetres = res.setListToutInfoFenetres();
		return res;
	}

	public CarSequencing changementAvecEnvisagerPrio(CarSequencing carSequencingActuelle) {
		CarSequencing res = carSequencingActuelle;
		int penalitePrio1 = carSequencingActuelle.totalPenalitePriori();
		int penalitePrio2 = carSequencingActuelle.totalPenalitePriori();
		int penalitePrio3 = carSequencingActuelle.totalPenalitePriori();
		int position1 = inputCarSeq.nbrVoitureDateMoins();
		int position2 = position1;
		long start = System.currentTimeMillis();
		//System.out.println("le temps debut est " + System.currentTimeMillis());
		//long end = start + 60*10;
		
		do {
			int position1b = position1;
			int position2b = position2;
			do {
				Vec2d vec1 = choixAleatoire();
				position1 = (int) vec1.x;
				position2 = (int) vec1.y;
				if (System.currentTimeMillis() > start + 60*1500)
					return changement(position1b, position2b, carSequencingActuelle);
				penalitePrio1 = penalitePrio2 + ecartePrioChangement(position1, position2, res);
			} while ((!valide_Prio_Pour_ChangerPrio(position1, position2, res) ||  penalitePrio1 >= penalitePrio3));
			System.out.println("penalitePrio1 < penalitePrio3 ?" + "P1 est " + penalitePrio1 + ": p3 est "  + penalitePrio3);;
			penalitePrio3 = penalitePrio1;
			System.out.println("coupe est valide ( " + position1 + ", " + position2 + ")");
			
			
		} while (penalitePrio1 > 0 && System.currentTimeMillis() < start + 60*1500);
		System.out.println("le temps est " + (System.currentTimeMillis()-start));
		res = changement(position1, position2, carSequencingActuelle);
		System.out.println("nbr penalite prio apres  i changement est " + res.totalPenalitePriori());
		
		return res;
	}

	public CarSequencing changementAvecEnvisagerNonPrio(CarSequencing carSequencingActuelle) {
		CarSequencing res = carSequencingActuelle;
		int penalitePrio = carSequencingActuelle.totalPenalitePriori();
		System.out.println("nombre des penalite prio est " + penalitePrio);
		int penalitePrio1;
		int penaliteNonPrio1 = carSequencingActuelle.totalPenaliteNonPriori();
		int penaliteNonPrio2 = carSequencingActuelle.totalPenaliteNonPriori();
		int penaliteNonPrio3 = carSequencingActuelle.totalPenaliteNonPriori();
		int position1 = inputCarSeq.nbrVoitureDateMoins();
		int position2 = position1;
		long start = System.currentTimeMillis();
		//System.out.println("le temps debut est " + System.currentTimeMillis());
		//long end = start + 60*10;
		
		do {
			int position1b = position1;
			int position2b = position2;
			do {
				Vec2d vec1 = choixAleatoire();
				position1 = (int) vec1.x;
				position2 = (int) vec1.y;
				if (System.currentTimeMillis() > start + 60*2000)
					return changement(position1b, position2b, carSequencingActuelle);
				penaliteNonPrio1 = penaliteNonPrio2 + ecarteNonPrioChangement(position1, position2, res);
				penalitePrio1 = penalitePrio + ecartePrioChangement(position1, position2, res);
			} while ((!valide_Prio_Pour_ChangerNonPrio(position1, position2, res) ||  penaliteNonPrio1 >= penaliteNonPrio3) || penalitePrio1 > penalitePrio);
			System.out.println("penalitePrio1 < penalitePrio3 ?" + "P1 est " + penaliteNonPrio1 + ": p3 est "  + penaliteNonPrio3);;
			penaliteNonPrio3 = penaliteNonPrio1;
			System.out.println("coupe est valide ( " + position1 + ", " + position2 + ")");
			
			
		} while (penaliteNonPrio1 > 0 && System.currentTimeMillis() < start + 60*2000);
		System.out.println("le temps est " + (System.currentTimeMillis()-start));
		res = changement(position1, position2, carSequencingActuelle);
		System.out.println("nbr penalite non prio apres  i changement est " + res.totalPenaliteNonPriori());
		
		return res;
	}
	
	public Vector<CarSequencing> changementSuiteAvecEnvisagerPrio(int nbrChangement) {
		int taille = listCarSequencings.size();
		for (int index = 0; index < nbrChangement; index ++) {
			listCarSequencings.add(changementAvecEnvisagerPrio(listCarSequencings.get(index + taille -1)));
			System.out.println("nbr penalite prio apres " + (index +1) + "changement est " + listCarSequencings.get(listCarSequencings.size()-1).totalPenalitePriori());
			if (listCarSequencings.get(index +1).totalPenalitePriori() == 0)
				return listCarSequencings;

			}
		return listCarSequencings;
	} 
	
	public Vector<CarSequencing> changementSuiteAvecEnvisagerNonPrio(int nbrChangement) {
		int taille = listCarSequencings.size();
		for (int index = 0; index < nbrChangement; index ++) {
			listCarSequencings.add(changementAvecEnvisagerNonPrio(listCarSequencings.get(index + taille-1)));
			System.out.println("nbr penalite non prio apres " + (index +1) + "changement est " + listCarSequencings.get(listCarSequencings.size()-1).totalPenaliteNonPriori());
			if (listCarSequencings.get(index +1).totalPenaliteNonPriori() == 0)
				return listCarSequencings;

			}
		return listCarSequencings;
	} 
	
	public Vector<CarSequencing> changementSuiteAvecEnvisagerPrioEtNonPrio(int nbrChangementPrio, int nbrChangementNonPrio){
		changementSuiteAvecEnvisagerPrio(nbrChangementPrio);
		changementSuiteAvecEnvisagerNonPrio(nbrChangementNonPrio);
		return listCarSequencings;
	}
	public Vector<Option> copyListOption(CarSequencing carSequencing) {
		Vector<Option> copyListOption = new Vector<Option>();
		for (Option option : carSequencing.getListOptions()) {
			copyListOption.add(option);
		}
		return copyListOption;
	}


	
	public Vector<Voiture> copyListVoiture(CarSequencing carSequencing) {
		Vector<Voiture> copyListVoiture = new Vector<Voiture>();
		for (Voiture voiture : carSequencing.getListVoitures()) {
			copyListVoiture.add(voiture);
		}
		return copyListVoiture;

	}
	

	public int delta_Option_Position(Option option, int position1, int position2, CarSequencing carSequencing) {
		int indexOption = carSequencing.getListOptions().indexOf(option);
		Voiture voiture1 = carSequencing.getListVoitures().get(position1);
		Voiture voiture2 = carSequencing.getListVoitures().get(position2);
		Vector<InfoFenetre> listFenetreInfo = carSequencing.listToutInfoFenetres.get(indexOption)
				.getList_Fenetres_Info();
		int deltaCout = 0;
		for (InfoFenetre infoFenetre : listFenetreInfo) {
			Fenetre fenetre = infoFenetre.getFenetre();

			if (check_Fenetre_Position(position1, fenetre) && !check_Fentre_Commun(position1, position2, fenetre)) {
				int infoAvant = infoFenetre.getInfo();
				int infoChangement = infoFenetre.getInfo();
				if (voiture1.getOptionMap().get(indexOption) && !voiture2.getOptionMap().get(indexOption)) {
					infoChangement = infoChangement - 1;
				}
				if (!voiture1.getOptionMap().get(indexOption) && voiture2.getOptionMap().get(indexOption)) {
					infoChangement = infoChangement + 1;
				}
				deltaCout = deltaCout + Math.max(0, infoChangement - option.r1) - Math.max(0, infoAvant - option.r1);
			}

		}

		return deltaCout;

	}

	

	public Boolean check_Fenetre_Position(int position, Fenetre fenetre) {
		return (fenetre.getDebut() <= position && position < (fenetre.getDebut() + fenetre.getTaille()));
	}

	public Vec2d indexFenetreTouche(int position, Option option) {
		int x = Math.max(inputCarSeq.nbrVoitureDateMoins() - option.r2 + 1, position - option.r2 + 1);
		int y = Math.min(position, inputCarSeq.getListVoitures().size() - option.r1 - 1);
		return new Vec2d(x, y);
	}

	public Boolean check_Fentre_Commun(int position1, int position2, Fenetre fenetre) {
		return (check_Fenetre_Position(position1, fenetre) && check_Fenetre_Position(position2, fenetre));
	}

	public Boolean valide_Prio_Pour_ChangerPrio(int position1, int position2, CarSequencing carSequencing) {
		return (ecartePrioChangement(position1, position2, carSequencing) < 0);
	}

	public Boolean valide_Prio_Pour_ChangerNonPrio(int position1, int position2, CarSequencing carSequencing) {
		return (ecarteNonPrioChangement(position1, position2, carSequencing) < 0);
	}
	public int ecartePrioChangement(int position1, int position2, CarSequencing carSequencing) {
		int totalDeltaPrio = 0;
		for (Option option : carSequencing.getListOptions()) {
			if (option.priorite) {
				totalDeltaPrio = totalDeltaPrio + delta_Option_Position(option, position1, position2, carSequencing)
						+ delta_Option_Position(option, position2, position1, carSequencing);

			}
		}
		return totalDeltaPrio;
	}

	public int ecarteNonPrioChangement(int position1, int position2, CarSequencing carSequencing) {
		int totalDeltaPrio = 0;
		for (Option option : carSequencing.getListOptions()) {
			if (!option.priorite) {
				totalDeltaPrio = totalDeltaPrio + delta_Option_Position(option, position1, position2, carSequencing)
						+ delta_Option_Position(option, position2, position1, carSequencing);

			}
		}
		return totalDeltaPrio;
	}

	public int comparation(CarSequencing carsequencing1, CarSequencing carsequencing2) {
		int res = 0;
		for (int index = 0; index < carsequencing1.getListVoitures().size(); index++) {
			if (carsequencing1.getListVoitures().get(index).getSeqRank() != carsequencing2.getListVoitures().get(index)
					.getSeqRank()) {
				// System.out.println("carSeque est " +
				// carsequencing1.getListVoitures().get(index).getSeqRank());
				res++;
			}
		}
		return res;
	}

	public Boolean check_nbrPenalite_par_delta(int position1, int position2, CarSequencing carSequencing) {
		int totalPenalitePrioAvant = carSequencing.totalPenalitePriori();
		int totalPenalitePrioAPres = changement(position1, position2,carSequencing).totalPenalitePriori();
		System.out.println(" nbr total penaliteprio avant changer: " + totalPenalitePrioAPres);
		System.out.println(" nbr total penaliteprio  apres changer: " + totalPenalitePrioAvant);
		System.out.println(
				" ecarte de totalprio  penalite : " + ecartePrioChangement(position1, position2, carSequencing));

		return (totalPenalitePrioAPres == totalPenalitePrioAvant
				+ ecartePrioChangement(position1, position2, carSequencing));
	}

	// public CarSequencing changement(int position1, int position2) {
	// CarSequencing res = new CarSequencing(inputCarSeq.getListOptions(),
	// inputCarSeq.getListVoitures(), inputCarSeq.getColorMax(),
	// inputCarSeq.getObjectives());
	// if (!estChangementSimple(position1, position2)) {
	// position2--;
	// }
	// Voiture tmp = inputCarSeq.getListVoitures().get(position1);
	// res.getListVoitures().set(position1,
	// inputCarSeq.getListVoitures().get(position2));
	// res.getListVoitures().set( position2, tmp);
	// return res;
	//
	// }

	// public CarSequencing suiteChangement() {
	// //inputCarSeq.listToutInfoFenetres = inputCarSeq.setListToutInfoFenetres();
	// CarSequencing res = valideChangementPrio();
	// listCarSequencings.add(res);
	// while (res.totalPenalitePriori() > 0) {
	// res = valideChangementPrio();
	// }
	// System.out.println("nombre de changement est " + (listCarSequencings.size()
	// -2));
	// return res;
	// }

	// public Boolean estChangementSimple(int position1, int position2) {
	// return (position1 != position2 &&
	// inputCarSeq.getListClassVoitures().get(position1) !=
	// inputCarSeq.getListClassVoitures().get(position2));
	// }
	//
	// public CarSequencing estSortie() {
	// CarSequencing res = changement();
	// while (inputCarSeq.totalPenalitePriori() == res.totalPenalitePriori()) {
	// res = changement();
	// }
	// return res;
	// }
	
	// public CarSequencing valideChangementNonPrio() {
		//
		// //CarSequencing res = new CarSequencing(copyInputOption(),
		// copyInputVoiture(), inputCarSeq.getColorMax(), inputCarSeq.getObjectives());
		// CarSequencing res = changement();
		// res.listToutInfoFenetres = res.setListToutInfoFenetres();
		// while
		// (listCarSequencings.get(listCarSequencings.size()-1).totalPenaliteNonPriori()
		// <= res.totalPenaliteNonPriori()) {
		// res = changement();
		// res.listToutInfoFenetres = res.setListToutInfoFenetres();
		// }
		// return res;
		// }
	// public int delta_Option_PositionV2(Option option, int position1, int
		// position2,CarSequencing carSequencing) {
		// int indexOption = carSequencing.getListOptions().indexOf(option);
		// Voiture voiture1 = carSequencing.getListVoitures().get(position1);
		// Voiture voiture2 = carSequencing.getListVoitures().get(position2);
		// Vector<InfoFenetre> listFenetreInfo =
		// carSequencing.listToutInfoFenetres.get(indexOption).getList_Fenetres_Info();
		// int deltaCout =0;
		// int index = (int) indexFenetreTouche(position1, option).x;
		// while (index >= indexFenetreTouche(position1, option).x && index
		// <indexFenetreTouche(position1, option).y && (index
		// <indexFenetreTouche(position2, option).x || index >=
		// indexFenetreTouche(position2, option).y) )
		// { //Fenetre fenetre = listFenetreInfo.get(index).getFenetre();
		// int infoAvant = listFenetreInfo.get(index-inputCarSeq.nbrVoitureDateMoins() +
		// option.r2 -1 ).getInfo();
		// int infoChangement =
		// listFenetreInfo.get(index-inputCarSeq.nbrVoitureDateMoins() + option.r2 -1
		// ).getInfo();
		// if (voiture1.getOptionMap().get(indexOption) &&
		// !voiture2.getOptionMap().get(indexOption)) {
		// infoChangement = infoChangement - 1;
		// }
		// if (!voiture1.getOptionMap().get(indexOption) &&
		// voiture2.getOptionMap().get(indexOption)) {
		// infoChangement = infoChangement + 1;
		// }
		// deltaCout = deltaCout + Math.max(0, infoChangement - option.r1) - Math.max(0,
		// infoAvant - option.r1);
		// index++;
		// }
		//
		//
		//
		// return deltaCout;
		//
		// }
//	public CarSequencing changement(int position1, int position2) {
//	CarSequencing res = new CarSequencing(copyInputOption(), copyInputVoiture(), inputCarSeq.getColorMax(),
//			inputCarSeq.getObjectives());
//	Voiture tmp = res.getListVoitures().get(position1);
//	res.getListVoitures().set(position1, res.getListVoitures().get(position2));
//	res.getListVoitures().set(position2, tmp);
//	res.listToutInfoFenetres = res.setListToutInfoFenetres();
//	return res;
//}
}

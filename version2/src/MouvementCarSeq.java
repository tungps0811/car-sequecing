//import java.util.Random;

import java.util.Random;
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
		int position1 = min + random.nextInt(max -min);
		int position2 = min + random.nextInt(max -min);
		return new Vec2d(position1, position2);
		}
	
	public CarSequencing changement(int position1, int position2) {
		CarSequencing res = new CarSequencing(copyInputOption(), copyInputVoiture(), inputCarSeq.getColorMax(), inputCarSeq.getObjectives());
		Voiture tmp = res.getListVoitures().get(position1);
		res.getListVoitures().set(position1,res.getListVoitures().get(position2) );
		res.getListVoitures().set(position2,tmp);
		res.listToutInfoFenetres = res.setListToutInfoFenetres();	
		return res;
	}
	
	public CarSequencing valideChangementPrio() {
		CarSequencing res = inputCarSeq;
		int penalitePrio = inputCarSeq.totalPenalitePriori();
		//while (listCarSequencings.get(listCarSequencings.size()-1).totalPenalitePriori() <= res.totalPenalitePriori()) {
			Vec2d vec = choixAleatoire();
			int position1 = (int)vec.x;
			int position2 = (int) vec.y;
//			System.out.println("position 1 " + position1);
//			System.out.println("position 2 " + position2);
			while (!valide_Prio_Pour_Changer(position1, position2,res)) {
				Vec2d vec1 = choixAleatoire();
				 position1 = (int)vec1.x;
				 position2 = (int) vec1.y;
			}
				
				penalitePrio = penalitePrio + ecartePrioChangement(position1,position2, res);
				System.out.println("nombre penalitePrio calculer par delta est " + penalitePrio );
				res = changement(position1,position2);
				System.out.println("position 1 " + position1);
				System.out.println("position 2 " + position2);
				//res.listToutInfoFenetres = res.setListToutInfoFenetres();
		
		listCarSequencings.add(res);
		System.out.println("nombre de changement est " + (listCarSequencings.size()));
		return res;
	}
	
	public Vector<Option> copyInputOption() {
		Vector<Option> copyListOption = new Vector<Option>();
		for (Option option: inputCarSeq.getListOptions()) {
			copyListOption.add(option);
		}
		return copyListOption;
	}
	public Vector<Voiture> copyInputVoiture(){
		Vector<Voiture> copyListVoiture = new Vector<Voiture>();
		for (Voiture voiture: inputCarSeq.getListVoitures()) {
			copyListVoiture.add(voiture);
			}
		return copyListVoiture;
		
	}
//	public CarSequencing valideChangementNonPrio() {
//
//		//CarSequencing res = new CarSequencing(copyInputOption(), copyInputVoiture(), inputCarSeq.getColorMax(), inputCarSeq.getObjectives());
//		CarSequencing res = changement();
//		res.listToutInfoFenetres = res.setListToutInfoFenetres();
//		while (listCarSequencings.get(listCarSequencings.size()-1).totalPenaliteNonPriori() <= res.totalPenaliteNonPriori()) {
//			res = changement();
//			res.listToutInfoFenetres = res.setListToutInfoFenetres();
//		}	
//		return res;
//	}
	
	
	
	public int delta_Option_Position(Option option, int position1, int position2,CarSequencing carSequencing) {
		int indexOption = carSequencing.getListOptions().indexOf(option);
		Voiture voiture1 = carSequencing.getListVoitures().get(position1);
		Voiture voiture2 = carSequencing.getListVoitures().get(position2);
		Vector<InfoFenetre> listFenetreInfo = carSequencing.listToutInfoFenetres.get(indexOption).getList_Fenetres_Info();
		int deltaCout =0; 
		for (InfoFenetre infoFenetre: listFenetreInfo) {
			Fenetre fenetre = infoFenetre.getFenetre();
			int infoAvant = infoFenetre.getInfo();
			int infoChangement =  infoFenetre.getInfo();
		if (check_Fenetre_Position(position1,fenetre) && !check_Fentre_Commun(position1,position2,fenetre)) {
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
		return (fenetre.getDebut() <= position && position < (fenetre.getDebut()+fenetre.getTaille()));
	}
	
	public Boolean check_Fentre_Commun(int position1, int position2, Fenetre fenetre) {
		return (check_Fenetre_Position(position1,fenetre) && check_Fenetre_Position(position2,fenetre));
	}
	
	public Boolean valide_Prio_Pour_Changer(int position1, int position2, CarSequencing carSequencing) {
		return (ecartePrioChangement(position1, position2, carSequencing) < 0);
	}
	public int ecartePrioChangement(int position1, int position2, CarSequencing carSequencing) {
		int totalDeltaPrio = 0;
		for (Option option: carSequencing.getListOptions()) {
			if (option.priorite) {
				totalDeltaPrio = totalDeltaPrio + delta_Option_Position(option, position1, position2,carSequencing)+ delta_Option_Position(option,position2,position1, carSequencing);
				
			}
		}	
		return totalDeltaPrio;
	}
	
	public int ecarteNonPrioChangement(int position1, int position2, CarSequencing carSequencing) {
		int totalDeltaPrio = 0;
		for (Option option: carSequencing.getListOptions()) {
			if (!option.priorite) {
				totalDeltaPrio = totalDeltaPrio + delta_Option_Position(option, position1, position2,carSequencing)+ delta_Option_Position(option,position2,position1, carSequencing);
				
			}
		}	
		return totalDeltaPrio;
	}
	
	public int comparation(CarSequencing carsequencing1, CarSequencing carsequencing2) {
		int res = 0;
		for (int index = 0; index <carsequencing1.getListVoitures().size(); index++) {
			if (carsequencing1.getListVoitures().get(index).getSeqRank() != carsequencing2.getListVoitures().get(index).getSeqRank()) {
				//System.out.println("carSeque est " + carsequencing1.getListVoitures().get(index).getSeqRank());
				res++;
			}
		}
		return res;
	}

	
//	public CarSequencing changement(int position1, int position2) {
//	CarSequencing res = new CarSequencing(inputCarSeq.getListOptions(), inputCarSeq.getListVoitures(), inputCarSeq.getColorMax(), inputCarSeq.getObjectives());
//	if (!estChangementSimple(position1, position2)) {
//		position2--;
//	}
//	Voiture tmp = inputCarSeq.getListVoitures().get(position1);
//	res.getListVoitures().set(position1, inputCarSeq.getListVoitures().get(position2));
//	res.getListVoitures().set( position2, tmp);
//	return res;
//
//}	
	
	
//	public CarSequencing suiteChangement() {
//		//inputCarSeq.listToutInfoFenetres = inputCarSeq.setListToutInfoFenetres();
//		CarSequencing res = valideChangementPrio();
//		listCarSequencings.add(res);
//		while (res.totalPenalitePriori() > 0) {
//			res = valideChangementPrio();
//		}
//		System.out.println("nombre de changement est " + (listCarSequencings.size() -2));
//		return res;
//	}
	
//	public Boolean estChangementSimple(int position1, int position2) {
//	return (position1 != position2 && inputCarSeq.getListClassVoitures().get(position1) != inputCarSeq.getListClassVoitures().get(position2));
//}
//
//public CarSequencing estSortie() {
//	CarSequencing res = changement();
//	while (inputCarSeq.totalPenalitePriori() == res.totalPenalitePriori()) {
//		res = changement();
//	}
//	return res;
//}
}

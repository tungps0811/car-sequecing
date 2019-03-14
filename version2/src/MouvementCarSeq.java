//import java.util.Random;

import java.util.Random;
import java.util.Vector;

import com.sun.javafx.geom.Vec2d;

public class MouvementCarSeq {
	private CarSequencing inputCarSeq;
	private Vector<CarSequencing> listCarSequencings = new Vector<CarSequencing>();
	
	
	public MouvementCarSeq(CarSequencing inputCarSeq) {
		this.inputCarSeq = inputCarSeq;
		listCarSequencings.add(inputCarSeq);
		
	}
	
	public CarSequencing changement() {
	
		//supposons toujours que position1 < position2;
		Vec2d vec = choixAleatoire();
		int position1 = (int)vec.x;
		int position2 = (int) vec.y;
		System.out.println("position 1 " + position1);
		System.out.println("position 2 " + position2);
		
		CarSequencing res = new CarSequencing(copyInputOption(), copyInputVoiture(), inputCarSeq.getColorMax(), inputCarSeq.getObjectives());
		 
		Voiture tmp = res.getListVoitures().get(position1);
		res.getListVoitures().set(position1,res.getListVoitures().get(position2) );
		res.getListVoitures().set(position2,tmp);
		return res;
		
	}
	
//	public CarSequencing changement(int position1, int position2) {
//		CarSequencing res = new CarSequencing(inputCarSeq.getListOptions(), inputCarSeq.getListVoitures(), inputCarSeq.getColorMax(), inputCarSeq.getObjectives());
//		if (!estChangementSimple(position1, position2)) {
//			position2--;
//		}
//		Voiture tmp = inputCarSeq.getListVoitures().get(position1);
//		res.getListVoitures().set(position1, inputCarSeq.getListVoitures().get(position2));
//		res.getListVoitures().set( position2, tmp);
//		return res;
//	
//	}
	
	public Vec2d choixAleatoire() {
		int min = inputCarSeq.nbrVoitureDateMoins();
		int max = inputCarSeq.getListVoitures().size() - 1;
		Random random = new Random();
		int position1 = min + random.nextInt(max -min);
		int position2 = min + random.nextInt(max -min);
//		if (position1 > position2) {
//			int tmp = position1;
//			position1 = position2;
//			position2 = tmp;
//		}
		
		return new Vec2d(position1, position2);
		
			
	}
	
//	public Boolean estChangementSimple(int position1, int position2) {
//		return (position1 != position2 && inputCarSeq.getListClassVoitures().get(position1) != inputCarSeq.getListClassVoitures().get(position2));
//	}
//	
//	public CarSequencing estSortie() {
//		CarSequencing res = changement();
//		while (inputCarSeq.totalPenalitePriori() == res.totalPenalitePriori()) {
//			res = changement();
//		}
//		return res;
//	}
	
	public int comparation(CarSequencing carsequencing1, CarSequencing carsequencing2) {
		int res = 0;
		for (int index = 0; index <carsequencing1.getListVoitures().size(); index++) {
			if (carsequencing1.getListVoitures().get(index).getSeqRank() != carsequencing2.getListVoitures().get(index).getSeqRank()) {
				res++;
			}
		}
		return res;
	}
	
	
	
	public Vector<Option> copyInputOption() {
		Vector<Option> copyListOption = new Vector<Option>();
		for (Option option: inputCarSeq.getListOptions()) {
			//Option copyOption = new Option(option.r1,option.r2,option.priorite, option.nomOption);
			//copyListOption.add(copyOption);
			copyListOption.add(option);
			
		}
		return copyListOption;
	}
	public Vector<Voiture> copyInputVoiture(){
		Vector<Voiture> copyListVoiture = new Vector<Voiture>();
		for (Voiture voiture: inputCarSeq.getListVoitures()) {
//			Voiture copyVoiture = new Voiture(voiture.getDate(), voiture.getSeqRank(), voiture.getIdent(), voiture.getPainColor(), copyInputOptionMap(voiture));
//			copyListVoiture.add(copyVoiture);
			copyListVoiture.add(voiture);
			}
		return copyListVoiture;
		
	}
	
//	public Vector<Boolean> copyInputOptionMap(Voiture voiture){
//		Vector<Boolean> copyOptionMap = new Vector<Boolean>();
//		for (Boolean map: voiture.getOptionMap()) {
//			Boolean copyMap = new Boolean(map);
//			copyOptionMap.add(copyMap);
//		}
//		return copyOptionMap;
//		
//	}

	public void copyInput1() {
		Vector<Option> copyListOption = new Vector<Option>();
		for (Option option: inputCarSeq.getListOptions()) {
			Option copyOption = new Option(option.r1,option.r2,option.priorite, option.nomOption);
			copyListOption.add(copyOption);
		}
		Vector<Voiture> copyListVoiture = new Vector<Voiture>();
		for (Voiture voiture: inputCarSeq.getListVoitures()) {
			Voiture copyVoiture = new Voiture(voiture.getDate(), voiture.getSeqRank(), voiture.getIdent(), voiture.getPainColor(), voiture.getOptionMap());
			copyListVoiture.add(copyVoiture);
		}
		
	}
	public CarSequencing estSortieTest() {
		inputCarSeq.listToutInfoFenetres = inputCarSeq.setListToutInfoFenetres();
		//CarSequencing res = new CarSequencing(copyInputOption(), copyInputVoiture(), inputCarSeq.getColorMax(), inputCarSeq.getObjectives());
		CarSequencing res = changement();
		res.listToutInfoFenetres = res.setListToutInfoFenetres();
		while (inputCarSeq.totalPenalitePriori() <= res.totalPenalitePriori()) {
			res = changement();
			res.listToutInfoFenetres = res.setListToutInfoFenetres();
		}	
		
//		while (inputCarSeq.totalPenalitePriori() == res.totalPenalitePriori()) {
//			res = changement(res);
//		}
		return res;
	}
}

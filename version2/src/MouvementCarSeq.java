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
		CarSequencing res = new CarSequencing(inputCarSeq.getListOptions(), inputCarSeq.getListVoitures(), inputCarSeq.getColorMax(), inputCarSeq.getObjectives());
//		if (!estChangementSimple(position1, position2)) {
//			vec = choixAleatoire();
//			position1 = (int)vec.x;
//			position2 = (int) vec.y;
//		}
		Voiture tmp = inputCarSeq.getListVoitures().get(position1);
		res.getListVoitures().setElementAt(inputCarSeq.getListVoitures().get(position2), position1);
		res.getListVoitures().setElementAt(tmp, position2);
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
		if (position1 > position2) {
			int tmp = position1;
			position1 = position2;
			position2 = tmp;
		}
		System.out.println("position 1 " + position1);
		System.out.println("position 2 " + position2);
		return new Vec2d(position1, position2);
		
			
	}
	
	public Boolean estChangementSimple(int position1, int position2) {
		return (position1 != position2 && inputCarSeq.getListClassVoitures().get(position1) != inputCarSeq.getListClassVoitures().get(position2));
	}
	
	public CarSequencing estSortie() {
		CarSequencing res = changement();
		while (inputCarSeq.totalPenalitePriori() == res.totalPenalitePriori()) {
			res = changement();
		}
		return res;
	}
	
	
	
	

}

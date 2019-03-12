//import java.util.Random;

import java.util.Random;
import java.util.Vector;

import com.sun.javafx.geom.Vec2d;


public class MouvementCarSeq {
	private CarSequencing inputCarSeq;
	private long cout;
	private Vector<CarSequencing> listeCarSeq;
	
	public MouvementCarSeq(CarSequencing inputCarSeq) {
		this.inputCarSeq = new CarSequencing(inputCarSeq.getListOptions(), inputCarSeq.getListVoitures(), inputCarSeq.getColorMax(), inputCarSeq.getObjectives());		
//		cout = inputCarSeq.CoutTotal();
		listeCarSeq = new Vector<>();
		listeCarSeq.add(inputCarSeq);
	}
	
	public CarSequencing changement() {
		CarSequencing carSeq = listeCarSeq.get(listeCarSeq.size()-1);
		Vec2d vector = choixAleatoire();
		int position1 = (int)vector.x;		
		int position2 = (int)vector.y;
		
		
		//supposons toujours que position1 < position2;		
		if (!estChangementSimple(position1, position2)) {
			System.out.println("csa sert a rien de changement");
			return carSeq;
		}

		Voiture tmp = carSeq.getListVoitures().get(position1);
		Voiture voitureAtPos2 = carSeq.getListVoitures().get(position2);
		
		carSeq.getListVoitures().set(position1, voitureAtPos2);
		carSeq.getListVoitures().set(position2, tmp);
		
		return carSeq;		
	}
	
	public Vec2d choixAleatoire() {
		CarSequencing carSeq = listeCarSeq.get(listeCarSeq.size()-1);
		int min = carSeq.nbrVoitureDateMoins();
		int max = carSeq.getListVoitures().size() - 1;
		Random random = new Random();
		int position1 = min + random.nextInt(max-min);
		int position2 = min + random.nextInt(max-min);
		
		
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
	
	public Boolean estChangementSimple(int position1, int position2) {
		return (position1 != position2 && inputCarSeq.getListClassVoitures().get(position1) != inputCarSeq.getListClassVoitures().get(position2));
	}

}

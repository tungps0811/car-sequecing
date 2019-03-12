//import java.util.Random;

import com.sun.javafx.geom.Vec2d;

public class MouvementCarSeq {
	private CarSequencing inputCarSeq;
	private long cout;
	
	public MouvementCarSeq(CarSequencing inputCarSeq) {
		this.inputCarSeq = inputCarSeq;
		cout = inputCarSeq.CoutTotal();
	}
	
	public CarSequencing changement() {
	
		//supposons toujours que position1 < position2;
		CarSequencing res = inputCarSeq;
		if (!estChangementSimple((int)choixAleatoire().x, (int)choixAleatoire().y)) {
			System.out.println("csa sert a rien de changement");
			return inputCarSeq;
		}
		Voiture tmp = inputCarSeq.getListVoitures().get((int) choixAleatoire().x);
		res.getListVoitures().setElementAt(inputCarSeq.getListVoitures().get((int)choixAleatoire().y), (int) choixAleatoire().x);
		res.getListVoitures().setElementAt(tmp, (int)choixAleatoire().y);
		return res;
		
	}
	
	public Vec2d choixAleatoire() {
		int min = inputCarSeq.nbrVoitureDateMoins();
		int max = inputCarSeq.getListVoitures().size() - 1;
		//Random random = new Random();
		int position1 = (min + (int)Math.random()* (max-min));
				//random.nextInt(max -min);
		int position2 =  (min + (int )Math.random()* (max-min));
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
	
	
	
	
	
	

}

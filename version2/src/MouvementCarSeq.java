
public class MouvementCarSeq {
	private CarSequencing inputCarSeq;
	
	public MouvementCarSeq(CarSequencing inputCarSeq) {
		this.inputCarSeq = inputCarSeq;
	}
	
	public CarSequencing changement(int position1, int position2) {
		//supposons toujours que position1 < position2;
		CarSequencing res = inputCarSeq;
		if (position1 < inputCarSeq.nbrVoitureDateMoins() || position2 >= inputCarSeq.getListVoitures().size()) {
			return inputCarSeq;
		}
		Voiture tmp = inputCarSeq.getListVoitures().get(position1);
		res.getListVoitures().setElementAt(inputCarSeq.getListVoitures().get(position2), position1);
		res.getListVoitures().setElementAt(tmp, position2);
		return res;
		
	}
	
	

}

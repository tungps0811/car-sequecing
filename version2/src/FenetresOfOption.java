import java.util.Vector;

public class FenetresOfOption {
	//liste des fenetres pour une option donnée.
	protected Vector<Fenetre> listFenetresOfOption;
	private Option option;
	private int debut; //debut: nombre des voitures dans la date J-1;
	private Vector<Voiture> listVoitures;
	
	public FenetresOfOption(Option option,Vector<Voiture>listVoitures, int debut ) {
		this.option = option;
		listFenetresOfOption = creeFenetresOfOption(listVoitures,debut);
	}
	
	public Vector<Fenetre> getFenetresOfOption() {
		return listFenetresOfOption;
	}
	
	public Vector<Fenetre> creeFenetresOfOption(Vector<Voiture> listVoitures, int debut){
		//index debut: nombre des voitures dans la date J-1;
		Vector<Fenetre> res =new Vector<Fenetre>();
		int r1 = option.r1;
		int r2 = option.r2;
		for (int index = debut - r2 + 1; index <= listVoitures.size() - r2; index++  ) {
			Fenetre fenetre = new Fenetre(index,r2,option);
			res.add(fenetre);
		}
		for (int taille = r1 +1; taille < r2; taille++) {
			Fenetre fenetre = new Fenetre(listVoitures.size() - taille,taille,option);
			res.add(fenetre);
			
		}
		return res;
		
	}
	
	public Boolean check_fenetre(Fenetre fenetre) {
		if (listFenetresOfOption.contains(fenetre))
			return true;
		return false;
	}

}

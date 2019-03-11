import java.util.Vector;

public class InfoFenetresOption {
	public Vector<InfoFenetre> list_Fenetres_Info;
	private Option option;

	public InfoFenetresOption(Option option) {
		this.option = option;
		list_Fenetres_Info = new Vector<InfoFenetre>();
	}
	
	public Option getOption() {
		return option;
	}
	
	public Vector<InfoFenetre> getList_Fenetres_Info() {
		return list_Fenetres_Info;
	}
	
	public int nbrVoitureDateMoins(Vector<Voiture> listVoitures) {
		int index = 0;
		while (listVoitures.get(index).getDate().equals(listVoitures.get(0).getDate()) )
			index++;
		return index;
	}
	public void setList_Fenetres_Info(Vector<Voiture> listVoitures, Vector<Option> listOptions){
		//index debut: nombre des voitures dans la date J-1;
		int r1 = option.r1;
		int r2 = option.r2;
		for (int index = nbrVoitureDateMoins(listVoitures) - r2 + 1; index <= listVoitures.size() - r2; index++  ) {
			Fenetre fenetre = new Fenetre(index,r2);
			int info = getInfoFenetre(listVoitures, listOptions, fenetre);
			InfoFenetre  infoFenetre= new InfoFenetre(fenetre, info);
			list_Fenetres_Info.add(infoFenetre);
		}
		for (int taille = r1 +1; taille < r2; taille++) {
			Fenetre fenetre = new Fenetre(listVoitures.size() - taille,taille);
			int info = getInfoFenetre(listVoitures, listOptions, fenetre);
			InfoFenetre  infoFenetre= new InfoFenetre(fenetre, info);
			list_Fenetres_Info.add(infoFenetre);
		}
	}
	
	public int getInfoFenetre(Vector<Voiture> listVoitures, Vector<Option> listOptions, Fenetre fenetre){
		int res = 0;
		for (int index = fenetre.getDebut(); index <fenetre.getDebut() + fenetre.getTaille(); index++) {
			if (listVoitures.get(index).getOptionMap().get(listOptions.indexOf(option)))
				res ++;
		}
		return res;
	}
	public Boolean check_fenetre(Fenetre fenetre) {
		for (InfoFenetre infoFenetre: list_Fenetres_Info ) {
			if (infoFenetre.getFenetre().equals(fenetre))
				return true;
		}
		return false;
	}
	
//	public int get_Index_Of_Fenetre(Fenetre fenetre) {
//		if (check_fenetre(fenetre))
//			return listFenetres.indexOf(fenetre);
//		return -1;
//	}
	
	

}

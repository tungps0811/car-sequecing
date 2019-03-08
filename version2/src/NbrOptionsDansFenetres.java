import java.util.Vector;

public class NbrOptionsDansFenetres extends FenetresOfOption{
	public Vector<Option> listOptions;
	public Vector<Integer> listNbrOptions;

	
	//element of listNbrOptions présente les nombre des voitures qui ont l'option pour chaque option donnée et chaque fenetre.
	//la liste 
	public NbrOptionsDansFenetres( Option option, Vector<Voiture>listVoitures, int debut, Vector<Option> listOptions) {
		super(option,listVoitures,debut);
		this.listOptions = listOptions;
		listNbrOptions = setNbrOptions(listVoitures, listOptions,listFenetres);
	}
	
	public Vector<Integer>  setNbrOptions(Vector<Voiture> listVoitures, Vector<Option> listOptions,Vector<Fenetre> listFenetres){
		Vector<Integer> res = new Vector<Integer>();
		for (Fenetre fenetre: listFenetres) {
			res.add(fenetre.nbrOption(listVoitures, listOptions));
		}
		return res;
	}
	
	
	

}

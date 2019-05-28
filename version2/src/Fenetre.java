import java.util.Vector;

public class Fenetre {
	private int indexFenetre;	
	private Option option;	
	private int nombreOption;
		
	public Fenetre(int indexFentre, Option option, Vector<Voiture> listVoitures) {
		this.indexFenetre = indexFentre;
		this.option = option;
		this.nombreOption = optionDansFenetre(listVoitures);
	}
	
	public int getIndexFenetre() {
		return indexFenetre;
	}
	
	public Option getOption() {
		return option;
	}
	
	public int getNombreOption() {
		return nombreOption;
	}
	
	protected int optionDansFenetre(Vector<Voiture> listVoitures) {
		int res = 0;
		int taille = indexFenetre + option.r2;
		if ( taille >= listVoitures.size() ) taille = listVoitures.size(); 
		for (int i = indexFenetre; i < taille; i++) {
			if (listVoitures.get(i).hasOption(option)) res++;
		}		
		return res;
	}		
}

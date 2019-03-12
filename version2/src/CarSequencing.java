import java.util.HashMap;
import java.util.Vector;


public class CarSequencing {
	//inputs
	private Vector<Option> listOptions;
	private Vector<Voiture> listVoitures;
	//private ClassVoiture[] listClassVoitures;
	private Vector<Integer> listClassVoitures;
	private int colorMax;
	private Vector<String> objectives;
    public Vector<InfoFenetresOption> listToutInfoFenetres;
	//etats pour sauvgarder
	
	//private Vector<Vector<Integer>> 
//	private Vector<FenetresOfOption> listDesFenetresOfOption; // liste des fenêtres pour toutes les options
//	
//	private Vector<NbrOptionsDansFenetres> listDesNbrsOptionsFenetre; // liste des nombres de options dans toute les fenêtres
	
	
	public CarSequencing(Vector<Option> listOptions, Vector<Voiture> listVoitures, int colorMax, Vector<String> objectives) {
		this.listOptions = listOptions;
		this.listVoitures = listVoitures;
		listClassVoitures = listClassVoitures1();
//		listDesFenetresOfOption = initListDesFenetresOfOption();
//		listDesNbrsOptionsFenetre = initListDesNbrsOptionsFenetre();
		listToutInfoFenetres = getListToutInfoFenetres();
		this.objectives = objectives;
		this.colorMax = colorMax;
	
	}
	
	
	 public Vector<InfoFenetresOption> getListToutInfoFenetres() {
		 Vector<InfoFenetresOption> res = new  Vector<InfoFenetresOption>();
		 for (Option option: listOptions) {
			 InfoFenetresOption infoFenetresOption = new  InfoFenetresOption(option);
			 infoFenetresOption.setList_Fenetres_Info(listVoitures, listOptions);
			 res.add(infoFenetresOption);
		 }
		 return res;
		 
	 }
//	 public Vector<FenetresOfOption> initListDesFenetresOfOption(){
//		 Vector<FenetresOfOption> res = new Vector<FenetresOfOption>();
//		 for (Option option: listOptions) {
//			FenetresOfOption fenetresOfOption = new FenetresOfOption();
//			fenetresOfOption.listFenetres=fenetresOfOption.creeFenetresOfOption(listVoitures, option);
//			res.add(fenetresOfOption);
//		 }
//		 return res;
//	 }
//	 
//	 public Vector<NbrOptionsDansFenetres> initListDesNbrsOptionsFenetre(){
//		 Vector<NbrOptionsDansFenetres> res = new Vector<NbrOptionsDansFenetres>();
//		 for (Option option: listOptions) {
//			 NbrOptionsDansFenetres nbr = new NbrOptionsDansFenetres(option,listVoitures,listOptions);
//			 res.add(nbr);
//		 }
//			 return res;
//	 }
//	
	
	public Vector<Option> getListOptions() {
		return listOptions;
	}
	
	public Vector<Voiture> getListVoitures() {
		return listVoitures;
	}
	
	public int nbrContraintsPrio() {
		int res = 0;
		for (Option option: listOptions) {
			if (option.priorite)
				res++;
		}
		return res;
	}

	public int nbrContraintsNonPrio() {
		int res = 0;
		for (Option option: listOptions) {
			if (!option.priorite)
				res++;
		}
		return res;
	}
	
	public Vector<String> getObjectives() {
		return objectives;
	}
	public  HashMap<ClassVoiture, Integer> MapClassVoiture() {
		 HashMap<ClassVoiture, Integer> mapClass = new HashMap<ClassVoiture, Integer>();
		 int k = 0;
		 int index = 0;
		 while (index < listVoitures.size()) {
			if (!mapClass.containsKey(listVoitures.get(index).Information())){
				mapClass.put(listVoitures.get(index).Information(), k+1);
				k++;
			}
			
			index ++;
		 }
		 
		 return mapClass;
		
		 
	}
	
	public ClassVoiture[] listClassVoitures() {
		ClassVoiture[] res = new ClassVoiture[listVoitures.size()];
		for (int index = 0; index < listVoitures.size(); index++) {
			res[index] = numClassOfVoiture(listVoitures.get(index));
		}
		return res;
	}
	
	public Vector<Integer> listClassVoitures1(){
		Vector<Integer> res = new Vector<Integer>();
		for (Voiture voiture: listVoitures) {
			res.add(MapClassVoiture().get(voiture.Information()));
		}
		return res;
		
	}
	
	public int nbrVoitureInClass1(int numClass) {
		if (numClass <1 || numClass > MapClassVoiture().size())
			return -1000;
		int res = 0;
		for (int numC : listClassVoitures1()) {
			if (numC == numClass)
				res++;
		}
		return res;
	}
	public void printlistClassVoiture() {
		for (int index =0; index < listVoitures.size(); index++)
			System.out.print(listClassVoitures()[index].getNumClass() + " ");
	}
	public ClassVoiture numClassOfVoiture(Voiture voiture) {
		ClassVoiture res = voiture.Information();
		res.setNumClass(MapClassVoiture().get(res));
		return res;
	}
	
	
	public int nbrVoitureDateMoins() {
		int index = 0;
		while (listVoitures.get(index).getDate().equals(listVoitures.get(0).getDate()) )
			index++;
		return index;
	}
	
	
	public int maxFenetre() {
		int res = listOptions.get(0).r2;
		for (int index = 1; index <listOptions.size();index++) {
			if (listOptions.get(index).r2 >res)
				res = listOptions.get(index).r2;
		}
		return res;
	}
	
	public int minFenetre() {
		int res = listOptions.get(0).r2;
		for (int index = 1; index <listOptions.size();index++) {
			if (listOptions.get(index).r2 < res)
				res = listOptions.get(index).r2;
		}
		return res;
	}
	
	public int getColorMax() {
		return colorMax;
	}
	
	public Vector<Integer> getListClassVoitures() {
		return listClassVoitures;
	}
	public int indexOfOption(Option option) {
		return listOptions.indexOf((Object)option);
	}
	
//	public int penaliteFenetreOption(int debut,int taille, Option option) {
//		int r1 = option.r1;
//		int nbr = 0;
//		for (int index = debut; index <debut + taille; index ++) {
//			if (listVoitures.get(index).getOptionMap().get(listOptions.indexOf(option)));
//				nbr++;
//		}
//		return Math.max(0, nbr-r1); // return 0 if nbr-r1 < 0
//	}

	public int penaliteFenetreOption(Option option, Fenetre fenetre) {
		int indexOpt = indexOfOption(option);
		for (InfoFenetre infoFenetre: listToutInfoFenetres.get(indexOpt).list_Fenetres_Info) {
			if (infoFenetre.getFenetre().equals(fenetre))
				return Math.max(infoFenetre.getInfo()-option.r1,0);
		}
		
		return 0;
	}
	
	
	
//	public int totalPenaliteOption(Option option) {
//		int r1 = option.r1;
//		int r2 = option.r2;
//		int res = 0;
//		for (int index = nbrVoitureDateMoins() - r2 + 1; index <= listVoitures.size() - r2; index++  ) {
//			res = res + penaliteFenetreOption(index,r2, option); 
//		}
//		for (int taille = r1 +1; taille < r2; taille++) {
//			res = res + penaliteFenetreOption(listVoitures.size() - taille , taille, option);
//		}
//		return res;
//	}
//	
	public int totalPenaliteOption(Option option) {
		int res = 0;
		int indexOpt = indexOfOption(option);
		for (InfoFenetre infoFenetre: listToutInfoFenetres.get(indexOpt).list_Fenetres_Info)
			res = res + Math.max(0,infoFenetre.getInfo() -option.r1);
		return res;
	}
	
	public int totalPenalitePriori() {
		int res = 0;
		for (Option option: listOptions) {
			if (option.priorite)
				res = res + totalPenaliteOption(option); 
		}
		return res;
	}
	
	public int totalPenaliteNonPriori() {
		int res = 0;
		for (Option option: listOptions) {
			if (!option.priorite)
				res = res + totalPenaliteOption(option); 
		}
		return res;
	}
	
	public int penaliteCouleur() {		
		int nbr_purge = 0 ; 		
		//definition couleur premiere voiture		
		int current_couleur = listVoitures.get(nbrVoitureDateMoins()-1).getPainColor();
		int nbr_voiture_caracterise = 1 ;
		
		for (int i = nbrVoitureDateMoins()  ; i < listVoitures.size()  ; i++) {	
			
			if ( current_couleur == listVoitures.get(i).getPainColor() ) {
				nbr_voiture_caracterise++;
//				System.out.println(nbr_voiture_caracterise);
				if (nbr_voiture_caracterise > getColorMax()) {					
					nbr_voiture_caracterise = 1 ;
					nbr_purge++;
					System.out.print("(*max*)");
				}				
			}
			else {				
				nbr_purge++;
				nbr_voiture_caracterise = 1 ;
				System.out.print("(*colorChange*)");
				current_couleur = listVoitures.get(i).getPainColor(); // update current paint color
			}
			System.out.print("("+listVoitures.get(i).getPainColor()+")");
		}
		System.out.println();
		return nbr_purge;		
	}
	
	
	public long CoutTotal() {
		return 10000 * totalPenalitePriori() + 100* totalPenaliteNonPriori() + 1*penaliteCouleur();
	}
	
	

	@Override 
	public String toString() {
		return "The total options: " + listOptions.size() + "; the total vehicule: " + listVoitures.size(); 
	}

	
}

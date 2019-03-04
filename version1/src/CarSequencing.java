import java.util.HashMap;
import java.util.Vector;


public class CarSequencing {
	private Vector<Option> listOptions;
	private Vector<Voiture> listVoitures;
	private int colorMax;
	
	public CarSequencing(Vector<Option> listOptions, Vector<Voiture> listVoitures, int colorMax) {
		this.listOptions = listOptions;
		this.listVoitures = listVoitures;
		this.colorMax = colorMax;
	}
	
	

	public Vector<Voiture> getListVoitures() {
		return listVoitures;
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
	
	
	public int getColorMax() {
		return colorMax;
	}
	@Override 
	public String toString() {
		return "The total options: " + listOptions.size() + "; the total vehicule: " + listVoitures.size(); 
	}

	
}

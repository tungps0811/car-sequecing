import java.util.Vector;

public class CarSequencing {
	private Vector<Option> listOptions;
	public Vector<Voiture> listVoitures;
	
	public CarSequencing(Vector<Option> listOptions, Vector<Voiture> listVoitures) {
		this.listOptions = listOptions;
		this.listVoitures = listVoitures;
	}
	
	@Override 
	public String toString() {
		return "The total options: " + listOptions.size() + "; the total vehicule: " + listVoitures.size(); 
	}
}

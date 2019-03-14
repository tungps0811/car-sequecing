
import java.util.Vector;

public class Voiture{ //extends ClassVoiture{
	private String date; // le but est distinguer les dates J-1 et J dans fichiers vehicules.
	private int seqRank;
	private String ident;
	private int painColor;
	private Vector<Boolean> optionMap;
	private int numeroClass;
	
	
	public Voiture(String date, int seqRank, String ident, int painColor,Vector<Boolean> optionMap) {
		this.date = date;
		this.seqRank = seqRank;
		this.ident = ident;
		this.painColor = painColor;
		this.optionMap = optionMap;
		
		//numeroClass = 0;
	}
	
	public String getDate() {
		return date;
	}
	
	public int getSeqRank() {
		return seqRank;
	}
	
	public String getIdent() {
		return ident;
	}
	
	public int getPainColor() {
		return painColor;
	}
	
	public Vector<Boolean> getOptionMap() {
		return optionMap;
	}
	
//	public int getNumeroClass() {
//		return numeroClass;
//	}
//	public void setNumeroClass() {
//		CarSequencing.getClassVoiture(this);
//	}

//	
//	public ClassVoiture Information() {
//		ClassVoiture res = new ClassVoiture(super.getPainColor(),super.getOptionMap());
//		return res;
//	}	
	
}

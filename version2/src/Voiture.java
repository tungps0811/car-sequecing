import java.util.Vector;

public class Voiture extends ClassVoiture{
	private String date; // le but est distinguer les dates J-1 et J dans fichiers vehicules.
	private int seqRank;
	private String ident;		
	
	public Voiture(String date, int seqRank, String ident, int painColor,Vector<Boolean> optionMap) {
		super(painColor,optionMap);
		this.date = date;
		this.seqRank = seqRank;
		this.ident = ident;
		
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
	
	// checked -- si une voiture a une option?
	public boolean hasOption(Option option) {		
		return getOptionMap().get(option.optionIndex);
	}
	
	public ClassVoiture Information() {
		ClassVoiture res = new ClassVoiture(super.getPainColor(),super.getOptionMap());
		return res;
	}	
	
}

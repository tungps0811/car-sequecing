


import java.util.HashMap;
//import sun.util.calendar.BaseCalendar.Date;

public class Voiture extends ClassVoiture{
	private String date; // le but est distinguer les dates J-1 et J dans fichiers vehicules.
	private int seqRank;
	private String ident;
	//private int painColor;
	//private HashMap<String, Boolean> optionMap;
	//private int numeroClass;
	
	
	public Voiture(String date, int seqRank, String ident, int painColor,HashMap<String, Boolean> optionMap) {
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
	
	
	
//	public int getNumeroClass() {
//		return numeroClass;
//	}
//	public void setNumeroClass() {
//		CarSequencing.getClassVoiture(this);
//	}

	
	public ClassVoiture Information() {
		ClassVoiture res = new ClassVoiture(super.getPainColor(),super.getOptionMap());
		return res;
	}	
	
}

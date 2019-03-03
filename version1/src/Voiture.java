


import java.util.HashMap;
//import sun.util.calendar.BaseCalendar.Date;

public class Voiture {
	private String date; // le but est distinguer les dates J-1 et J dans fichiers vehicules.
	private int seqRank;
	private String ident;
	private int painColor;
	private HashMap<String, Boolean> optionMap;
	private int numeroClass;
	
	
	public Voiture(String date, int seqRank, String ident, int painColor,HashMap<String, Boolean> optionMap) {
		this.date = date;
		this.seqRank = seqRank;
		this.ident = ident;
		this.optionMap = optionMap;
		numeroClass = 0;
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
	
	public HashMap<String, Boolean> getHasOption() {
		return optionMap;
	}
	
	public int getNumeroClass() {
		return numeroClass;
	}
	public void setNumeroClass(int num) {
		numeroClass = num;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Vehicle: date: " + date + "; seqRank: " + seqRank + "; ident: " + ident + "; painColor: " + painColor + "; Options:" + optionMap.toString();
	}
}

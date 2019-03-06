import java.util.Vector;

public class OrdreGeneration {	
	private int violation;
	private Vector<Voiture> ordre;
	
	public OrdreGeneration(int violation, Vector<Voiture> ordre) {		
		this.violation = violation;
		this.ordre = ordre;
	}

	public void setViolation(int violation) {
		this.violation = violation;
	}

	public void setOrdre(Vector<Voiture> ordre) {
		this.ordre = ordre;
	}	
	
	public String toString() {
		String res= "";
		
		return res;
	}
}

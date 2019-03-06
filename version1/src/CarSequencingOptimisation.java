import java.util.Vector;

// pour les changement de l'ordre de la chaine 
// plutôt une interface

public class CarSequencingOptimisation extends CarSequencing {
	
	private Vector<OrdreGeneration> ordreRecord; // pour tout les ordre qu'on a éffectuer // il faut trouver autre type
	
	// constructor
	public CarSequencingOptimisation(Vector<Option> listOptions, Vector<Voiture> listVoitures, int colorMax,
			Vector<OrdreGeneration> ordreGenerations) {
		super(listOptions, listVoitures, colorMax);
		this.ordreRecord = ordreGenerations;
	}
	
	public void violationConcernantVoiture () {
		
	}
	
	public void chercherLeMeilleur (/* liste of voiture */) {		
		// calculer le coût quand on déplace la voiture à la fin vers l'entête de la liste (et pousser toute les voitures) et voir si ça va être meilleur avant de faire le movement
			
			// si c n'est pas la même classe, calculer le cas suivant
				// si c'est meilleur
				// il faut sauvegarder l'ordre qui coûte le moins cher et la valeur dans un tableau ???
				// faire le changement
			// sinon déplacer la voiture suivant
		// sinon calculer le cas la voiture suivant
	}
	
	public void changement (/* deux position dans le tableau qu'on a trouvé par la méthode chercherLeMeilleur */) {
		// déplacer selon le changement donné 
	}
	
	
	
}

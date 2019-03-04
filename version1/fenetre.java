package version2;

import java.util.Vector;

public class fenetre {

	Vector<voiture> voitures;
	int taille;

	
	// construction fenetre de la voiture n°debut à celle n°début+taille
	public fenetre(Vector<voiture> voitures, int taille, int debut) {
		for (int i= debut ; i < taille ; i++) {
			this.voitures.addElement(voitures.get(i));
		}
	}
	
	
	public boolean check_fenetre(int nbr_a_respecter,int index_option) {
		int nbr_voiture_caracterise = 0 ; 
		
		
		for (int i = 0  ; i < voitures.size()  ; i++) {
			if(voitures.get(i).getOptions().get(index_option)) 
			{
				nbr_voiture_caracterise++;
			}
		}
		
		
		if ( nbr_voiture_caracterise > nbr_a_respecter) {
			return true;
		}
		return false;
		
	}
	
/*	
	public boolean check_fenetre(int nbr_a_respecter, int batch_paint) {
		int nbr_voiture_caracterise = 1 ; 
		int nbr_violation_contrainte =0 ; 
		
		int current_couleur = voitures.get(0).getColor();
		for (int i = 0  ; i < voitures.size()  ; i++) {
			
			
			if ( current_couleur == voitures.get(i).getColor() )
			{	nbr_voiture_caracterise++;
				if (nbr_voiture_caracterise > 25) {
					nbr_voiture_caracterise = 0 ;
					nbr_violation_contrainte++;
				}
				
			}
			else {
				current_couleur = voitures.get(i).getColor();
			}
				
			
			
		}
		
		return false;
		
	}
*/
	public int check_color(int nbr_max_a_respecter, int batch_paint) {
		
		int nbr_purge =0 ; 
		
		//definition couleur premiere voiture
		int current_couleur = voitures.get(0).getColor();
		int nbr_voiture_caracterise = 1 ; 
		
		
		for (int i = 0  ; i < voitures.size()  ; i++) {
			
			
			if ( current_couleur == voitures.get(i).getColor() )
			{	nbr_voiture_caracterise++;
				if (nbr_voiture_caracterise > nbr_max_a_respecter) {
					nbr_voiture_caracterise = 0 ;
					nbr_purge++;
				}
				
			}
			else {
				nbr_purge++;
				current_couleur = voitures.get(i).getColor();
			}
				
			
			
		}
		
		return nbr_purge;
		
	}
	
	
}

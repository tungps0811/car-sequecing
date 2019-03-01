package version2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;


public class Lecture_fichier {

	public static void main(String[] args) throws FileNotFoundException {
		
		
		// lire les fichiers ratios
		Vector<ratio> ratios = read_ratios("/home/bwah/Projet_industriel/test/ratios.txt");
		
		
		// lire les fichiers voitures
		Vector<voiture> voitures = read_voitures("/home/bwah/Projet_industriel/test/vehicles.txt");
		
		//lire fichier paintbatch
		int paint_batch = read_paint("/home/bwah/Projet_industriel/test/paint_batch_limit.txt");
		

		//lire fichier contraintes
		Vector<String> objectifs = read_contraintes("/home/bwah/Projet_industriel/test/optimization_objectives.txt");
		
		
	}
	
	
	
	
	
	public void checker(Vector<voiture> mesvoitures,Vector<String> contraintes) {
		HashMap<Integer,String> prio_valeur = new HashMap();
		prio_valeur =constraint_mode(contraintes);
		
		
	}
	
	public static Scanner lecture_fichier(String path) throws FileNotFoundException {
		File fichier = new File(path);
		System.out.println(fichier);
		Scanner scan = new Scanner(fichier);
		return scan;
	}
	
	public static Vector<ratio> read_ratios(String path) throws FileNotFoundException{

	Scanner scan = lecture_fichier(path);
	
	Vector<String> lignes = new Vector();
	Vector<ratio> ratios = new Vector();
	
	System.out.println(scan.nextLine());
	while (scan.hasNextLine()) {
		String nextLigne = scan.nextLine();
		lignes.add(nextLigne);
		
		String[] tab= nextLigne.split(";");

		ratios.add(new ratio(Integer.parseInt(tab[0].split("/")[0]),Integer.parseInt(tab[0].split("/")[1]),(tab[1]=="1"),tab[2]));
		
	
	}
		
		return ratios;
	}
	
	
	public static Vector<voiture> read_voitures(String path) throws FileNotFoundException{
	

		Scanner scan = lecture_fichier(path);
		
		System.out.println(scan.nextLine());
		System.out.println(scan.nextLine());
		Vector<String> lignes = new Vector();
		Vector<voiture> voitures = new Vector();
		
		Vector<Boolean> options = new Vector(); 
		
		while (scan.hasNextLine()) {
			String nextLigne = scan.nextLine();
			lignes.add(nextLigne);
			
			String[] tab= nextLigne.split(";");
			
			
			
			for (int i=5; i <tab.length;i++) {
				options.add((tab[i]=="1")); // conversion string to boolean
			}
			
			voitures.add(new voiture(Integer.parseInt(tab[1]),Integer.parseInt(tab[3]),options));
			
			
		}
		
		return voitures;
	}
	
	
	public static int read_paint(String path) throws FileNotFoundException {

		Scanner scan = lecture_fichier(path);
		
		System.out.println(scan.nextLine());
		int paint_limit = Integer.parseInt((scan.nextLine()).replaceAll(";",""));
		
		System.out.println(paint_limit);
		
		return paint_limit;
	}
	
	public static Vector<String> read_contraintes(String path) throws FileNotFoundException {

		Scanner scan = lecture_fichier(path);
		
		System.out.println(scan.nextLine());
	
		
		Vector<String> objectifs = new Vector<String>();
		while (scan.hasNextLine()) {
			
			String nextLigne = scan.nextLine();
			String[] tab= nextLigne.split(";");
			
			objectifs.add(tab[1]);
			
		}
		
		//System.out.println(objectifs.get(0).toString());
		
		
		HashMap<Integer,String> prio_valeur = new HashMap();
		prio_valeur =constraint_mode(objectifs);
		
		
		//System.out.println(prio_valeur);

		return objectifs;
	}
	
	
	public static HashMap<Integer,String> constraint_mode(Vector<String> contraintes) {
		HashMap<Integer,String> prio_valeur = new HashMap();
		int valeur[] = {10000,100,1} ; 
		int index=0;
		int nbr_prio=3;
		while (index < nbr_prio) {
			//System.out.println(contraintes.capacity());
			//System.out.println(index);
			prio_valeur.put(valeur[index],contraintes.get(index));
			index++;
		}
			
		return prio_valeur;
	}
	
	
}
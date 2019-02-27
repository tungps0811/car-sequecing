package version1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;


public class Lecture_fichier {

	public static void main(String[] args) throws FileNotFoundException {
		
		/*
		File repertoire=  new File("/home/bwah/eclipse-workspace/TD4/TD5/src/ex3");
		String [] listefichiers = repertoire.list() ;
		System.out.println(listefichiers[0]);
		System.out.println(listefichiers[1]);
		*/	
		
	
			
		File fichier = new File("/home/bwah/Projet_industriel/test/ratios.txt");
		System.out.println(fichier);
		Scanner scan = new Scanner(fichier);
		
		//System.out.println(scan.getClass());
		
		//System.out.println(scan.toString());
		
		//System.out.println(scan.nextLine());
		//System.out.println(scan.nextLine());
		
		Vector<String> lignes = new Vector();
		Vector<ratio> ratios = new Vector();
		
		System.out.println(scan.nextLine());
		while (scan.hasNextLine()) {
			String nextLigne = scan.nextLine();
			lignes.add(nextLigne);
			
			String[] tab= nextLigne.split(";");
			//System.out.print(tab[0]+" ");
			//System.out.print(tab[1]+" ");
			//System.out.println(tab[2]);
			
			//System.out.println(Integer.parseInt(tab[0].split("/")[0]));
			//System.out.println(tab[0].split("/")[1]);
			ratios.add(new ratio(Integer.parseInt(tab[0].split("/")[0]),Integer.parseInt(tab[0].split("/")[1]),(tab[1]=="1"),tab[2]));
			
		
			
		}
		
		
		//System.out.println(ratios.get(1).toString());

		
		File fichier2 = new File("/home/bwah/Projet_industriel/test/vehicles.txt");
		System.out.println(fichier2);
		Scanner scan2 = new Scanner(fichier2);
		
		System.out.println(scan2.nextLine());
		System.out.println(scan2.nextLine());
		Vector<String> lignes2 = new Vector();
		Vector<voiture> voitures = new Vector();
		
		Vector<Boolean> options = new Vector(); 
		
		while (scan2.hasNextLine()) {
			String nextLigne2 = scan2.nextLine();
			lignes2.add(nextLigne2);
			
			String[] tab= nextLigne2.split(";");
			
			
			
		for (int i=5; i <tab.length;i++) {
			options.add((tab[i]=="1")); // conversion string to boolean
		}
			
			voitures.add(new voiture(Integer.parseInt(tab[1]),Integer.parseInt(tab[3]),options));
			
			
		}
		
		
		
		File fichier3 = new File("/home/bwah/Projet_industriel/test/paint_batch_limit.txt");
		System.out.println(fichier3);
		Scanner scan3 = new Scanner(fichier3);
		
		System.out.println(scan3.nextLine());
		int paint_limit = Integer.parseInt((scan3.nextLine()).replaceAll(";",""));
		
		System.out.println(paint_limit);
		
		File fichier4 = new File("/home/bwah/Projet_industriel/test/optimization_objectives.txt");
		System.out.println(fichier4);
		Scanner scan4 = new Scanner(fichier4);
		
		System.out.println(scan4.nextLine());
	
		
		Vector<String> objectifs = new Vector<String>();
		while (scan4.hasNextLine()) {
			
			String nextLigne4 = scan4.nextLine();
			String[] tab= nextLigne4.split(";");
			
			objectifs.add(tab[1]);
			//System.out.println(scan4.nextLine());
		}
		
		System.out.println(objectifs.get(0).toString());
		
		

	}
}
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
		
		System.out.println(scan.getClass());
		
		System.out.println(scan.toString());
		
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
			
			//System.out.println(tab[3]);
			
			
		}
		
		
		System.out.println(ratios.get(1).toString());

		
	

	}
}
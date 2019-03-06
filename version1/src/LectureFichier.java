
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class LectureFichier {
	
	private String ratiosFilePath;
	private String vehiculesFilePath;
	private String painColorFilePath; 
	private String readObjectiveFilePath;
	
	public LectureFichier(String ratiosFilePath, String vehiculesFilePath, String painColorFilePath, String readObjectiveFilePath) {
		this.vehiculesFilePath = vehiculesFilePath;
		this.ratiosFilePath = ratiosFilePath;
		this.painColorFilePath = painColorFilePath;
		this.readObjectiveFilePath = readObjectiveFilePath;
	}
	
	public Vector<Option> readOptionFile() throws FileNotFoundException {

		File fichier = new File(ratiosFilePath);
		Scanner scanOption = new Scanner(fichier);
		System.out.println(scanOption.getClass());
		
		Vector<Option> listOption = new Vector<Option>();
	
			 scanOption.nextLine();
			 
			 while (scanOption.hasNext()) {
				 try {
					 String nextLigne = scanOption.nextLine();
					 String[] tab= nextLigne.split(";");
					 Option optionEnLigne = new Option(Integer.parseInt(tab[0].split("/")[0]),Integer.parseInt(tab[0].split("/")[1]),(tab[1].equals("1")),tab[2]);
					 listOption.add(optionEnLigne);
				 }catch(NumberFormatException ex){ 
					 System.out.println("le type ne peut pas convertir en entier");
				 }
			 }	 
			 for (Option option: listOption) {
				 System.out.println( option);
			 }
			 
			 scanOption.close();	  
		 return listOption;
	}
	
	public Vector<Voiture> readVoitureFile() throws FileNotFoundException {
		
		File fichier = new File(vehiculesFilePath);
		Scanner scanOption = new Scanner(fichier);
		System.out.println(scanOption.getClass());
		Vector<Voiture> listVoitures = new Vector<Voiture>();
		
		// Process first line
		String[] labels =  processFirstLine(scanOption.nextLine());
		
		while (scanOption.hasNext()) {
			 try {
				 String nextLigne = scanOption.nextLine();
				 String[] tab = nextLigne.split(";");
				 HashMap<String, Boolean> mapOption = new HashMap<String, Boolean>();
				 for (int index = 4; index < tab.length; index++) {
					 mapOption.put(labels[index], readOptionValue(tab[index]));
				 }
				 Voiture voiture = new Voiture(tab[0], Integer.parseInt(tab[1]), tab[2], Integer.parseInt(tab[3]), mapOption);
				 listVoitures.add(voiture);
				 
			 } catch(NumberFormatException ex){ 
				 System.out.println("le type ne peut pas convertir en entier");
			 }
		 }	 
		 
		 scanOption.close();	  
	 return listVoitures;

	}
	
	public String[] processFirstLine(String firstLine) {
		String[] tab = firstLine.split(";");
		return tab;
	}
	
	public Boolean readOptionValue(String string) {
		return string.equals("1");
	}
	
	public int readPainColorFile() throws FileNotFoundException{
		File fichier = new File(painColorFilePath);
		Scanner scanPainColor = new Scanner(fichier);
		scanPainColor.nextLine();
		String[] tab = scanPainColor.nextLine().split(";");
		int res = Integer.parseInt(tab[0]);
		scanPainColor.close();
		return res;
	}
	
	public Vector<String> readObjectiveFile() throws FileNotFoundException{
		File fichier = new File(readObjectiveFilePath);
		Scanner scanObjective = new Scanner(fichier);
		scanObjective.nextLine();
		Vector<String> objectives = new Vector<String>();
		while (scanObjective.hasNextLine()) {
			
			String nextLigne = scanObjective.nextLine();
			String[] tab= nextLigne.split(";");
			objectives.add(tab[1]);
		}
		scanObjective.close();
		for (String obj: objectives) {
			 System.out.println( obj);
		}	 
		return objectives;
		
	}
}	
import java.io.FileNotFoundException;

public class MainCarSequencing {

	public static void main(String[] args) throws FileNotFoundException {

		String ratiosFilePath = "../../Instances_set_A/022_3_4_EP_RAF_ENP/ratios.txt";
		String vehiculesFilePath = "../../Instances_set_A/022_3_4_EP_RAF_ENP/vehicles.txt";
		String painColorFilePath = "../../Instances_set_A/022_3_4_EP_RAF_ENP/paint_batch_limit.txt";

		LectureFichier fileReader = new LectureFichier(ratiosFilePath, vehiculesFilePath,painColorFilePath);
		//CarSequencing carSequence = new CarSequencing(fileReader.readOptionFile(), fileReader.readVoitureFile(),fileReader.readPainColorFile());
		CarSequencing carSequence = new CarSequencing(fileReader.readOptionFile(), fileReader.readVoitureFile(),fileReader.readPainColorFile());
		carSequence.MapClassVoiture();
		System.out.println("nombre de voiture in classe 3 est " + carSequence.nbrVoitureInClass1(3));
		System.out.println("la voiture est dans la class " + carSequence.numClassOfVoiture(carSequence.getListVoitures().get(2)).getNumClass() + " " + carSequence.numClassOfVoiture(carSequence.getListVoitures().get(2)).toString());
		System.out.println("nbr voiture jour J -1 est " + carSequence.nbrVoitureDateMoins());
		System.out.println("max fenetre est " + carSequence.maxFenetre());
		System.out.println("min fenetre est " + carSequence.minFenetre());
		System.out.println("color max possible est " + carSequence.getColorMax() );
		System.out.println("nbr violations des contraints priorite est " + carSequence.totalPenalitePriori());
		Option option1 = carSequence.getListOptions().get(1);
		System.out.println("nbr violations des sur " + option1.nomOption + " " + carSequence.totalPenaliteOption(option1));
		Option option2 = carSequence.getListOptions().get(5);
		System.out.println("nbr violations des sur " + option2.nomOption + " " + carSequence.totalPenaliteOption(option2));
	} 

}

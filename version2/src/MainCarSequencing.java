import java.io.FileNotFoundException;

public class MainCarSequencing {

	public static void main(String[] args) throws FileNotFoundException {

		String ratiosFilePath = "../../Instances_set_A/064_38_2_EP_RAF_ENP_ch2/ratios.txt";
		String vehiculesFilePath = "../../Instances_set_A/064_38_2_EP_RAF_ENP_ch2/vehicles.txt";
		String painColorFilePath = "../../Instances_set_A/064_38_2_EP_RAF_ENP_ch2/paint_batch_limit.txt";
		String readObjectiveFilePath = "../../Instances_set_A/064_38_2_EP_RAF_ENP_ch2/optimization_objectives.txt";

		LectureFichier fileReader = new LectureFichier(ratiosFilePath, vehiculesFilePath,painColorFilePath,readObjectiveFilePath);
		//CarSequencing carSequence = new CarSequencing(fileReader.readOptionFile(), fileReader.readVoitureFile(),fileReader.readPainColorFile());
		CarSequencing carSequence = new CarSequencing(fileReader.readOptionFile(), fileReader.readVoitureFile(),fileReader.readPainColorFile(), fileReader.readObjectiveFile());
		//carSequence.MapClassVoiture();
//		System.out.println("nombre de voiture in classe 3 est " + carSequence.nbrVoitureInClass1(3));
//		System.out.println("la voiture est dans la class " + carSequence.numClassOfVoiture(carSequence.getListVoitures().get(2)).getNumClass() + " " + carSequence.numClassOfVoiture(carSequence.getListVoitures().get(2)).toString());
		System.out.println("nbr voiture jour J-1 est " + carSequence.nbrVoitureDateMoins());
		System.out.println("max fenetre est " + carSequence.maxFenetre());
		System.out.println("min fenetre est " + carSequence.minFenetre());
		System.out.println("color max possible est " + carSequence.getColorMax() );
		System.out.println("nbr Constrains Prio est " + carSequence.nbrContraintsPrio());
		System.out.println("nbr Constrains Non Prio est " + carSequence.nbrContraintsNonPrio());
		System.out.println("nbr violations des contraints priorite est " + carSequence.totalPenalitePriori());
		System.out.println("nbr violations des contraints non priorite est " + carSequence.totalPenaliteNonPriori());
		System.out.println("nbr violations des contraints couleur est " + carSequence.penaliteCouleur());
		
	} 

}

import java.io.IOException;
import java.util.Random;

public class Test {

	public static void main(String[] args) throws IOException {
		
		String ratiosFilePath = "../../Instances_set_A/064_38_2_EP_RAF_ENP_ch2_TEST/ratios.txt";
		String vehiculesFilePath = "../../Instances_set_A/064_38_2_EP_RAF_ENP_ch2_TEST/vehicles.txt";
		String painColorFilePath = "../../Instances_set_A/064_38_2_EP_RAF_ENP_ch2_TEST/paint_batch_limit.txt";
		String readObjectiveFilePath = "../../Instances_set_A/064_38_2_EP_RAF_ENP_ch2_TEST/optimization_objectives.txt";

		// read file premier fois
		LectureFichier fileReader = new LectureFichier(ratiosFilePath, vehiculesFilePath, painColorFilePath,
				readObjectiveFilePath);
		Ordonnancement ordre = new Ordonnancement(fileReader.readOptionFile(), fileReader.readVoitureFile(),
				fileReader.readPainColorFile(), fileReader.readObjectiveFile());

		System.out.println("BEFORE");
		int cout1 = ordre.totalPenalite();
		System.out.println("Best cout : " + cout1);
		
		Optimisation optimisation = new Optimisation();
		// tirage des nombres aleatoire
		int min = ordre.nbrVoitureJourAvant();
		int max = ordre.getListVoitures().size() - 1;
		
		Random random = new Random();
		int pos1 = min + random.nextInt(max - min);
		int pos2 = min + random.nextInt(max - min);
		System.out.println(pos1 + " - " + pos2);

		int deltaEnvisager = 10000 * optimisation.envigagerPriorite(pos1, pos2, ordre)
				+ 100 * optimisation.envigagerNonPriorite(pos1, pos2, ordre) + optimisation.engagerCouleur(pos1, pos2, ordre);
		System.out.println("Delta envigager = " + deltaEnvisager);
		System.out.println("Couleur envigager = " + optimisation.engagerCouleur(pos1, pos2, ordre));

		optimisation.changement(pos1, pos2, ordre);
		
		LectureFichier fileReader2 = new LectureFichier(ratiosFilePath, "vehicles-ver2.txt", painColorFilePath,
				readObjectiveFilePath);
		Ordonnancement ordre2 = new Ordonnancement(fileReader2.readOptionFile(), fileReader2.readVoitureFile(),
				fileReader2.readPainColorFile(), fileReader2.readObjectiveFile());
		
		int cout2 = ordre2.totalPenalite();
		System.out.println("New cout : " + cout2);
		int deltaCout = cout2 - cout1;
		System.out.println("Delta cout = " + deltaCout);
		
	}

}

import java.io.IOException;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws IOException {

		for (;;) {
			String ratiosFilePath = "../../Instances_set_A/048_39_1_EP_ENP_RAF-TEST/ratios.txt";
			String vehiculesFilePath = "vehicles.txt";
			String painColorFilePath = "../../Instances_set_A/048_39_1_EP_ENP_RAF-TEST/paint_batch_limit.txt";
			String readObjectiveFilePath = "../../Instances_set_A/048_39_1_EP_ENP_RAF-TEST/optimization_objectives.txt";

			//read file premier fois
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
				int pos1 = min + random.nextInt(max-min);
				int pos2 = min + random.nextInt(max-min);
				
				// il faut mettre devant touttttttttttttttttttt
				optimisation.changement(pos1, pos2, ordre);
				
				
				//read file premier fois
				LectureFichier fileReader2 = new LectureFichier(ratiosFilePath, "vehicles-ver2.txt", painColorFilePath,
						readObjectiveFilePath);
				Ordonnancement ordre2 = new Ordonnancement(fileReader2.readOptionFile(), fileReader2.readVoitureFile(),
						fileReader2.readPainColorFile(), fileReader2.readObjectiveFile());
				// changement ou envisager *** 
				
				int delta = optimisation.delta(pos1, pos2, ordre, ordre2);
				
				System.out.println("Delta = " + delta);
				// calcul le cout
				int cout2 = ordre2.totalPenalite();
				System.out.println("Old cout " + cout1 + " - New cout = " + cout2);
				
				if (cout2 < cout1) {
					System.out.println("New record : " + cout2);
					EcritureFichier writer = new EcritureFichier(ordre2);
					writer.write("vehicles.txt");
					break;
				}
				
			
		}
					
	}
}

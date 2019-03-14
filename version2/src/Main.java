import java.io.IOException;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws IOException {

		for (;;) {
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
					+ 100 * optimisation.envigagerNonPriorite(pos1, pos2, ordre);
			System.out.println("Delta envigager = " + deltaEnvisager);

			if (deltaEnvisager < 0) {
				System.out.println("New record found : " + ordre.totalPenalite());
				optimisation.changement(pos1, pos2, ordre);
				EcritureFichier writer = new EcritureFichier(ordre);
				writer.write(vehiculesFilePath);
			}
		}
	}
}

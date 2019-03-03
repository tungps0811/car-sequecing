import java.io.FileNotFoundException;

public class MainCarSequencing {

	public static void main(String[] args) throws FileNotFoundException {
		String ratiosFilePath = "../Instances_set_A/022_3_4_EP_RAF_ENP/ratios.txt";
		String vehiculesFilePath = "../Instances_set_A/022_3_4_EP_RAF_ENP/vehicles.txt";
		LectureFichier fileReader = new LectureFichier(ratiosFilePath, vehiculesFilePath);
		
		CarSequencing carSequence = new CarSequencing(fileReader.readOptionFile(), fileReader.readVoitureFile());
		System.out.println(carSequence.listVoitures.get(1).toString());
		System.out.println(carSequence.toString());

	}

}

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EcritureFichier {
	private CarSequencing carSequence;
	public EcritureFichier(CarSequencing carSequence) {
		this.carSequence = carSequence;
	}
	
//	public void write() throws IOException {	
//		String header = "Date;SeqRank;Ident;Paint Color"; //;HPRC1;HPRC2;HPRC3;LPRC1;LPRC2;LPRC3;LPRC4;LPRC5;LPRC6";
//			for (int i = 1; i <= carSequence.nbrContraintsPrio(); i ++) {
//				header += ";HPRC" + i;
//			}
//			for (int i = 1; i <= carSequence.nbrContraintsNonPrio(); i ++) {
//				header += ";LPRC" + i;
//			}
//			
//			BufferedWriter writer = new BufferedWriter(new FileWriter("vehicles.txt"));
//			writer.write(header + "\r\n" + carSequence.prepareToWrite());
//			
//			writer.close();					
//	}

	public void write() throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter("solution.txt"));
		writer.write(carSequence.prepareToWrite2());
		writer.close();
	}

}

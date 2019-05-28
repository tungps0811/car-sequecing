import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EcritureFichier {
	private Ordonnancement ordonnancement;
	public EcritureFichier(Ordonnancement ordonnancement) {
		this.ordonnancement = ordonnancement;
	}
	// c:/temp/CHECKERS/WINDOWS/Candidate-base-A/Instances/048_39_1_EP_ENP_RAF_TUNG/
	public void write(String path) throws IOException {			
		String header = "Date;SeqRank;Ident;Paint Color";
		
			for (int i = 1; i <= ordonnancement.nbrContraintsPrio(); i ++) {
				header += ";HPRC" + i;
			}
			for (int i = 1; i <= ordonnancement.nbrContraintsNonPrio(); i ++) {
				header += ";LPRC" + i;
			}
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			writer.write(header + "\r\n" + ordonnancement.prepareToWrite());
			
			writer.close();					
	}
}

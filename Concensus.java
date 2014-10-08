import java.util.LinkedList;


public class Concensus {

	private static LinkedList<String[]> sequencesAlignes;
	private double seuil;
	private String[] z;

	public Concensus(String adresseFichierFasta){
		
		String sequencesFasta = ouvrirFichier(adresseFichierFasta);
		int nombreDeSequnces = calculerNombreDeSequences(sequencesFasta);
		
		//Question 1.1
		String alignementMultiple = executerClustal(adresseFichierFasta);
		sequencesAlignes = getSequencesAlignes();
		LinkedList<String[]> matriceConcensus = getMatriceconcensus(sequencesAlignes);
		
		
		z = getZ(matriceConcensus);
		
		//Question 1.3
		LinkedList<Integer> pourcentageIdentite = getPourcentageIdentite(sequencesAlignes, z);

		//Question 1.4
		seuil = getSeuil(sequencesAlignes, z);
		
		int sequencesFamille[] = getIndexFamille(sequencesAlignes, z , seuil); 
		
		LinkedList<String[]> intrus = getIntrus(sequencesFamille);
		LinkedList<String[]> famille = getFamille(sequencesFamille);
	}
	
	private LinkedList<String[]> getFamille(int[] sequencesFamille) {
		// TODO Auto-generated method stub
		return null;
	}

	private double getSeuil(LinkedList<String[]> sequencesAlignes, String[] z) {
		// TODO Auto-generated method stub
		return 0;
	}

	private LinkedList<Integer> getPourcentageIdentite(
			LinkedList<String[]> sequencesAlignes, String[] z) {
		// TODO Auto-generated method stub
		return null;
	}

	private LinkedList<String[]> getIntrus(int[] sequencesFamille) {
		// TODO Auto-generated method stub
		return null;
	}

	private int[] getIndexFamille(LinkedList<String[]> sequencesAlignes,
			String[] z, double seuil) {
		// TODO Auto-generated method stub
		return null;
	}

	private String[] getZ(LinkedList<String[]> matriceConcensus) {
		// TODO Auto-generated method stub
		return null;
	}

	private LinkedList<String[]> getMatriceconcensus(LinkedList<String[]> sequencesAlignes) {
		// TODO Auto-generated method stub
		return null;
	}

	private LinkedList<String[]> getSequencesAlignes() {
		// TODO Auto-generated method stub
		return null;
	}

	private String executerClustal(String sequencesFasta) {
		// TODO Auto-generated method stub
		return null;
	}

	private int calculerNombreDeSequences(String sequencesFasta) {
		// TODO Auto-generated method stub
		return 0;
	}

	private String ouvrirFichier(String adresseFichierFasta) {
		// TODO Auto-generated method stub
		return null;
	}

	public void afficherZ(){
	}
	
	private  LinkedList<String[]> getFamilleSeuilX(double seuil) {
		int sequencesFamille[] = getIndexFamille(sequencesAlignes, z , seuil); 
		return getFamille(sequencesFamille);
	}

	private double getSeuil() {
		// TODO Auto-generated method stub
		return seuil;
	}
	
	public static void main(String[] args) {

		Concensus concensus = new Concensus("");
		concensus.afficherZ();
		
		double seuil = concensus.getSeuil();
		
		//Question 5
		LinkedList<String[]> fammilleSeuilX = concensus.getFamilleSeuilX(3*seuil);
	}
}

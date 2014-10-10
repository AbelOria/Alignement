package Concensus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

public class AlignementMultiple {

	private static LinkedList<String[]> sequencesAlignes;
	private double[] pourcentageIdentite;
	private double seuil;
	private String[] Z;
	private LinkedList<String[]> intrus;
	private LinkedList<String[]> famille;

	public AlignementMultiple() {
	}

	public AlignementMultiple(int tailleAlphabet) {

		// Question 1.4
		seuil = etablirSeuil(tailleAlphabet);
	}

	private String executerClustal(String adresseClustal, String adresseFichier) {

		String argus[] = { adresseClustal, "-i", adresseFichier };
		String sequenceAligne = "";

		Runtime runtime = Runtime.getRuntime();

		try {
			final java.lang.Process process = runtime.exec(argus);

			try {
				process.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = "";
			try {
				while ((line = reader.readLine()) != null) {

					sequenceAligne += line + "\n";
				}
			} finally {
				reader.close();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return sequenceAligne;
	}

	private static LinkedList<String[]> getSequencesAlignes(
			String sequencesFasta) {

		LinkedList<String[]> ajout = new LinkedList<String[]>();
		int j = -1;
		// int k = 0;
		boolean copy = false;
		String sequence = "";
		for (int i = 0; i < sequencesFasta.length(); i++) {

			if (sequencesFasta.charAt(i) == '>') {
				if (j >= 0)
					ajout.add(j, sequence.split(" "));
				sequence = "";
				copy = false;
				j++;
			}

			if (copy && sequencesFasta.charAt(i) != '\n') {
				sequence = sequence + sequencesFasta.charAt(i) + " ";
			}

			if (sequencesFasta.charAt(i) == '\n') {
				copy = true;

			}
			if (i == sequencesFasta.length() - 1)
				ajout.add(j, sequence.split(" "));

		}

		return ajout;
	}

	protected String[] calculerZ(LinkedList<String[]> sequencesAlignes) {

		int taille = sequencesAlignes.get(0).length;
		String z[] = new String[taille];
		for (int i = 0; i < taille; i++) {
			HashMap<String, Integer> frequence = new HashMap<String, Integer>();

			// Trouver frequence des symboles
			for (String[] sequenceAligne : sequencesAlignes) {
				if (frequence.containsKey(sequenceAligne[i])) {
					int f = frequence.get(sequenceAligne[i]);
					frequence.replace(sequenceAligne[i], f + 1);
				} else {
					frequence.put(sequenceAligne[i], 1);
				}
			}

			// Recuperer le symbole avec la frequence la plus grande
			int max = -1;
			String symboleConcensus = "";
			for (String mapKey : frequence.keySet()) {
				if (frequence.get(mapKey) > max) {
					max = frequence.get(mapKey);
					symboleConcensus = mapKey;
				} else if (frequence.get(mapKey) == max) {
					symboleConcensus = "?";
				}
			}

			z[i] = symboleConcensus;
		}
		return z;
	}

	protected double[] getPourcentageIdentite(
			LinkedList<String[]> sequencesAlignes, String[] z) {
		int taille = z.length;

		double[] pourcentages = new double[sequencesAlignes.size()];

		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < sequencesAlignes.size(); j++) {
				if (z[i] == sequencesAlignes.get(j)[i]) {
					pourcentages[j]++;
				}
			}
		}

		for (int i = 0; i < pourcentages.length; i++) {
			pourcentages[i] /= taille;
		}

		return pourcentages;
	}

	private double etablirSeuil(int tailleAlphabet) {
		return 1.0 / tailleAlphabet;
	}

	protected int[] getIndexFamille(LinkedList<String[]> sequencesAlignes,
			String[] z) {

		String famille = "";

		boolean familleTrouve = false;
		for (int i = 0; i < sequencesAlignes.size(); i++) {

			boolean isFamille = evaluerFamille(sequencesAlignes.get(i), z);
			if (isFamille) {
				famille += i + " ";
				familleTrouve = true;
			}
		}
		String familleSplit[] = famille.split(" ");
		int familleInt[] = new int[familleSplit.length];
		for (int i = 0; i < familleSplit.length; i++) {
			familleInt[i] = Integer.parseInt(familleSplit[i]);
		}
		if (familleTrouve)
			return familleInt;

		return null;
	}

	private boolean evaluerFamille(String[] Sx, String[] z) {
		int matchs = 0;
		int symbolesEvalues = 0;
		for (int i = 0; i < z.length; i++) {
			if (!Sx[i].equals("-") && !Sx[i].equals("?") && !z[i].equals("-")
					&& !z[i].equals("?")) {
				if (Sx[i].equals(z[i])) {
					matchs++;
				}
				symbolesEvalues++;
			}
		}
		// for(int i = 0 ; i < Sx.length ; i++){ System.out.print(Sx[i]);};
		return ((double) matchs / symbolesEvalues) > seuil;
	}

	/**
	 * 
	 * @param indexFamille
	 *            Liste de index de "sequencesAlignes" qui font partie d'une
	 *            famille
	 * @return Sequences appartenant a une famille
	 */
	private LinkedList<String[]> getFamille(int[] indexFamille) {
		LinkedList<String[]> famille = new LinkedList<String[]>();
		for (int i = 0; i < indexFamille.length; i++)
			famille.add(sequencesAlignes.get(indexFamille[i]));
		return famille;
	}

	protected LinkedList<String[]> getIntrus(
			LinkedList<String[]> sequencesAlignes, int[] indexFamille) {

		LinkedList<String[]> intrus = new LinkedList<String[]>();

		for (int i = 0; i < sequencesAlignes.size(); i++) {
			boolean intru = true;

			for (int j = 0; j < indexFamille.length; j++) {
				if (indexFamille[j] == i) {
					intru = false;
				}
			}

			if (intru) {
				intrus.add(sequencesAlignes.get(i));
			}
		}

		return intrus;
	}

	public void aligner(String adresseFichierFasta, String adresseFichier) {

		// Question 1.1
		String alignementMultiple = executerClustal(adresseFichierFasta,
				adresseFichier);
		sequencesAlignes = getSequencesAlignes(alignementMultiple);

		// Question 1.2
		Z = calculerZ(sequencesAlignes);

		// Question 1.3
		pourcentageIdentite = getPourcentageIdentite(sequencesAlignes, Z);

		int indexFamille[] = getIndexFamille(sequencesAlignes, Z);

		intrus = getIntrus(sequencesAlignes, indexFamille);
		famille = getFamille(indexFamille);
	}

	public String getZ() {
		String z = "";
		for (int i = 0; i < Z.length; i++) {
			z += Z[i];
		}
		return z;
	}

	public double getSeuil() {
		return seuil;
	}

	public void setSeuil(double nouveauSeuil) {
		this.seuil = nouveauSeuil;
	}

	public LinkedList<String[]> getFamille() {
		return famille;
	}

	// EXECUTION DU LOGICIEL
	// =========================================================================
	public static void main(String[] args) {

		AlignementMultiple concensus = new AlignementMultiple(20);
		// concensus.getZ();

		// double seuil = concensus.getSeuil();

		// Question 5
		String adresseClustal = "/u/oriaecha/ift3295/workspace/alignement"
				+ "/src/alignement/clustalo-1.2.0-Ubuntu-32-bit";

		String adresseFichier = "/u/oriaecha/ift3295/workspace/alignement"
				+ "/src/alignement/inconnus.fa";

		concensus.aligner(adresseClustal, adresseFichier);
		System.out.println("famille: " + concensus.getFamille().size());
		System.out.println(concensus.getZ());

		concensus.setSeuil(concensus.getSeuil() * 5);

		concensus.aligner(adresseClustal, adresseFichier);
		System.out.println("famille: " + concensus.getFamille().size());
		System.out.println(concensus.getZ());

	}
}

package Concensus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Process {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Code pour DOS
/*		String adresse = "C:\\Windows\\System32\\cmd.exe";
		String argus[] = { adresse, "/C", "dir   C:\\abel\\fichier.txt" };

		Runtime runtime = Runtime.getRuntime();

		try {
			final java.lang.Process process = runtime.exec(argus);
			process.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = "";
			try {
				while ((line = reader.readLine()) != null) {
					// Traitement du flux de sortie de l'application si besoin
					// est
					System.out.println(line);
				}

			} finally {
				reader.close();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
*/
		
		/* Code pour Clustal */
		String adresse = "C:\\Users\\user\\Documents\\Clustal Omega\\clustal-omega-1.2.0-win32\\clustalo.exe";
		String argus[] = { adresse, "-i", "inconnus.fa" };

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
					// Traitement du flux de sortie de l'application si besoin
					// est
					System.out.println(line);
				}

			} finally {
				reader.close();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		//Code pour notepad
/*		Runtime runtime = Runtime.getRuntime();
		try {
			// java.lang.Process p =
			runtime.exec("C:\\Windows\\System32\\notepad.exe");
			String ligneCmd[] = { "dir" };
			String str = "dir";
			System.out.println("Creating Process...");
			java.lang.Process p = runtime.exec(new String[] {
					"C:\\Windows\\System32\\cmd.exe", "/c", str });

			InputStream in = p.getInputStream();
			System.out.println(str);

			for (int i = 0; i < in.available(); i++) {
				System.out.println("o" + in.read());
			}

			try {
				// cause this process to stop until process p is terminated
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// when you manually close notepad.exe program will continue here
			System.out.println("Waiting over.");

		} catch (IOException e) {
			e.printStackTrace();
		}
*/

	}
}

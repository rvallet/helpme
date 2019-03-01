package fr.dawan.mvc1.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import fr.dawan.mvc1.beans.User;

/**
 * Classe utilitaire
 * 
 * @author Thomas stagiaire
 *
 */
public class Tools {

	// méthode qui exporte en csv une liste d'utilisateurs
	// visibilité mots-clé typeRetour nomMethode(paramètres)
	/**
	 * 
	 * @param Chemin  complet
	 * @param List    récupérer depuis le fichier
	 * @param colonne a selectionné
	 * @param manière de séparer les colonnes en csv
	 * @throws Exception
	 */
	public static <T> void toCsv(String filePath, List<T> myList, List<String> columns, String separator)
			throws Exception {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
			if (myList != null && myList.size() > 0) {
				Field[] tab = myList.get(0).getClass().getDeclaredFields();
				StringBuilder ligneEntete = new StringBuilder();
				for (Field f : tab) {
					f.setAccessible(true);
					if (columns.contains(f.getName()))
						ligneEntete.append(f.getName()).append(separator);
				}
				bw.write(ligneEntete.toString().substring(0, ligneEntete.length() - 1));
				bw.newLine();
				for (T obj : myList) {
					StringBuilder line = new StringBuilder();
					for (Field f : tab) {
						if (columns.contains(f.getName()))
							line.append(f.get(obj).toString()).append(separator);
					}
					bw.write(line.toString().substring(0, line.length() - 1));
					bw.newLine();
				}
			}
		}
	}

	public static List<User> importCsv(String filePath) throws Exception {
		List<User> liU = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			// En tête
			br.readLine();
			String ligne = null;
			while ((ligne = br.readLine()) != null) {
				if (!ligne.trim().isEmpty()) {
					String[] ligneS = ligne.split(";");
					if (ligneS.length == 3) {
						try {
							User u = new User();
							u.setPseudo(ligneS[0]);
							u.setEmail(ligneS[1]);
							liU.add(u);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}
			}
		}
		return liU;
	}
}

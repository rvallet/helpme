package fr.dawan.mvc1.tools;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import fr.dawan.mvc1.beans.Objective;
import fr.dawan.mvc1.beans.Objective.ObjectiveStatus;
import fr.dawan.mvc1.beans.Problem;
import fr.dawan.mvc1.beans.Problem.ProblemFrequence;
import fr.dawan.mvc1.beans.Problem.ProblemObjective;

public class Calculation {

// ************************************méthode stop tabac*********************************************************	

	// méthode de calcul du temps d'abstinence pour le récupérer en secondes
	public static long absTimeSec(Problem p) {
//		Date dN = new Date();
//		Date dR = new Date(dN.getTime() - d.getTime());
//		return dR.toString();
		LocalDateTime now = LocalDateTime.now();
		System.out.println(p.getCreationDate());
		Date d = new Date(p.getCreationDate().getTime());
		System.out.println(d);
		LocalDateTime start = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		Duration abstinence = Duration.between(start, now);
		long totalSec = abstinence.getSeconds();
		return totalSec;
	}

	// méthode utilitaire pour calculer la période de temps en secondes pour
	// ProblemFrequence
	public static long freqToSec(Problem p) {
		if (p.getProblemFrequence().equals(ProblemFrequence.DAILY))
			return 86400;
		else if (p.getProblemFrequence().equals(ProblemFrequence.WEEKLY))
			return 604800;
		else if (p.getProblemFrequence().equals(ProblemFrequence.MONTHLY))
			return 2_592_000;
		else
			return 0;
	}

	// méthode pour calculer le nombre d'unité non consommée
	public static int notConsume(Problem p) {
		double quantityPerPeriod = p.getStartQuantity();
		long absTimeSec = absTimeSec(p);
		long periodSec = freqToSec(p);
		double notConsume = 0;
		notConsume = (quantityPerPeriod * absTimeSec) / periodSec;
		if (!p.getLiObjectives().isEmpty()) {
			for (Objective o : p.getLiObjectives()) {
				if (o.getStatus().equals(ObjectiveStatus.COMPLETED))
					notConsume += o.getQuantityNotSmoke();
			}
		}
		return (int) Math.round(notConsume);
	}

	// méthode pour calculer l'argent économisé en fontion du temps d'abstinence
	public static double moneySave(Problem p) {
		double quantityPerPeriod = p.getStartQuantity();
		double unitCost = p.getUnitCost();
		long absTimeSec = absTimeSec(p);
		long periodSec = freqToSec(p);
		double resultSave = 0;

		resultSave = (quantityPerPeriod * unitCost * absTimeSec) / periodSec;

		if (!p.getLiObjectives().isEmpty()) {
			for (Objective o : p.getLiObjectives()) {
				if (o.getStatus().equals(Objective.ObjectiveStatus.COMPLETED)) {
					resultSave += o.getMoneySave();
				}
			}
		}
		return Math.round(resultSave);
	}

	// méthode pour calculer le temps économisé en fontion des cigarettes non fumées
	public static long timeSave(Problem p) {
		long timeSave = 0;
		if (p.getObjective().equals(ProblemObjective.TO_STOP)) {
			timeSave = notConsume(p) * 300; // temps en sec pour fumer une cigarette
		} else {
			// TODO: terminer la méthode dans le cas du ProblemObjective.TO_DECREASE
		}
		return timeSave;
	}

//   méthode utilitaire pour mettre en forme un temps		
	public static String timeFormat(long totalSec) {
		String compteur = "";
		// Si le compteur est à plus d'un jour
		if (totalSec > 86400) {
			long days = Math.round(totalSec / 86400);
			long hours = Math.round((totalSec - days * 86400) / 3600);
			long min = Math.round((totalSec - days * 86400 - hours * 3600) / 60);
			long sec = Math.round(totalSec - days * 86400 - hours * 3600 - min * 60);
			compteur = days + "j " + hours + "h " + min + "min" + sec + "s";
			// Si le compteur est à plus d'une heure
		} else if (totalSec > 3600) {
			long hours = Math.round(totalSec / 3600);
			long min = Math.round((totalSec - hours * 3600) / 60);
			long sec = Math.round(totalSec - hours * 3600 - min * 60);
			compteur = hours + "h " + min + "min " + sec + "s";
			// Si le compteur est à plus d'une minute
		} else if (totalSec > 60) {
			long min = Math.round(totalSec / 60);
			long sec = totalSec - min * 60;
			compteur = min + "min " + sec + "s";
		} else {
			long sec = totalSec;
			compteur = sec + "sec";
		}
		return compteur;
	}

	public static LinkedHashMap<String, Double> heightNotSmokeGeek(Problem p) {
		double notSmokeCig = 0;
		if (p.getObjective().equals(ProblemObjective.TO_STOP)) {
			notSmokeCig = notConsume(p);
		} else {
			Objective o = p.getLiObjectives().stream()
					.filter(x -> x.getStatus().equals(Objective.ObjectiveStatus.IN_PROGRESS)).findFirst().orElse(null);
			notSmokeCig = o.getQuantityNotSmoke();
		}
		double cigH = 0.7;
		int pika = 6, frodon = 16, chewbacca = 33, tardis = 44, trex = 71, basilic = 214, millenium = 496,
				baradDur = 20000;
		double pikaH = 0.4, frodonH = 1.10, chewbaccaH = 2.28, tardisH = 3.05, trexH = 5, basilicH = 15,
				milleniumH = 34.75, bardDurH = 1400;

		double pikaResult = notSmokeCig * 100 / pika;
		if (pikaResult > 100)
			pikaResult = 100;
		double frodonResult = notSmokeCig * 100 / frodon;
		if (frodonResult > 100)
			frodonResult = 100;
		double chewbaccaResult = notSmokeCig * 100 / chewbacca;
		if (chewbaccaResult > 100)
			chewbaccaResult = 100;
		double tardisResult = notSmokeCig * 100 / tardis;
		if (tardisResult > 100)
			tardisResult = 100;
		double trexResult = notSmokeCig * 100 / trex;
		if (trexResult > 100)
			trexResult = 100;
		double basilicResult = notSmokeCig * 100 / basilic;
		if (basilicResult > 100)
			basilicResult = 100;
		double milleniumResult = notSmokeCig * 100 / millenium;
		if (milleniumResult > 100)
			milleniumResult = 100;
		double baradDurResult = notSmokeCig * 100 / baradDur;
		if (baradDurResult > 100)
			baradDurResult = 100;

		LinkedHashMap<String, Double> result = new LinkedHashMap<String, Double>();
		result.put("Pikachu", (double) Math.round(pikaResult));
		result.put("Frodon", (double) Math.round(frodonResult));
		result.put("Chewbacca", (double) Math.round(chewbaccaResult));
		result.put("Tardis", (double) Math.round(tardisResult));
		result.put("T-Rex", (double) Math.round(trexResult));
		result.put("Basilic", (double) Math.round(basilicResult));
		result.put("Millenium", (double) Math.round(milleniumResult));
		result.put("Barad-Dur", (double) Math.round(baradDurResult));

		return result;
	}

	public static LinkedHashMap<String, Double> whatToBuyWithMyMoney(Problem p) {
		double amountMoneySave = 0;
		if (p.getObjective().equals(ProblemObjective.TO_STOP)) {
			amountMoneySave = moneySave(p);
		} else {
			Objective o = p.getLiObjectives().stream()
					.filter(x -> x.getStatus().equals(Objective.ObjectiveStatus.IN_PROGRESS)).findFirst().orElse(null);
			amountMoneySave = Calculation.reducMoneySave(p);
		}

		double headphone = 150, nintendo = 300, phone = 550, computer = 1400, scooter = 3100, car = 11350;

		double headphoneResult = amountMoneySave * 100 / headphone;
		if (headphoneResult > 100)
			headphoneResult = 100;
		double nintendoResult = amountMoneySave * 100 / nintendo;
		if (nintendoResult > 100)
			nintendoResult = 100;
		double phoneResult = amountMoneySave * 100 / phone;
		if (phoneResult > 100)
			phoneResult = 100;
		double computerResult = amountMoneySave * 100 / computer;
		if (computerResult > 100)
			computerResult = 100;
		double scooterResult = amountMoneySave * 100 / scooter;
		if (scooterResult > 100)
			scooterResult = 100;
		double carResult = amountMoneySave * 100 / car;
		if (carResult > 100)
			carResult = 100;

		LinkedHashMap<String, Double> result = new LinkedHashMap<String, Double>();
		result.put("Headphone", (double) Math.round(headphoneResult));
		result.put("Nintendo", (double) Math.round(nintendoResult));
		result.put("Phone", (double) Math.round(phoneResult));
		result.put("Computer", (double) Math.round(computerResult));
		result.put("Scooter", (double) Math.round(scooterResult));
		result.put("Car", (double) Math.round(carResult));

		return result;
	}

// ************************************méthode réduc tabac*********************************************************		

	public static int quantityNotSmoke(Problem p) {
		int notSmoke = 0;
		Objective obj = p.getLiObjectives().stream()
				.filter(x -> x.getStatus().equals(Objective.ObjectiveStatus.IN_PROGRESS)).findFirst().orElse(null);
		Map<Integer, Integer> weekConso = obj.getRealConso();
		Set<Integer> keys = weekConso.keySet();
		for (Integer key : keys) {
			if (weekConso.get(key) != null)
				notSmoke += p.getStartQuantity() - weekConso.get(key);
		}
		return notSmoke;
	}

	public static double reducMoneySave(Problem p) {
		return quantityNotSmoke(p) * p.getUnitCost();
	}

	public static double finalAverageSmoke(Objective obj) {
		double average = 0;
		Map<Integer, Integer> weekConso = obj.getRealConso();
		Set<Integer> keys = weekConso.keySet();
		for (Integer key : keys) {
			if (weekConso.get(key) != null)
				average += weekConso.get(key);
		}
		return Math.round(average / 7);
	}

//	public static void main(String[] args) {
//
//		Problem pb = new Problem();
//		Objective obj = new Objective();
//		pb.setUnitCost(10);
//		obj.setQuantity(10);
//		obj.setStatus(ObjectiveStatus.IN_PROGRESS);
//		Map<Integer, Integer> map = new HashMap<>();
//
//		for (int i = 0; i < 7; i++) {
//			map.put(i, 8);
//		}
//
//		obj.setRealConso(map);
//		pb.getLiObjectives().add(obj);
//
//		System.out.println("quantity : " + Calculation.quantityNotSmoke(pb));
//		System.out.println("thune : " + Calculation.reducMoneySave(pb));
//
//	}
}

package projetdawan;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import fr.dawan.mvc1.beans.Problem;
import fr.dawan.mvc1.beans.Problem.ProblemFrequence;
import fr.dawan.mvc1.tools.Calculation;

public class CalculationTest {

	@Test
	public void moneySaveNullFromNow() throws Exception {
		Problem pbTest = new Problem();
		pbTest.setStartQuantity(10);
		pbTest.setUnitCost(1);
		pbTest.setProblemFrequence(ProblemFrequence.DAILY);
		Date testDate = new Date();
		pbTest.setCreationDate(testDate);
		assertEquals(0.00d, Calculation.moneySave(pbTest), 0.001);
	}

	@Test
	public void moneySaveTenFromNowYesterday() throws Exception {
		Problem pbTest = new Problem();
		pbTest.setStartQuantity(10);
		pbTest.setUnitCost(1);
		pbTest.setProblemFrequence(ProblemFrequence.DAILY);
		Date testDate = new Date();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(testDate);
		cal.add(Calendar.DATE, -1);
		testDate = cal.getTime();
		pbTest.setCreationDate(testDate);
		assertEquals(10.00d, Calculation.moneySave(pbTest), 0.001);
	}

	@Test
	public void moneySaveExtreme() throws Exception {
		Problem pbTest = new Problem();
		pbTest.setStartQuantity(60d);
		pbTest.setUnitCost(1.5d);
		pbTest.setProblemFrequence(ProblemFrequence.DAILY);
		Date testDate = new Date();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(testDate);
		cal.add(Calendar.DATE, -1);
		testDate = cal.getTime();
		pbTest.setCreationDate(testDate);
		assertEquals(90d, Calculation.moneySave(pbTest), 0.001);
	}

}

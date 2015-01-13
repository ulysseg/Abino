import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.BeforeClass;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;


public class IndividuTest {
	
	public static DataSet input = new DataSet(4, 1);
	
	static Individu i1, i2;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		input.addRow(new DataSetRow(new double[]{0, 0, 0, 0}, new double[]{0})); //equal
		input.addRow(new DataSetRow(new double[]{0, 0, 0, 1}, new double[]{1}));
		input.addRow(new DataSetRow(new double[]{0, 0, 1, 0}, new double[]{1}));
		input.addRow(new DataSetRow(new double[]{0, 0, 1, 1}, new double[]{1}));

		input.addRow(new DataSetRow(new double[]{0, 1, 0, 0}, new double[]{0}));
		input.addRow(new DataSetRow(new double[]{0, 1, 0, 1}, new double[]{0})); //equal
		input.addRow(new DataSetRow(new double[]{0, 1, 1, 0}, new double[]{0})); //equal
		input.addRow(new DataSetRow(new double[]{0, 1, 1, 1}, new double[]{1}));

		input.addRow(new DataSetRow(new double[]{1, 0, 0, 0}, new double[]{0}));
		input.addRow(new DataSetRow(new double[]{1, 0, 0, 1}, new double[]{0})); //equal
		input.addRow(new DataSetRow(new double[]{1, 0, 1, 0}, new double[]{0})); //equal
		input.addRow(new DataSetRow(new double[]{1, 0, 1, 1}, new double[]{1}));

		input.addRow(new DataSetRow(new double[]{1, 1, 0, 0}, new double[]{0}));
		input.addRow(new DataSetRow(new double[]{1, 1, 0, 1}, new double[]{0}));
		input.addRow(new DataSetRow(new double[]{1, 1, 1, 0}, new double[]{0}));
		input.addRow(new DataSetRow(new double[]{1, 1, 1, 1}, new double[]{0})); //equal
		
		i1 = new Individu();
		i2 = new Individu();

	}

//	@Test
//	public void testIndividu() {
//		Individu i = new Individu();
//		
//		Double[] weights;
//		i.setWeights(weights);
//		fail("Not yet implemented");
//	}

	@Test
	public void testIndividuIndividu() {
		fail("Not yet implemented");
	}

	@Test
	public void testFitness() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWeights() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetWeights() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetWeightAtIndex() {
		fail("Not yet implemented");
	}

	@Test
	public void testMutate() {
		fail("Not yet implemented");
	}

	@Test
	public void testCross() {
		System.out.println(i1);
		System.out.println(i2);
		i1.cross(i2);
		System.out.println(i1);
		System.out.println(i2);
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoubleTodouble() {
		fail("Not yet implemented");
	}

}

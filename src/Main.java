import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

//bitbucket.org/louisbob/amino
//algos4.cs.princeton.edu/61event
public class Main {

	public static int nbCases = 4;
	public static DataSet input = new DataSet(4, 1);


	public static void main(String[] args) {

		// On represente selon les colonnes
		// 0 si plus de 1 a gauche
		// 1 si plus de 1 a droite
		// 0 si equalite
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
//		input.addRow(new DataSetRow(new double[]{1, 0, 1, 0}, new double[]{0})); //equal
		input.addRow(new DataSetRow(new double[]{1, 0, 1, 1}, new double[]{1}));

		input.addRow(new DataSetRow(new double[]{1, 1, 0, 0}, new double[]{0}));
		input.addRow(new DataSetRow(new double[]{1, 1, 0, 1}, new double[]{0}));
		input.addRow(new DataSetRow(new double[]{1, 1, 1, 0}, new double[]{0}));
//		input.addRow(new DataSetRow(new double[]{1, 1, 1, 1}, new double[]{0})); //equal


		Population p = new Population(1000);
//		System.out.println(p);
//
//		for(Individu i : p.individus) {
//			System.out.println(i.fitness());
//		}

		// Test de setWeightAtIndex
		//		Individu i = new Individu();
		//		System.out.println(i);
		//		i.setWeightAtIndex(1000, 0);
		//		System.out.println(i);

		// Essai de mutate
		//		System.out.println("Avant mutation :"+p.individus.get(0));
		//		p.individus.get(0).mutate();
		//		System.out.println("Après mutation :"+p.individus.get(0));

		// Test de cross
		//		Individu i = new Individu();
		//		Individu j = new Individu();
		//		System.out.print("i: "+i);
		//		System.out.print("j: "+j);
		//		i.cross(j);
		//		System.out.print("i: "+i);
		//		System.out.print("j: "+j);

		//Test de bordel
//		System.out.println(p);
//		for(Individu i : p.individus) {
//			System.out.println(i.fitness());
//		}
//		Population popDescendante = p.bordel();
//		System.out.println(popDescendante);
//		for(Individu i : popDescendante.individus) {
//			System.out.println(i.fitness());
//		}

		// Test de getBestIndividual
		//		System.out.println(p.getTheBestOfTheBestOfTheBestOfTheBestOfTheBestOfTheBestOfTheBestOfTheBest());

		// Test de getSideWithOnes(dataRow))
		//		for(DataSetRow dataRow : input.getRows()) {
		//			System.out.print(dataRow);
		//			System.out.println("Actual="+getSideWithOnes(dataRow));
		//		}
		
		
		int i = 0;
		double maxFitness = -1;
		while(p.getTheBest().fitness(false) < 1) {
			Population popDescendante = p.bordel();
			p = popDescendante;
			i++;
//			if(p.getTheBest().fitness(false) > maxFitness) {
				maxFitness = p.getTheBest().fitness(true);
				System.out.println(maxFitness);
				System.out.println(p.getTheBest());
//			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(popDescendante);
		} 
		System.out.println("i="+i);
		System.out.println("fitness="+p.getTheBest().fitness(false));
		System.out.println("STOPA");
	}


	/**
	 * retourne 0 si y a plus de 1 a gauche
	 * retourne 1 si y a plus de 1 a droite
	 * @param r
	 * @return
	 */
	public static int getSideWithOnes(DataSetRow r) {
		double[] matrix = r.getInput();
		int left = 0, right = 0;

		for(int i=0; i < matrix.length; i++) {
			if(matrix[i] == 1) {
				if(i < matrix.length/2) {
					left++;
				} else {
					right++;
				}
			}
		}

		if(left > right) {
			return 0;
		} else if (right > left){
			return 1;
		} else { // equal
			return -1;
		}
	}

}

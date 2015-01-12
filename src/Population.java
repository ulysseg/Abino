import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Population {
	protected ArrayList<Individu> individus;

	public Population(int nbIndividus) {
		individus = new ArrayList<Individu>();

		for(int i=0; i < nbIndividus; i++) {
			individus.add(new Individu());
		}
	}

	public Population(Population p) {
		individus = new ArrayList<Individu>();

		for(Individu i : p.individus) {
			individus.add(new Individu(i));
		}
	}

	/**
	 * Fait une mutation sur chacun des individus avec
	 * la proba passee en parametre
	 * @param probability
	 */
	public void mutate(double probability) {
		Random rand = new Random();
		for (Individu i : this.individus) {
			if(rand.nextDouble() <= probability) {
				i.mutate();
			}
		}
	}

	/**
	 * Fait un nombre aleatoire de croisements sur des individus
	 * avec une proba probability
	 * @param probability
	 */
	public void croisements(double probability) {
		Random rand = new Random();
		int nbCroisements = rand.nextInt(10);
		for (int i = 0; i < nbCroisements; i++) {
			int index1 = 0, index2 = 0;
			while(index1 == index2) {
				index1 = rand.nextInt(this.individus.size());
				index2 = rand.nextInt(this.individus.size());
			}
			if(rand.nextDouble() <= probability) {
				this.individus.get(index1).cross(this.individus.get(index2));
			}
		}
	}

	/* 
	 * Cree une nouvelle population a partir de celle courante
	 */
	public Population bordel() {
		Population p = new Population(this);

		// Mutations
		p.mutate(0.2);

		// Croisements
		p.croisements(0.6);


		return p;
	}
	
	public Individu getTheBestOfTheBestOfTheBestOfTheBestOfTheBestOfTheBestOfTheBestOfTheBest() {
		Individu theBest = null;
		for (Individu i : this.individus) {
			if(theBest == null || theBest.fitness() < i.fitness()) {
				theBest = i;
			}
		}
		return theBest;
	}


	@Override
	public String toString() {
		String s = "";

		for(Individu i : this.individus) {
			s += i.toString() + "\n";
		}

		return s;
	}

}

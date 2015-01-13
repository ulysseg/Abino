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
	// Penser a utiliser selection
	//	public Population bordel() {
	//		Population p = new Population(this);
	//
	//		// Mutations
	//		p.mutate(0.2);
	//
	//		// Croisements
	//		p.croisements(0.6);
	//
	//
	//		return p;
	//	}
	public Population bordel() {
		// Create empty population
		Population p = new Population(0);
		Random rand = new Random();


		while(p.individus.size() < this.individus.size()) {
			Individu father, mother;
			father = this.selection();
			while(father == (mother = this.selection()));

			if(rand.nextDouble() <= 0.6) {
				father.cross(mother);
			}

			father.mutate(0.1);
			mother.mutate(0.1);
			
			p.individus.add(father);
			p.individus.add(mother);
		}

		return p;
	}

	public Individu getTheBest() {
		Individu theBest = null;
		for (Individu i : this.individus) {
			if(theBest == null || theBest.fitness(false) < i.fitness(false)) {
				theBest = i;
			}
		}
		return theBest;
	}

	/**
	 * Retourne un individu en en piochant deux au hasard,
	 * puis en choisissant le meilleur avec une probabilite
	 * de 75%
	 */
	public Individu selection() {
		// Pioche au hasard deux individus differents
		Random rand = new Random();
		int index1 = 0, index2 = 0;
		index1 = rand.nextInt(this.individus.size());
		while(index1 == index2) {
			index2 = rand.nextInt(this.individus.size());
		}

		//double fitness1 =
		double fitness1 = this.individus.get(index1).fitness(false);
		double fitness2 = this.individus.get(index2).fitness(false);

		// Determine le meilleur du moins bon
		Individu better, worse;
		if(fitness1 > fitness2) {
			better = this.individus.get(index1);
			worse = this.individus.get(index2);
		} else {
			worse = this.individus.get(index1);
			better = this.individus.get(index2);
		}

		// Dans 75% des cas, onp rend le meilleur des deux
		if(rand.nextDouble() <= 0.75) {
			return better;
		} else {
			return worse;
		}
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

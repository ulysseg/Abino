import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.Perceptron;
import org.neuroph.util.TransferFunctionType;


public class Individu {
	NeuralNetwork nn;
	double fitness;

	// Chaque individu est un reseau de neurone
	public Individu() {
//		nn = new Perceptron(Main.nbCases, 1);
//		nn.getLayerAt(0).getNeuronAt(0).
		this.nn = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 4, 3, 1);
		nn.randomizeWeights(-1, 1);
		this.fitness = this.fitness(false);
	}

	/**
	 * Copy constructor
	 * @param i
	 */
	public Individu(Individu i) {
//		nn = new Perceptron(Main.nbCases, 1);
		this.nn = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 4, 3, 1);
		nn.setWeights(DoubleTodouble(i.getWeights()));
		this.fitness = i.fitness;
	}

	/**
	 * Fitness ou fonction d'évaluation
	 * remettre systematique le meilleur
	 * @return
	 */
	public double fitness(boolean display) {
		// Tester l'individu sur un certain de matrices pour connaitre son "score"
		int nbRightGuess = 0;
		
		// Prendre en compte le fait d'etre proche de 0 ou de 1

		for(DataSetRow dataRow : Main.input.getRows()) {
			this.nn.setInput(dataRow.getInput());
			this.nn.calculate();
			double[] networkOutput = nn.getOutput(); // Voir ce que ce retourne
//			int test = (int) networkOutput[0];
			if(display) {
				System.out.println("networkOutput="+networkOutput[0]);
			}
			// Un peu bullshit cette ligne
//			if(Main.getSideWithOnes(dataRow) == -1) {
//				nbRightGuess++;
//			if(networkOutput[0] == Main.getSideWithOnes(dataRow)) {
//				nbRightGuess++;
//			}
			
			
			if(networkOutput[0] <= 0.6 &&  Main.getSideWithOnes(dataRow) == 0) {
				nbRightGuess++;
			} else if(networkOutput[0] > 0.6 && Main.getSideWithOnes(dataRow) == 1){
				nbRightGuess++;
			}

			
		}
		return ((double) nbRightGuess)/Main.input.size();
	}

	public Double[] getWeights() {
		return this.nn.getWeights();
	}

	/**
	 * Set weights
	 * @param weights
	 * fait n'importe quoi
	 */
	public void setWeights(Double[] weights) {
		for(int i=0; i < this.nn.getWeights().length; i++) {
			this.nn.getWeights()[i] = weights[i];
		}
	}

	public void setWeightAtIndex(double value, int index) {
		//		this.nn.getWeights()[index] = value;
		double[] weights = DoubleTodouble(this.nn.getWeights());
		weights[index] = value;
		this.nn.setWeights(weights);
	}

	/**
	 * TODO
	 */
	public void mutate(double probability) {
		Random rand = new Random();

		for (int i = 0; i < this.getWeights().length; i++) {
			if(rand.nextDouble() <= probability) {
//				double randNeuronValue = rand.nextDouble();
				double randNeuronValue = -1 + 2*rand.nextDouble();
				this.setWeightAtIndex(randNeuronValue, i);
			}
		}
	}

	/*
	 * croisement en un point (tjs du point du croisement jusqu'a la fin)
	 * modifie this et i
	 */
	public void cross(Individu i) {
		//		Individu child = new Individu();
		Random rand = new Random();
		// Point de croisement
		int crossPoint = rand.nextInt(this.getWeights().length);

		// Sauvegarde des coefficients de this du point de 
		// croisement jusqu'a la fin
		double[] valueTmp = new double[this.getWeights().length - crossPoint];
		for(int j = crossPoint, k = 0; j < this.getWeights().length; j++, k++) {
			valueTmp[k] = this.getWeights()[j];
		}

		// Copie de parametre vers this
		for(int j = crossPoint; j < this.getWeights().length; j++) {
			this.setWeightAtIndex(i.getWeights()[j], j);
		}

		// Copie de this vers parametre a l'aide valueTmp
		for(int j = crossPoint, k = 0; j < this.getWeights().length; j++, k++) {
			i.setWeightAtIndex(valueTmp[k], j);
		}
	}



	@Override
	public String toString() {
		String s = "";

		for(Layer l : this.nn.getLayers()) {
			System.out.println();
			for(Neuron n : l.getNeurons()) {
				for(int i=0; i < n.getWeights().length; i++) {
					s += n.getWeights()[i].value + " ";
				}
			}
		}

		return s;
	}

	/**
	 * A tester
	 * @param in
	 * @return
	 */
	public static double[] DoubleTodouble(Double[] in) {
		double[] tempArray = new double[in.length];
		int i = 0;
		for(Double d : in) {
			tempArray[i] = (double) d;
			i++;
		}
		return tempArray;
	}

}

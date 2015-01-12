import java.util.Arrays;
import java.util.Random;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;


public class Individu {
	NeuralNetwork nn;

	// Chaque individu est un reseau de neurone
	public Individu() {
		nn = new Perceptron(Main.nbCases, 1);
		nn.randomizeWeights();
	}
	
	public Individu(Individu i) {
		nn = new Perceptron(Main.nbCases, 1);
		nn.setWeights(DoubleTodouble(i.getWeights()));
	}

	/**
	 * Fitness ou fonction d'évaluation
	 * @return
	 */
	public double fitness() {
		// Tester l'individu sur un certain de matrices pour connaitre son "score"
		int nbRightGuess = 0;
		
		for(DataSetRow dataRow : Main.input.getRows()) {
			this.nn.setInput(dataRow.getInput());
			this.nn.calculate();
			double[] networkOutput = nn.getOutput(); // Voir ce que ce retourne
			int test = (int) networkOutput[0];
			System.out.println("networkOutput="+networkOutput[0]);
			// Un peu bullshit cette ligne
//			if(networkOutput[0] == Main.getSideWithOnes(dataRow)) {
			if(test == Main.getSideWithOnes(dataRow)) {
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
	public void mutate() {
		Random rand = new Random();
		int neuronIndex = rand.nextInt(Main.nbCases-1);
//		System.out.println("neuronIndex="+neuronIndex);
		double value = rand.nextDouble();
		this.setWeightAtIndex(value, neuronIndex);

	}
	
	/*
	 * cross switch les deux neurones entre les deux individus
	 * modifie this et i
	 */
	public void cross(Individu i) {
//		Individu child = new Individu();
		Random rand = new Random();
		int neuronIndex = rand.nextInt(Main.nbCases-1);
		double valueTmp = this.getWeights()[neuronIndex]; 
		
		// Pour celui de "gauche"
		this.setWeightAtIndex(i.getWeights()[neuronIndex], neuronIndex);
		// Celui en parametre
		i.setWeightAtIndex(valueTmp, neuronIndex);
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

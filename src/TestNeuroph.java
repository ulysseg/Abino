import java.util.Arrays;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.Perceptron;


public class TestNeuroph {

	public static void main(String[] args) {


		// create training set (logical AND function)
		DataSet trainingSet = new DataSet(2, 1);
		trainingSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
		trainingSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{0}));
		trainingSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{0}));
		trainingSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{1}));

		// create perceptron neural network
		NeuralNetwork myPerceptron = new Perceptron(2, 1);
		
		System.out.println("Avant l'apprentissage");
		for(Layer l : myPerceptron.getLayers()) {
			for(Neuron n : l.getNeurons()) {
				for(int i=0; i < n.getWeights().length; i++) {
					double value = n.getWeights()[i].value;
					System.out.println(value);
				}
			}
		}

		// learn the training set
		myPerceptron.learn(trainingSet);

		System.out.println("Après l'apprentissage");
		for(Layer l : myPerceptron.getLayers()) {
			for(Neuron n : l.getNeurons()) {
				for(int i=0; i < n.getWeights().length; i++) {
					double value = n.getWeights()[i].value;
					System.out.println(value);
				}
			}
		}

		// test perceptron
		System.out.println("Testing trained perceptron");
		testNeuralNetwork(myPerceptron, trainingSet);
		
		// Modif
		myPerceptron.getLayerAt(1).getNeuronAt(0).getWeights()[0].value = 1000;
//		myPerceptron.
		
		System.out.println("Après modif manuelle");
		for(Layer l : myPerceptron.getLayers()) {
			for(Neuron n : l.getNeurons()) {
				for(int i=0; i < n.getWeights().length; i++) {
					double value = n.getWeights()[i].value;
					System.out.println(value);
				}
			}
		}

		// save trained perceptron
		myPerceptron.save("mySamplePerceptron.nnet");

		// load saved neural network
		NeuralNetwork loadedPerceptron = NeuralNetwork.createFromFile("mySamplePerceptron.nnet");

		// test loaded neural network
		System.out.println("Testing loaded perceptron");
		testNeuralNetwork(loadedPerceptron, trainingSet);

	}

	public static void testNeuralNetwork(NeuralNetwork nnet, DataSet tset) {

		for(DataSetRow dataRow : tset.getRows()) {

			nnet.setInput(dataRow.getInput());
			nnet.calculate();
			double[ ] networkOutput = nnet.getOutput();
			System.out.print("Input: " + Arrays.toString(dataRow.getInput()) );
			System.out.println(" Output: " + Arrays.toString(networkOutput) );

		}

	} 

}

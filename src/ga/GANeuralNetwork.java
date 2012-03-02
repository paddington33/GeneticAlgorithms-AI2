package ga;

import java.util.ArrayList;

import neuralNets.NeuralNetwork;
import neuralNets.Neuron;
import neuralNets.NeuronConnection;

public class GANeuralNetwork extends NeuralNetwork implements GeneticAlgorithm {

	public GANeuralNetwork(ArrayList<ArrayList<Neuron>> network) {
		super(network);
	}

	@Override
	public int compareTo(GeneticAlgorithm o) {
		return 0;
	}

	@Override
	public void buildByChromosomes(ArrayList<Chromosome> chromosomes) {
		int index = 0;
		for(ArrayList<Neuron> layer : network)
		{
			for(Neuron neuron : layer)
			{
				for(NeuronConnection neuronConnection : neuron.getInputs())
				{
					neuron.addInputConnection((NeuronConnection)chromosomes.get(index));
					index++;
				}
				
				for(NeuronConnection neuronConnection : neuron.getInputs())
				{
					neuron.addOutputConnection((NeuronConnection)chromosomes.get(index));
					index++;
				}			
				
			}
		}
	}

	@Override
	public ArrayList<Chromosome> getChromosomes() {
		return null;
	}

	@Override
	public double getFitness() {
		return 0;
	}

}

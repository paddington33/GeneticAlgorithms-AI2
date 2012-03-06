package ga;

import java.util.ArrayList;

import neuralNets.FinalLayerNeuron;
import neuralNets.InputLayerNeuron;
import neuralNets.NeuralNetwork;
import neuralNets.NeuralNetworkFactory;
import neuralNets.Neuron;
import neuralNets.NeuronConnection;

public class GANeuralNetworkFactory extends NeuralNetworkFactory {
	
	public static NeuralNetwork makeUsingChromosomes(int[] nNeuronsInLayers, ArrayList<NeuralNetworkChromosome> chromosomes)
	{
		
		ArrayList<ArrayList<Neuron>> network = new ArrayList<ArrayList<Neuron>>();
		
		for(int i = 0 ; i < nNeuronsInLayers.length; i++ )
		{
			ArrayList<Neuron> layer = new ArrayList<Neuron>();
			for(int j = 0;j < nNeuronsInLayers[i];j++)
			{
				Neuron neuron;
				if(i == 0)
					neuron = new InputLayerNeuron();
				else if(i == nNeuronsInLayers.length - 1)
					neuron = new FinalLayerNeuron();
				else
					neuron = new Neuron();
				layer.add(neuron);
			}
			
			network.add(layer);
			
			int weightCounter = 0;
			
			//If not first layer generate connections
			if(i != 0)
			{
				for(int j = 0; j < nNeuronsInLayers[i-1] ; j++)
				{
					for(int k = 0; k < nNeuronsInLayers[i]; k++ )
					{
						
						NeuronConnection neuronConnection = new NeuronConnection(
								network.get(i-1).get(j), 									//jth neuron in layer i-1
								network.get(i).get(k),										//kth neuron in layer i
								chromosomes.get(weightCounter++).getWeight() );				//weight from chromosome
						
						network.get(i-1).get(j).addOutputConnection(neuronConnection);
						network.get(i).get(k).addInputConnection(neuronConnection);
					}
				}
			}
		}
		
		NeuralNetwork neuralNetwork = new NeuralNetwork(network);
		
		return neuralNetwork;
	}
}

package ga;

import neuralNets.Neuron;
import neuralNets.NeuronConnection;

public class GANeuron extends Neuron {
	public void removeInputConnection(NeuronConnection neuronConnection)
	{
		this.inputs.remove(neuronConnection);
	}
	
	public void removeOutputConnection(NeuronConnection neuronConnection)
	{
		this.outputs.remove(neuronConnection);
	}
}

package ga;

import java.util.ArrayList;

import neuralNets.FinalLayerNeuron;
import neuralNets.InputLayerNeuron;
import neuralNets.NeuralNetwork;
import neuralNets.Neuron;

public class NeuralNetworkPopWrapper implements GeneticAlgorithm {

	private ArrayList<? extends Chromosome> chromosomes;
	private NeuralNetwork network;
	
	@Override
	public int compareTo(GeneticAlgorithm arg0) {
		return (int) (arg0.getFitness() - this.getFitness());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void buildByChromosomes(ArrayList<? extends Chromosome> chromosomes) {
		this.chromosomes = chromosomes;
		int[] nNeuronsInLayers = {4,7,4};
		network = GANeuralNetworkFactory.makeUsingChromosomes(nNeuronsInLayers, (ArrayList<NeuralNetworkChromosome>) chromosomes);
	}

	@Override
	public ArrayList<? extends Chromosome> getChromosomes() {
		return chromosomes;
	}

	@Override
	public double getFitness() {

		int[] randomPattern = randomPattern(network.getInputLayer().size());
		int[] invertedPattern = invertPattern(randomPattern);
		int[] shiftedPattern = shiftPattern(randomPattern);
		double[] halfPattern = halfPattern(randomPattern);
		int[] resultPattern;
		
		resultPattern = invertedPattern;
		
		
		int j = 0;
		for(Neuron neuron : network.getInputLayer())
		{
			((InputLayerNeuron)neuron).setValue(randomPattern[j]);
			j++;
		}
		
		network.forwardPropagation();
		
		double acc = 0;
		double err = 0;
		
		j = 0;
		for(Neuron neuron : network.getFinalLayer())
		{
			err = Math.abs(((FinalLayerNeuron)neuron).getOutput() - resultPattern[j]);
			acc -= err;
			j++;
		}
		
		return acc;		
	}
	
	
	
	
	
	//TESTS
	

	public static double randomValue()
	{
		return Math.random();
	}
	
	public static int[] randomPattern(int size)
	{
		int[] pattern = new int[size];
		for(int i = 0;i<size;i++)
		{
			if(Math.random() > .5)
				pattern[i] = 1;
			else
				pattern[i] = 0;
		}
		return pattern;
	}
	
	
	
	public static int[] invertPattern(int[] pattern)
	{
		int[] invertedPattern = new int[pattern.length];
		for(int i = 0;i<pattern.length;i++)
			if(pattern[i] == 0)
				invertedPattern[i] = 1;
			else
				invertedPattern[i] = 0;
		return invertedPattern;
	}
	
	public static int[] shiftPattern(int[] pattern)
	{
		int shiftedPattern[] = new int[pattern.length];
		
		for(int i = 0 ; i < pattern.length ; i++)
		{
			shiftedPattern[(i+1)%(pattern.length)] = pattern[i];			
		}
		
		return shiftedPattern;
	}
	
	public static double[] halfPattern(int[] pattern)
	{
		double halfPattern[] = new double[pattern.length];
		
		for(int i = 0 ; i < pattern.length ; i++)
		{
			halfPattern[i] = (double)pattern[i]*.5;			
		}
		
		return halfPattern;
	}
	
	
	//
	
	
	

}

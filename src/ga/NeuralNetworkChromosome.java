package ga;

public class NeuralNetworkChromosome implements Chromosome {
	private double weight;
	private Object value;
	
	public NeuralNetworkChromosome(double weight)
	{
		this.weight = weight;
		this.value = weight;
	}

	public double getWeight()
	{
		return weight;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(Object value) {
		this.value = value;
	}
		
}

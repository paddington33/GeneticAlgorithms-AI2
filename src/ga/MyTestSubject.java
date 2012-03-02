package ga;

import java.util.ArrayList;

public class MyTestSubject implements GeneticAlgorithm {

	private ArrayList<Chromosome> chromosomes;
	private double fitness = 0.0;
	
	public MyTestSubject() {
	}
	
	@Override
	public void buildByChromosomes(ArrayList<Chromosome> chromosomes) {
		this.chromosomes = chromosomes;
		for(Chromosome chromosome : chromosomes)
			fitness += ((MyChromosomeImp)chromosome).getValue();
	}

	@Override
	public ArrayList<Chromosome> getChromosomes() {
		return chromosomes;
	}

	@Override
	public int compareTo(GeneticAlgorithm subject) 
	{
		return subject.getFitness() < this.getFitness() ? -1 : 1;
	}

	@Override
	public double getFitness() {
		return fitness;
	}
	
	public String toString()
	{
		return "f: " + fitness;
	}

}

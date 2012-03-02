package ga;

import java.util.ArrayList;

public interface GeneticAlgorithm extends Comparable<GeneticAlgorithm> {
	public void buildByChromosomes(ArrayList<Chromosome> chromosomes);
	public ArrayList<Chromosome> getChromosomes();
	public double getFitness();
}

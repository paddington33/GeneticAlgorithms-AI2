package ga;

import java.util.ArrayList;

public interface GeneticAlgorithm extends Comparable<GeneticAlgorithm> {
	public void buildByChromosomes(ArrayList<? extends Chromosome> chromosomes);
	public ArrayList<? extends Chromosome> getChromosomes();
	public double getFitness();
}

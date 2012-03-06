package ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgorithmEngine 
{
	private ArrayList<GeneticAlgorithm> population = new ArrayList<GeneticAlgorithm>();
	private int populationSize = 2000; //default
	private Class<?> populationClass;
	
	public GeneticAlgorithmEngine(int populationSize,Class<?> populationClass) {
		this.populationSize = populationSize;
		this.populationClass = populationClass;
	}
	
	public void generatePopulation()
	{
		Random random = new Random();
		
		GeneticAlgorithm subject = null;
		for(int i = 0;i<populationSize;i++)
		{
			
			ArrayList<Chromosome> cs = new ArrayList<Chromosome>();

			for(int j = 0;j<56;j++) ///Make DYNAMIC!!
				cs.add(new NeuralNetworkChromosome(random.nextGaussian()));
			
			try {
				subject = (GeneticAlgorithm) populationClass.newInstance();
				subject.buildByChromosomes(cs);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
			population.add(subject);
		}
		
	}
	
	public void nextGeneration()
	{
		int noParents = 100;
		
		Collections.sort(population);
		
		Random random = new Random();
		
		ArrayList<GeneticAlgorithm> parents = new ArrayList<GeneticAlgorithm>();
		
		for(int i = 0;i< noParents;i++)
			parents.add(population.get(i));

		ArrayList<GeneticAlgorithm> nextGeneration = new ArrayList<GeneticAlgorithm>();
		for(int i = 0;i < populationSize;i++)
		{
			int indexOfFirstParent = random.nextInt(noParents);
			int indexOfSecondParent = random.nextInt(noParents);
			
			GeneticAlgorithm firstParrent = parents.get(indexOfFirstParent);
			GeneticAlgorithm secondParrent = parents.get(indexOfSecondParent);
			
			ArrayList<? extends Chromosome> offspringChromosomes = onePointCrossOverStrategy(firstParrent.getChromosomes(), secondParrent.getChromosomes());
			
			mutateChromosomes(offspringChromosomes);			
			
			GeneticAlgorithm offspring = null;
			try {
				offspring = (GeneticAlgorithm) populationClass.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
			offspring.buildByChromosomes(offspringChromosomes);
			
			nextGeneration.add(offspring);
		}

		double aveFit = 0.0;
		
		//Print generation fitness 
		for(GeneticAlgorithm f : nextGeneration)
		{
			aveFit += f.getFitness();
		}
		System.out.println(aveFit/populationSize);
		
		population = nextGeneration;
	}
	
	
	private void mutateChromosomes(ArrayList<? extends Chromosome> chromosomes) {
		Random random = new Random();
		for(Chromosome chromosome : chromosomes)
		{
			if(random.nextDouble() > 2.0/chromosomes.size())
			{
				double value = (Double) chromosome.getValue();
				value += random.nextGaussian()/100.0;
				chromosome.setValue(value);
			}
		}
	}

	public ArrayList<? extends Chromosome> onePointCrossOverStrategy(ArrayList<? extends Chromosome> c1, ArrayList<? extends Chromosome> c2)
	{
		//Assume same size, always same order
		Random random = new Random();
		int crossOverPoint = random.nextInt(c1.size());
		
		ArrayList<Chromosome> offspring = new ArrayList<Chromosome>();
		for(int i = 0;i < crossOverPoint;i++)
			offspring.add(c1.get(i));
		
		for(int i = crossOverPoint;i < c2.size();i++)
			offspring.add(c2.get(i));
		
		return offspring;
	}
	
	
}

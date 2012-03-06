package ga;

public class GATestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GeneticAlgorithmEngine ga = new GeneticAlgorithmEngine(1000, NeuralNetworkPopWrapper.class);
		ga.generatePopulation();

		for(int i = 0;i<10000;i++)
			ga.nextGeneration();
	}

}

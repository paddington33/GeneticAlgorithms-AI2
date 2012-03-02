package ga;


public class MyChromosomeImp implements Chromosome {
	private double value = 0.0;
	public MyChromosomeImp(double value) {
		this.value = value;
	}
	
	public double getValue(){
		return value;
	}
	
	public void setValue(double value)
	{
		this.value = value;
	}
	
	public String toString(){
		return "val: " + value;
	}

}

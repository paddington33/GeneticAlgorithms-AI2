package ga;


public class MyChromosomeImp implements Chromosome {
	private Object value = 0.0;
	
	public MyChromosomeImp(double value) {
		this.value = value;
	}
	
	public Object getValue(){
		return value;
	}
	
	public void setValue(double value)
	{
		this.value = value;
	}
	
	public String toString(){
		return "val: " + value;
	}

	@Override
	public void setValue(Object value) {
		this.value = value;
	}

}

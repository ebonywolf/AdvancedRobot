package Wagner.geneticAlgorithm;


public abstract class Gene {
	protected int gene;

	protected Gene() {

	}

	public Gene(String s) {
		readText(s);
	}

	protected abstract void readText(String s);

	public int getValue() {
		return gene;
	}

	protected abstract void mutate(double magnitude);

	public  Gene clone() {
		return null;
	}

	public abstract String toString();

}

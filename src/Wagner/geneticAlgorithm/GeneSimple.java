package Wagner.geneticAlgorithm;

import Wagner.utilities.Calc;

public class GeneSimple extends Gene {
	private int max;
	private int min;
	
	GeneSimple(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	public GeneSimple(int min, int max){
		this.max=max;
		this.min=min;
		gene=Calc.random(max-min)+min;
	}
	@Override
	protected void readText(String s) {
		String number="";
		int pos=0;
		for(int i=0;i<3;i++) {
			number= "";
			while(s.charAt(pos)!='@') {
				number+=s.charAt(pos);
				pos++;
			}
			pos++;
			switch (i) {
			case 0:min=Integer.parseInt(number);break;
			case 1:max=Integer.parseInt(number);break;
			case 2:gene=Integer.parseInt(number);break;
			default:
				break;
			}
		}
	}
	@Override
	protected void mutate(double magnitude) {
	
		double p = magnitude;
		p =  (double)max * p;
		gene += Calc.random((int) p)-(p/2);
		if (gene > max)
			gene = max;
		if (gene < min)
			gene = min;
	}
	public int getMax() {
		return max;
	}
	public int getMin() {
		return min;
	}

	public GeneSimple clone() {
		GeneSimple novo = new GeneSimple(min, max);
		novo.gene = gene;
		return novo;
	}

	@Override
	public String toString() {

		String s = "";
	
		s += gene + "@";

		return s;
	}

}

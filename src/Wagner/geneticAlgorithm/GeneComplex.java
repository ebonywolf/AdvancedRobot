package Wagner.geneticAlgorithm;

import Wagner.utilities.Calc;

public class GeneComplex extends Gene{

	
	public GeneComplex() {
		gene=Calc.random(100000);
	}
	
	public GeneComplex(String s) {
		super(s);
	}
	@Override

	protected void mutate(double magnitude) {
		double p = magnitude;
		p = gene * p;
		gene += Calc.random((int) p);
		
	}

	@Override
	protected void readText(String s) {
		String number = "";
		int pos = 0;

		number = "";
		while (s.charAt(pos) != '@') {
			number += s.charAt(pos);
			pos++;
		}
		pos++;

		gene = Integer.parseInt(number);
	}

	@Override
	public Gene clone() {
		Gene novo = new GeneComplex();
		
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

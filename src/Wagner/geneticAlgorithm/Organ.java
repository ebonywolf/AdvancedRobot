package Wagner.geneticAlgorithm;

import java.util.ArrayList;

import Wagner.advanced.MegaRobot;
import Wagner.advanced.Module;
import Wagner.utilities.Pointer;
import Wagner.utilities.TipoComparavel;

public abstract class Organ extends Module implements Cloneable,
		TipoComparavel<Organ> {
	public ArrayList<Genotype> genotypes=new ArrayList<Genotype>();
	public double fitness;

	public Organ(Pointer<MegaRobot> a) {
		super(a.object);
		genotypes = new ArrayList<Genotype>();
		fitness = 0;

	}
	public Organ(Pointer<MegaRobot> a, String gene) {
		super(a.object);
		int pos = 0;
		int max = gene.length();
		while (pos < max) {
			String novogene = "";
			while (gene.charAt(pos) != '&') {
				novogene += gene.charAt(pos);
				pos++;
			}
			pos++;
			genotypes.add(new Genotype(novogene));
		}
	}

	public abstract void reset();

	public Organ(MegaRobot a, ArrayList<Genotype> genotipo) {
		super(a);
		genotypes = genotipo;
		fitness = 0;
	}

	public Genotype get(int n) {
		return genotypes.get(n);
	}

	public double getFitness() {
		return fitness;

	}

	public Organ(MegaRobot a, String s) {
		super(a);
		
		int pos = 0;
		int max = s.length();
		while (pos < max) {
			String novogene = "";
			while (s.charAt(pos) != '&') {
				novogene += s.charAt(pos);
				pos++;
			}
			pos++;
			genotypes.add(new Genotype(novogene));
		}
	}

	public Organ(MegaRobot a) {
		super(a);
		reset();
	}
	public ArrayList<Genotype> cross(Organ mom) {
		ArrayList<Genotype> son = new ArrayList<Genotype>();
		for (int i = 0; i < genotypes.size(); i++) {
			Genotype novo=get(i).cross(mom.get(i),
					GeneticConstants.CROSS_PERCENT);
			
			son.add(novo);
		}
		
		return son;
	}

	public String toString() {
		String s = "";
		for (Genotype a : genotypes) {
			s += a.toString();
			s += '&';
		}
		return s;
	}

	public boolean greaterThan(Organ a) {
		return fitness > a.fitness;
	}

	public boolean lesserThan(Organ a) {
		return fitness < a.fitness;
	}

	public boolean equalTo(Organ a) {
		return fitness == a.fitness;
	}

}

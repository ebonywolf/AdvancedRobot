package Wagner.geneticAlgorithm;

import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import Wagner.utilities.Calc;

public class Genotype {
	private ArrayList<Gene> genes;
	private double mutation;
	private double mutationPercent;
	private ArrayList<GeneSimple> geneTypes;
	private int geneAt = 0;
//	private int size;

	Genotype(ArrayList<GeneSimple> types, double mutationMagnitude,
			double mutationPercent, int maxsize) {
		geneTypes = types;
		this.mutation = mutationMagnitude;
		this.mutationPercent = mutationPercent;
		
		genes = new ArrayList<Gene>();

		int number = geneTypes.size();
		GeneSimple novo;
		GeneSimple tipo;
		for (int i = 0; i < maxsize; i++) {
			tipo = geneTypes.get(Calc.random(number));
			novo = new GeneSimple(tipo.getMin(), tipo.getMax());
			genes.add(novo);
		}
	}

	public Genotype(GeneSimple type, double mutationMagnitude, double mutationPercent,
			int maxsize) {
		geneTypes = new ArrayList<GeneSimple>();
		geneTypes.add(type);
		this.mutation = mutationMagnitude;
		this.mutationPercent = mutationPercent;

		genes = new ArrayList<Gene>();

		int number = geneTypes.size();
		GeneSimple novo;
		GeneSimple tipo;
		for (int i = 0; i < maxsize; i++) {
			tipo = geneTypes.get(Calc.random(number));
			novo = new GeneSimple(tipo.getMin(), tipo.getMax());
			genes.add(novo);
		}
	}

	Genotype(ArrayList<GeneSimple> types, double mutation, double mutationPercent,
			ArrayList<Gene> ngenes) {
		geneTypes = types;
		this.mutation = mutation;
		this.mutationPercent = mutationPercent;
		genes = new ArrayList<Gene>();
		for (Gene a : ngenes) {
			genes.add(a.clone());
		}
		
	}

	public Genotype(String s){
		genes=new ArrayList<Gene>();
		int pos=0;
		int max=s.length();
		while(pos<max) {
			String novogene="";
			while(s.charAt(pos)!='#') {
				novogene+=s.charAt(pos);
				pos++;
			}
			pos++;
			genes.add(new GeneComplex(novogene));
		}
		max=genes.size();
		
	}
	public void mutateGenes() {
		
		for (Gene g : genes) {
			if (Math.random() < mutationPercent) {
				
				g.mutate(mutation);
			}
		}
	}

	public Genotype cross(Genotype mom, double crossPercent) {
		int total = genes.size();
		int number = (int) (total * crossPercent);
		int mutatedGenes[] = new int[total];
		for (int i = 0; i < total; i++) {
			mutatedGenes[i] = i;
		}
		Calc.shuffleArray(mutatedGenes);
		Genotype son = clone();
		for (int i = 0; i < number; i++) {
			int crossingGene=mutatedGenes[i];
			son.genes.remove(crossingGene);
			son.genes.add(crossingGene, mom.genes.get(crossingGene));
		}
		son.mutateGenes();
		return son;
	}

	public int getCurrent() {
		return genes.get(geneAt).getValue();
	}
	public int get(int n) {
		return genes.get(n).getValue();
	}

	public void advanceCurrent() {
		geneAt++;
		if(geneAt>=genes.size()) {
			geneAt=0;
		}
	}

	public Genotype clone() {
		Genotype novo = new Genotype(geneTypes, mutation, mutationPercent,
				genes);
		return novo;
	}
	public String toString() {
		String s="";
		for(Gene a:genes) {
			s+=a.toString();
			s+='#';
		}
		return s;
	}

}

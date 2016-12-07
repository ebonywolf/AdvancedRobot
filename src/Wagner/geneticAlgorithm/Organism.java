package Wagner.geneticAlgorithm;

import java.util.ArrayList;

import Wagner.advanced.MegaRobot;
import Wagner.advanced.Module;
import Wagner.utilities.Calc;
import Wagner.utilities.TipoComparavel;

public class Organism extends Module implements TipoComparavel<Organism> {
	public ArrayList<Organ> organs;
	public double fitness;
	
	public Organism(MegaRobot a) {
		super(a);
		organs = new ArrayList<Organ>();	
	}
	public Organism(MegaRobot a,ArrayList<Organ> organs) {
		super(a);
		this.organs =organs;	
	}
	public void add(Organ a) {
		organs.add(a);
	}

	@Override
	public void update() {
		for(Organ a: organs) {
			a.update();
		}
	}
	public Organism cross(Organism mom) {
		Organism son=clone();
		for(int i=0;i<organs.size();i++) {		
			son.organs.get(i).genotypes= organs.get(i).cross(mom.organs.get(i));
		}
		return son;
	}
	public Organism clone() {
		ArrayList<Organ> novo= GeneticConstants.CloneOrgans(this);
		Organism son=new Organism(self, novo);
		return son;
	}
	@Override
	public boolean greaterThan(Organism a) {
		
		return fitness>a.fitness;
	}
	@Override
	public boolean lesserThan(Organism a) {
		// TODO Auto-generated method stub
		return fitness<a.fitness;
	}
	@Override
	public boolean equalTo(Organism a) {
		// TODO Auto-generated method stub
		return  fitness==a.fitness;
	}
	public void calculateFitness() {
		int soma=0;
		for(Organ a: organs) {
			soma+=a.fitness;
		}
		fitness=soma;
	}
	


	

}

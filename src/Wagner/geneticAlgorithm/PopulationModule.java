package Wagner.geneticAlgorithm;

import java.util.ArrayList;

import Wagner.advanced.MegaRobot;
import Wagner.advanced.Module;
import Wagner.utilities.TopList;

public class PopulationModule extends Module {

	public ArrayList<Organism> population;

	TopList<Organism> baddass = new TopList<Organism>(10);
	TopList<Organism> daddies;
	private int total;
	private int current = 0;
	private int turns;
	private int count=0;
	private int evolution=0;
	private double fitnessTotal=0;
	
	public PopulationModule(MegaRobot a, Organism sample, int population,int turn) {
		super(a);
		ArrayList<Organ> organs;
		Organism novo;
		this.turns=turn;
		this.population=new ArrayList<Organism>();
		for (int i = 0; i < population; i++) {
			organs =  GeneticConstants.CloneOrgans(sample);
			for (Organ org : organs) {
				org.reset();
			}
			novo = new Organism(a, organs);
			this.population.add(novo);
		}
		total = this.population.size();
		daddies = new TopList<Organism>(total/4);

	}
	@Override
	public void update() {
		population.get(current).update();

	}

	public Organism getCurrent() {
		return population.get(current);
	}
	public void compileTopten() {
		
		SourceGenerator.generateSource(baddass);
	}
	public void endRound() {
		Organism selecionado=getCurrent();
		selecionado.calculateFitness();
		fitnessTotal+=selecionado.fitness;
		count++;
		if(count>=turns) {
			count=0;
			current++;
			
			selecionado.fitness=fitnessTotal/turns;
			fitnessTotal=0;
			System.out.println(selecionado.fitness);
			baddass.add(selecionado);
			daddies.add(selecionado);
		}
		
		if (current == total) {
			evolution++;
			current = 0;
			ArrayList<Organism> sons = new ArrayList<Organism>();
			daddies.shuffle();
			for (int i = 0; i < daddies.size(); i += 2) {
				for (int j = 0; j < 8; j++) {
					Organism novo=((Organism) daddies.get(i))
							.cross((Organism) daddies.get(i + 1));
					sons.add(novo);
				
				}
			}
			
			population.clear();
			population=sons;
			daddies.clear();
		}
	}
	public void report() {
		int media=0;
		for(int i=0; i<current;i++) {
			media+=population.get(i).fitness;
		}
		media=media/current;
		
		System.out.println("Best so far:"+baddass.get(0).fitness+
				"\nmedium for this round:"+media+"\nCurrent Evolution:"+evolution+
				"\ncurrent Robot:"+current+" out of "+ population.size());
		
		
	}

}

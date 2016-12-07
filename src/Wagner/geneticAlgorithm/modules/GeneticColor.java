package Wagner.geneticAlgorithm.modules;

import java.awt.Color;

import Wagner.advanced.MegaRobot;
import Wagner.geneticAlgorithm.Gene;
import Wagner.geneticAlgorithm.GeneSimple;
import Wagner.geneticAlgorithm.GeneticConstants;
import Wagner.geneticAlgorithm.Genotype;
import Wagner.geneticAlgorithm.Organ;
import Wagner.utilities.Pointer;

public class GeneticColor extends Organ {


	boolean done = false;

	public GeneticColor(Pointer<MegaRobot> a) {
		super(a);
		done = false;
		// TODO Auto-generated constructor stub
	}
	public GeneticColor(MegaRobot a) {
		super(a);
	}
	public GeneticColor(MegaRobot a, String gene) {
		super(a,gene);
	}

	@Override
	public void reset() {
		GeneSimple red = new GeneSimple(0, 255);// 10 0s
		  Genotype color = new Genotype(red, GeneticConstants.MUTATIONMAG,
				GeneticConstants.MUTATION_PERCENT, 3);
			GeneSimple green = new GeneSimple(0, 255);// 10 0s
			  Genotype color2 = new Genotype(green, GeneticConstants.MUTATIONMAG,
					GeneticConstants.MUTATION_PERCENT, 3);
				GeneSimple blue = new GeneSimple(0, 255);// 10 0s
				  Genotype color3 = new Genotype(blue, GeneticConstants.MUTATIONMAG,
						GeneticConstants.MUTATION_PERCENT, 3);

		done = false;
		genotypes.add(color);
		genotypes.add(color2);
		genotypes.add(color3);

	}

	@Override
	public void update() {
		Genotype color=	genotypes.get(0);
		Genotype color1=	genotypes.get(1);
		Genotype color2=	genotypes.get(2);
		if(done==false) {
			self.setBodyColor(new Color(color.get(0),color.get(1),color.get(2))); 
			self.setRadarColor(new Color(color1.get(0),color1.get(1),color1.get(2)));
			self.setGunColor(new Color(color2.get(0),color2.get(1),color2.get(2)));
			done=true;
		}
		
	}
}

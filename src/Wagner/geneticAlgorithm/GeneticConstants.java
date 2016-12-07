package Wagner.geneticAlgorithm;

import java.util.ArrayList;

import Wagner.advanced.MegaRobot;
import Wagner.geneticAlgorithm.modules.GeneticAim;
import Wagner.geneticAlgorithm.modules.GeneticColor;
import Wagner.geneticAlgorithm.modules.GeneticMovement;
import Wagner.geneticAlgorithm.modules.GeneticRadarModule;
import Wagner.utilities.Pointer;

public class GeneticConstants {
	public static int GENOSIZE=10;
	public static double MUTATIONMAG=.7;
	public static double MUTATION_PERCENT=.13;
	public static double CROSS_PERCENT=.5;
	public static Pointer<MegaRobot> ME;
	
	
	public static ArrayList<Organ> CloneOrgans(Organism a){
		ArrayList<Organ> novo= new ArrayList<Organ>() ;
		Organ novoO = null;
		for(Organ o: a.organs) {
			if( o instanceof GeneticMovement ) {
				novoO= new GeneticMovement (ME);
			}
			if(o instanceof GeneticAim ) {
				novoO= new GeneticAim (ME);
			}
			if(o instanceof GeneticColor ) {
				novoO= new GeneticColor (ME);
			}
			if(o instanceof GeneticRadarModule ) {
				novoO= new GeneticRadarModule (ME);
			}
			novo.add(novoO);
		}
		
			return novo;
	}
}

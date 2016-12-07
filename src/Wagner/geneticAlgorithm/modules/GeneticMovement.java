package Wagner.geneticAlgorithm.modules;

import Wagner.advanced.MegaRobot;
import Wagner.advanced.Module;
import Wagner.geneticAlgorithm.Gene;
import Wagner.geneticAlgorithm.GeneSimple;
import Wagner.geneticAlgorithm.GeneticConstants;
import Wagner.geneticAlgorithm.Genotype;
import Wagner.geneticAlgorithm.Organ;
import Wagner.utilities.Calc;
import Wagner.utilities.Coord;
import Wagner.utilities.Pointer;
import Wagner.utilities.Tank;

public class GeneticMovement extends Organ{
	private int WALL = 20;
	private boolean turning=true;
	private int still=0;
	public GeneticMovement(Pointer<MegaRobot> a) {
		super(a);
		
	}
	public GeneticMovement(MegaRobot a, String gene) {
		super(a,gene);
	}
	public GeneticMovement(MegaRobot a) {
		super(a);
	}
	@Override
	public void reset() {
		
		// TODO Auto-generated method stub
		GeneSimple distance = new GeneSimple(0, 700);// -500 500 com 120 0
		Genotype foward = new Genotype(distance, GeneticConstants.MUTATIONMAG,
				GeneticConstants.MUTATION_PERCENT, GeneticConstants.GENOSIZE);// magnitude,
																				// Mutation
		GeneSimple turnG = new GeneSimple(-10, 120);// -40 +40 com 5 0;
		Genotype turn = new Genotype(turnG, GeneticConstants.MUTATIONMAG,
				GeneticConstants.MUTATION_PERCENT, GeneticConstants.GENOSIZE);
		genotypes.add(foward);
		genotypes.add(turn);
	}

	@Override
	public void update() {
		Tank target = null;
		for (Tank a : MegaRobot.enemies) {
			if (a.alive) {
				target = a;
				break;
			}
		}
		if (target == null)
			return;
		
		moveFoward(target);
	}

	private void moveFoward(Tank target) {
		
			Genotype distance=genotypes.get(0);
			
			distance.advanceCurrent();
		
			Coord dest = new Coord(0, 0);
			dest = rotate(turning, target,distance.getCurrent());
			moveToXY(dest);	
			
			still--;
			if(still<=0) {
				Genotype turn= genotypes.get(1);
				turn.advanceCurrent();
				still=turn.getCurrent();
				turning=!turning;
			}
		
		
		
	}

	



	protected void moveToXY(Coord a) {
		double angle = Calc.turntoxy(info.tankHeading, info.coord, a);
		double distance = Calc.pointDistance(info.coord, a);

		if (angle > Calc.pi / 2 || angle < -Calc.pi / 2) {
			angle = Calc.shortestAngle(angle);
			distance *= -1;
		}

		if (Math.abs(angle) > 0.1)
			self.setTurnLeftRadians(angle);
		if (Math.abs(distance) > 5)
			self.setAhead(distance);
	}
	private Coord rotate(boolean turn, Tank target,double raio) {

		double angle = Calc.invertAngle(target.angle);
		double angleH = target.tankHeading;
		if (turn) {
			angle = angle - Calc.pi / 8;
		} else {
			angle = angle + Calc.pi / 8;
		}

		Coord dest = Calc.moveCoord(target.coord, angle, raio);
		if (dest.x > target.map.x - WALL)
			dest.x = target.map.x - WALL;
		if (dest.y > target.map.y - WALL)
			dest.y = target.map.y - WALL;
		if (dest.x < WALL)
			dest.x = WALL;
		if (dest.y < WALL)
			dest.y = WALL;
		return dest;
	}
	

	@Override
	public double getFitness() {
		// TODO Auto-generated method stub
		return fitness;
	}

	

}

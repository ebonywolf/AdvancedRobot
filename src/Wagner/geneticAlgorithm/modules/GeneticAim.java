package Wagner.geneticAlgorithm.modules;

import java.awt.Color;
import java.awt.Graphics2D;

import Wagner.advanced.MegaRobot;

import Wagner.geneticAlgorithm.GeneSimple;
import Wagner.geneticAlgorithm.GeneticConstants;
import Wagner.geneticAlgorithm.Genotype;
import Wagner.geneticAlgorithm.Organ;
import Wagner.utilities.Calc;
import Wagner.utilities.Coord;
import Wagner.utilities.Pointer;
import Wagner.utilities.Tank;

public class GeneticAim extends Organ {

	private boolean aiming = false;

	public GeneticAim(Pointer<MegaRobot> a) {
		super(a);
		fitness = 0;

	}

	public GeneticAim(MegaRobot a, String gene) {
		super(a, gene);
	}

	public GeneticAim(MegaRobot a) {
		super(a);

	}

	@Override
	public void reset() {
		GeneSimple aimG = new GeneSimple(-1100, 1100);// 10 0s
		Genotype aim = new Genotype(aimG, GeneticConstants.MUTATIONMAG,
				GeneticConstants.MUTATION_PERCENT, GeneticConstants.GENOSIZE);
		GeneSimple bulletG = new GeneSimple(50, 330);// 0.5-3
		Genotype bullet = new Genotype(bulletG, GeneticConstants.MUTATIONMAG,
				GeneticConstants.MUTATION_PERCENT, GeneticConstants.GENOSIZE);
		genotypes.add(aim);

		genotypes.add(bullet);
	}

	@Override
	public void update() {
		shoot();

	}

	private void aim(Tank target) {
		Genotype aim = genotypes.get(0);
		Genotype bullet = genotypes.get(1);
		if (Math.abs(self.getGunTurnRemainingRadians()) == 0) {
		
			if (self.getGunTurnRemaining() <= 0) {
				
				aiming = true;
				aim.advanceCurrent();
				bullet.advanceCurrent();
				double power = bullet.getCurrent() / 100;
				double enemySpeed=8*Math.sin(Calc.pi+target.angle-target.tankHeading);
				double turnAngle = Calc.turntoxy(info.gunHeading, info.coord,
						target.coord);
				double positiveEscape =0;
				if(target.speed!=0) positiveEscape =Math.asin(enemySpeed / (20 - 3 * power)) * 1.1;
				if (target.speed < 0) {
					positiveEscape *= -1;
				}
				double negativeEscape = positiveEscape * -1 * 0.6;
				//positiveEscape=checkBorder(positiveEscape, target);
				//negativeEscape=checkBorder(negativeEscape, target);
				
			
				double aimAngle = Math.abs(aim.getCurrent()) - 100;
				
				if (aimAngle > 0) {
					if (aim.getCurrent() > 0) {
						aimAngle = (double) positiveEscape * aimAngle / 1000;
						
						self.setTurnGunLeftRadians(turnAngle+aimAngle);
					
					} else {
						aimAngle = (double) negativeEscape * aimAngle / 1000;
						self.setTurnGunLeftRadians(turnAngle+aimAngle);
					}
					
				}else {
					self.setTurnGunLeftRadians(turnAngle);
					
				}
				

			}
			if (self.getGunHeat() <= 0) {
				bullet.advanceCurrent();
			}
			self.setFire(bullet.getCurrent() / 100);
		}
	}

	private double checkBorder(double angle,Tank target) {
		double ang=target.angle+angle;
	
		double distance=target.distance*1.5;

		
		
		double x=info.coord.x+distance*Math.cos(ang);
		double y=info.coord.y+distance*Math.sin(ang);
	
		
		Graphics2D g= self.getGraphics();
		g.setColor(Color.BLUE);
		
	
		int error=10;
		if(x<error)x=error;
		if(y<error)y=error;
		if(x>target.map.x-error)x=target.map.x-error;
		if(y>target.map.y-error)y=target.map.y-error;
		g.drawRect((int)x, (int)y, 30, 30);
		g.fillRect((int)x, (int)y, 30, 30);
		return Calc.findangle(info.coord, new Coord(x,y))-target.angle;
	}
	private void shoot() {

		Tank target = null;
		for (Tank a : MegaRobot.enemies) {
			if (a.alive) {
				target = a;
				break;
			}
		}
		if (target == null)
			return;
		aim(target);

	}

	@Override
	public double getFitness() {
		// TODO Auto-generated method stub
		return fitness;
	}

}

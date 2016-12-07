package Wagner.geneticAlgorithm.modules;

import java.awt.Graphics2D;

import Wagner.advanced.MegaRobot;
import Wagner.geneticAlgorithm.Organ;
import Wagner.utilities.Calc;
import Wagner.utilities.Pointer;
import Wagner.utilities.Tank;

public class GeneticRadarModule extends Organ{
	
	public GeneticRadarModule(Pointer<MegaRobot> a) {
		
		super(a);
		// TODO Auto-generated constructor stub
	}

	public Tank target;
	private boolean state;

	

	@Override
	public void update() {
		turnRadar();

	}

	private void turnRadar() {
		if (!state) {
			if (self.getOthers() > 1) {
				self.setTurnRadarLeftRadians(Calc.pi / 2);
			} else {
				for (Tank a : self.enemies) {
					if (a.alive) {
						target = a;
						state = true;
					}
				}
			}
		}
		if (state) {
			Tank target2 =target;;
			if (target.mod > 10) {
				self.setTurnRadarLeftRadians(Calc.pi * 2);
				
			} else {
				
				double angle = Calc.turntoxy(
						Calc.convertangle(self.getRadarHeadingRadians()),
						info.coord, target2.coord);
			
				self.setTurnRadarLeftRadians(angle);
			

			}
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}

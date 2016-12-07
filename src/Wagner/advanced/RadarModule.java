package Wagner.advanced;

import java.awt.Graphics2D;

import Wagner.utilities.Calc;
import Wagner.utilities.Tank;

public class RadarModule extends Module {
	public Tank target;
	private boolean state;

	public RadarModule(MegaRobot self) {
		super(self);
		state = false;
	}

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
						System.out.println(a.name+"  "+a.alive);
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
}

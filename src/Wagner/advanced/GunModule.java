package Wagner.advanced;

import java.awt.Color;
import java.awt.Graphics2D;

import Wagner.utilities.Calc;
import Wagner.utilities.RoboCalc;
import Wagner.utilities.Tank;

public class GunModule extends Module {
	private double shot = 1.5;
	private double DEFAULT = 2;
	private int ERROR = 2;
	private int LIMIT = 100;
	private boolean aiming = false;

	public GunModule(MegaRobot self) {
		super(self);

	}

	@Override
	public void update() {

		Tgun();

	}

	private void Tgun() {
		if (self.enemies.size() > 0) {
			int j;
			for (j = 0; j < self.enemies.size(); j++) {
				if (self.enemies.get(j).alive)
					break;
			}
			Tank target = self.enemies.get(j);
		
			double distance = target.distance;
			for (Tank a : self.enemies) {
				double temp = a.distance;
				if (temp < distance && a.alive) {
					distance = temp;
					target = a;
				}
			}
			
			
			if (!aiming) {
				aiming = true;
				shot = DEFAULT;
				if (target.distance < 350)
					shot = 2;
				if (target.distance < 150)
					shot = 3;
				if (info.energy < 30)
					shot = 1;
				if (target.distance < 100)
					shot = 3;
				if (info.energy < 10)
					shot = .5;
				if (info.energy < 5)
					shot = .1;
				if (target.distance < 50)
					shot = 3;

				double time = 0;
				target = target.clone();
			
				double time2 = 0;
				double temp = RoboCalc.timeToDest(target.coord, info.coord,
						shot);
				Graphics2D g = self.getGraphics();
				g.setColor(Color.blue);
			
				for (int i = 0; i < LIMIT; i++) {
					g.drawRect((int)target.coord.x-10, (int)target.coord.y-10,20,20);
					time2 = RoboCalc.timeToDest(target.coord, info.coord, shot);
					if (Math.abs(time - time2) < ERROR) {

						break;
					}
					target.updateT();
					time++;
				}
				
			
				g.setColor(Color.red);
				g.drawRect((int)target.coord.x-10, (int)target.coord.y-10,20,20);
				
				self.setTurnGunLeftRadians(Calc.turntoxy(info.gunHeading,
						info.coord, target.coord));

			}
			if (Math.abs(self.getGunTurnRemaining()) == 0 || Double.isNaN(Math.abs(self.getGunTurnRemaining()))) {
				aiming = false;
			}
			
			
			self.setFire(shot);
		
		}
	}

}

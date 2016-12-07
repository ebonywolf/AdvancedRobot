package Wagner.utilities;

import java.util.ArrayList;

import Wagner.advanced.Statistic;

import robocode.AdvancedRobot;
import robocode.Robot;
import robocode.ScannedRobotEvent;

public class TankOld {
	public double gunHeading, radarHeading, tankHeading, headingSpeed, speed,
			acceleration, energy, headingAccel;

	public String name;
	public Coord coord;
	public double angle, distance;
	public boolean alive;
	public int mod;
	public Coord map;

	public TankOld() {
		map = new Coord(1, 1);
		gunHeading = 0;
		radarHeading = 0;
		tankHeading = 0;
		alive = true;
		speed = 0;
		acceleration = 0;
		energy = 0;
		mod = 1;
		coord = new Coord(0, 0);

	}

	public void update(AdvancedRobot a) {
		energy = a.getEnergy();
		coord.x = a.getX();
		coord.y = a.getY();
		gunHeading = Calc.convertangle(a.getGunHeadingRadians());
		radarHeading = Calc.convertangle(a.getRadarHeadingRadians());
		double temp2 = tankHeading;
		tankHeading = Calc.convertangle(a.getHeadingRadians());
		headingSpeed = tankHeading - temp2;
		double temp = a.getVelocity();
		acceleration = (temp - speed) / mod;
		speed = temp;
		mod = 1;
	}

	public void update() {
		mod++;
	}

	public void update(ScannedRobotEvent e, Robot self) {
		/**------------- COORDENATES --------   */
		distance = e.getDistance();
		double an = self.getHeading() + e.getBearing();
		an = an * Math.PI / 180;
		angle = Calc.convertangle(an);
		coord.x = (self.getX() + e.getDistance() * Math.sin(an));
		coord.y = (self.getY() + e.getDistance() * Math.cos(an));

		/**------------- HEADING --------   */
		double lastHeadingspeed = headingSpeed;
		double lastTankHeading = tankHeading;
		tankHeading = Calc.convertangle(e.getHeadingRadians());
		if (Math.abs(tankHeading - lastTankHeading) < .3) // /loop error check
			headingSpeed = tankHeading - lastTankHeading;
		headingAccel = (headingSpeed - lastHeadingspeed) / mod;

		
		/**------------- ACCELERATION --------   */
		acceleration = (e.getVelocity() - speed) / mod;
		double temp3 = acceleration;
		
		

		speed = e.getVelocity();
		
		mod = 0;
	}
	public TankOld clone() {
		TankOld a = new TankOld();
		a.acceleration = this.acceleration;
		a.map = this.map;
		a.alive = this.alive;
		a.coord.x = this.coord.x;
		a.coord.y = this.coord.y;
		a.energy = this.energy;
		a.gunHeading = this.gunHeading;
		a.headingSpeed = this.headingSpeed;
		a.mod = this.mod;
		a.name = this.name;
		a.radarHeading = this.radarHeading;
		a.speed = this.speed;
		a.tankHeading = this.tankHeading;
		a.headingAccel = this.headingAccel;
		return a;
	}

}

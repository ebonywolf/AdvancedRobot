package Wagner.utilities;

import java.util.ArrayList;

import Wagner.advanced.Statistic;

import robocode.AdvancedRobot;
import robocode.Robot;
import robocode.ScannedRobotEvent;

public class Tank {
	public double gunHeading, radarHeading, tankHeading, headingSpeed, speed,
			acceleration, energy, headingAccel;

	public String name;
	public Coord coord;
	public double angle, distance;
	// public double reverseProb[] = new double[] { 0, 0, 0 };//
	// prob,occurrence,total
	// public double turnProb[] = new double[] { 0, 0, 0, 0 };//
	// prob,occurrence,total,media
	public Statistic<Integer> reverseProb = new Statistic<Integer>(15);
	public int reverseCounter = 0;
	public Statistic<Integer> turnProb = new Statistic<Integer>(15);
	public int turnCounter = 0;

	private boolean lastHeading = false;

	public boolean alive;
	public int mod;
	public Coord map;

	public Tank() {
		map = new Coord(1, 1);
		gunHeading = 0;
		radarHeading = 0;
		tankHeading = 0;
		alive = true;
		speed = 0;
		headingAccel=0;
		headingSpeed=0;
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

	public void updateT() {
		tankHeading += headingSpeed;
		while (tankHeading - Calc.pi * 2 > 0)
			tankHeading -= Calc.pi * 2;
		speed += acceleration;
		if (speed > 8)
			speed = 8;
		if (speed < -8)
			speed = -8;
		
		if (headingSpeed > Calc.degreeToRadian(10 - 0.75 * Math.abs(speed))) {
			headingSpeed = Calc.degreeToRadian(10 - 0.75 * Math.abs(speed));
			headingAccel = 0;
		}
		if (headingSpeed < -Calc.degreeToRadian(10 - 0.75 * Math.abs(speed))) {
			headingSpeed = - Calc.degreeToRadian(10 - 0.75 * Math.abs(speed));
			headingAccel = 0;
		}
		

		coord.x = coord.x + speed * Math.cos(tankHeading);
		coord.y = coord.y + speed * Math.sin(tankHeading);
		if (coord.x > map.x - 20)
			coord.x = map.x - 20;
		if (coord.y > map.y - 20)
			coord.y = map.y - 20;
		if (coord.x < 20)
			coord.x = 20;
		if (coord.y < 20)
			coord.y = 20;
		
		if(reverseCounter<=0 && reverseProb.size()>0) {
			if (speed > 0) acceleration =-2;
			if (speed < 0) acceleration = 2;
			reverseCounter=reverseProb.getRandom();
		}
		reverseCounter--;
		double temp=Calc.degreeToRadian(10 - 0.75 * Math.abs(speed));
		if(turnCounter<=0 && turnProb.size()>0) {
			if(headingSpeed>0)headingAccel=-(Calc.degreeToRadian(10 - 0.75 * Math.abs(speed)));
			if(headingSpeed<0)headingAccel=(Calc.degreeToRadian(10 - 0.75 * Math.abs(speed)));
		
			turnCounter=turnProb.getRandom();
		}
		turnCounter--;
		
	headingSpeed+=headingAccel;

	}

	public void update(ScannedRobotEvent e, Robot self) {
		alive=true;
		/** ------------- COORDENATES -------- */
		distance = e.getDistance();
		double an = self.getHeading() + e.getBearing();
		an = an * Math.PI / 180;
		angle = Calc.convertangle(an);
		coord.x = (self.getX() + e.getDistance() * Math.sin(an));
		coord.y = (self.getY() + e.getDistance() * Math.cos(an));

		/** ------------- HEADING -------- */
		double lastHeadingspeed = headingSpeed;
		double lastTankHeading = tankHeading;
		tankHeading = Calc.convertangle(e.getHeadingRadians());
		if (Math.abs(tankHeading - lastTankHeading) < .3) // /loop error check
			headingSpeed = tankHeading - lastTankHeading;
		headingAccel = (headingSpeed - lastHeadingspeed) / mod;

		/** ------------- ACCELERATION -------- */
		acceleration = (e.getVelocity() - speed) / mod;
		double temp3 = acceleration;

		/** ------------- revereProb -------- */
		reverseCounter++;
		if (temp3 * acceleration == 4) {// checks if the tank reversed direction
			reverseProb.add(reverseCounter);
			reverseCounter = 0;
		}
		

		/** ------------- TurnProb -------- */
		turnCounter++;
		if (headingSpeed * (speed * -1) > 0 && !lastHeading) {
			turnProb.add(turnCounter);
			turnCounter = 0;
			if (temp3 * acceleration == 4) {
				turnProb.remove();
				turnProb.remove();
			}

			lastHeading = true;
		} else {
			if (headingSpeed * (speed * -1) < 0 && lastHeading) {
				turnProb.add(turnCounter);
				lastHeading = false;
				if (temp3 * acceleration == 4)
					turnProb.remove();
				turnProb.remove();
			}
		}
		
		speed = e.getVelocity();
		mod = 0;
	}

	public Tank getT(int n) {
		Tank a = this.clone();
		for (int i = 0; i < n + mod - 1; i++) {
			a.updateT();
		}
		return a;
		

	}

	public Tank clone() {
		Tank a = new Tank();
		a.acceleration = this.acceleration;
		a.reverseProb = this.reverseProb;
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
		a.turnProb = this.turnProb;
		a.turnCounter=turnCounter;
		a.reverseCounter=reverseCounter;
		return a;
	}

}

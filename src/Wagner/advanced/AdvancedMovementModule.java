package Wagner.advanced;

import java.awt.Color;
import java.awt.Graphics2D;

import Wagner.utilities.Calc;
import Wagner.utilities.Coord;
import Wagner.utilities.Tank;

public class AdvancedMovementModule extends Module {
	
	private boolean moving;
	private int angle[]= {300,50};
	private int shotCount=0;
	private int cont=0;
	private int selected=0;
	private boolean direction=false;

	public AdvancedMovementModule(MegaRobot self) {
		super(self);
		moving = false;

	}
	public void getHit() {
		shotCount++;
		if(shotCount>1) {
			angle[0]=Calc.random(300);
			angle[1]=Calc.random(300);
			shotCount=0;
			self.stop();
			direction=!direction;
		}
	}

	public void hitWall() {
		self.stop();
		direction=!direction;
	}
	private void changeDir() {
		cont++;
		if(cont>=angle[selected]) {
			self.stop();
			direction=!direction;
			selected=(selected+1)%2;
			cont=0;
		}
	}

	@Override
	public void update() {
		move();
		changeDir();
		
	
	}

	private void move() {
		if (self.enemies.size() > 0) {
			Tank target=getTarget();
			double angle=target.angle+Calc.pi/2;
			
			if(target.distance>500){
				double mod=Calc.pi/6;
				if(!direction)mod*=-1;
				if(angle<0)angle-=mod;else
					if(angle>0)angle+=mod;
			}
			if(target.distance<100){
				double mod=Calc.pi/6;
				if(!direction)mod*=-1;
				if(angle<0)angle+=mod;else
					if(angle>0)angle-=mod;
			}
			
			double temp=Calc.turntoangle(info.tankHeading, angle);
			self.setTurnLeftRadians(temp);
			int distance=500;
			if(direction)distance*=-1;
	
			self.setAhead(distance);
			
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
	private Tank getTarget() {
		int i;
		for(i=0;i<self.enemies.size();i++) {
			if(self.enemies.get(i).alive)break;
		}
		Tank target = self.enemies.get(i);
		double distance =  target.distance;
		for (Tank a : self.enemies) {
			double temp =  a.distance;
			if (temp < distance && a.alive) {
				distance = temp;
				target = a;
			}
		}
		return target;
	}

	

}

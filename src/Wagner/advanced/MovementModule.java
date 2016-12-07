package Wagner.advanced;

import java.awt.Color;
import java.awt.Graphics2D;

import Wagner.utilities.Calc;
import Wagner.utilities.Coord;
import Wagner.utilities.Line;
import Wagner.utilities.Tank;

public class MovementModule extends Module {
	static private int ERROR = 20;

	public Coord destination;
	public Line<Coord> pathway;
	private boolean moving;
	
	private int shotCounts[]= {50,50,50,50,50,50,50,50};
	private int shotCount=0;
	private int vectorCount=0;
	
	private int RAIO = 500;
	private int WALL = 75;
	private int cont =0;
	private int LIMIT=80;
	private boolean DIRECTION = false;

	public MovementModule(MegaRobot self) {
		super(self);
		destination = new Coord(0, 0);
		pathway = new Line<Coord>();
		moving = false;

	}

	public void setDestination(Coord a) {
		pathway.clear();
		pathway.add(a);
		destination = a;
	}

	public void move() {
		if (!pathway.empty()) {
			if (pathway.get().equals(info.coord, ERROR)) {
				pathway.remove();
				moving = false;
				move();
				return;
			} else {
				if (info.speed == 0 && moving) {
					moving = false;
				}
				moveToXY(pathway.get());
				moving = true;
			}
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

	@Override
	public void update() {
		calculateRoute();
		move();
	}
	public void getHit() {
		LIMIT=(int) (Math.random()*90);
		shotCounts[vectorCount]=shotCount;
		shotCount=0;
		vectorCount++;
		vectorCount%=8;
	}

	private void calculateRoute() {
		if (self.enemies.size() > 0) {
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

			Coord dest = new Coord(0, 0);
			dest = rotate(DIRECTION, target);
			
			if (Calc.random(90) == 1) { 
				DIRECTION = !DIRECTION;
				cont=0;
			}
		
			if (Calc.pointDistance(dest, info.coord) < 40) {
				DIRECTION = !DIRECTION;
				cont=0;
			}
			cont++;
			shotCount++;
			int media=0;
			for(int j=0;j<shotCounts.length;j++) {
				media+=shotCounts[j];
			}
			if(Calc.random(50)>45) {
				media/=shotCounts.length;
				if(cont+5>media) {
					DIRECTION = !DIRECTION;
					cont=0;
					
				}
			}
			
			if(cont>LIMIT) {
				DIRECTION = !DIRECTION;
				cont=0;
			}
			
			setDestination(dest);

		}

	}

	private Coord rotate(boolean turn, Tank target) {

		double angle = Calc.invertAngle(target.angle);
		double angleH = target.tankHeading;
		if (turn) {
			angle = angle - Calc.pi / 8;
		} else {
			angle = angle + Calc.pi / 8;
		}

		Coord dest = Calc.moveCoord(target.coord, angle, RAIO);
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

}

package Wagner.advanced;

import Wagner.utilities.Calc;
import Wagner.utilities.Shot;
import Wagner.utilities.Shots;
import Wagner.utilities.Tank;

public class VirtualGuns extends Shots{
	Tank self;

	private double oldEnergy;
	VirtualGuns(Tank a,Tank self) {
		super(a);
		this.self=self;
		// TODO Auto-generated constructor stub
	}
	public void update() {
		super.update();
		double p=oldEnergy-enemy.energy;
		if(p<=3 && enemy.mod==1) {
			calculateHeading();
			shots.add(new Shot(p,enemy.gunHeading,enemy.coord));
		}
		
	}
	private void calculateHeading() {
		enemy.gunHeading=Calc.convertangle(enemy.angle);
	}
	private void checkCollision() {
		
	}
	

}

package Wagner.utilities;

public class Shot {
	public double power,speed,angle;
	public Coord coord,origin;
	public Shot(double power, double angle, Coord origin) {
		this.power=power;
		this.angle=angle;
		this.origin=origin;
		coord=origin;
		speed=20-power*3;
				
	}
	public void update() {
		coord=Calc.moveCoord(coord, angle, speed);
	}
	public Shot getT(int n) {
		Shot a=this.clone();
		a.coord=Calc.moveCoord(a.coord, a.angle, speed*n);
		return a;
	}
	public Shot getTDistance(double m) {
		Shot a=this.clone();
		int n=(int) (m/speed);
		a.coord=Calc.moveCoord(a.coord, a.angle, speed*n);
		return a;
	}
	
	public Shot clone() {
		Shot a=new Shot(power,angle,origin);
		a.coord=coord;
		return a;
	}
	
	
	
}

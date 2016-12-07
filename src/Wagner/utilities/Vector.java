package Wagner.utilities;

public class Vector {
	public double x;
	public double y;
	public double angle;
	public double speed;
	Vector(Coord a,Coord b,double speed){
		x=b.x-a.x;
		y=b.y-a.y;
		this.speed=speed;
		angle=Calc.findangle(a, b);
		x=speed*Math.cos(angle);
		y=speed*Math.sin(angle);
	}
	public Vector(double angle, double speed){
		this.angle=angle;
		setSpeed(speed);
	}
	public  Vector(Coord a){
		this.x=a.x;
		this.y=a.y;
		calculateAngle();
		calculateSpeed();
	}
	Vector(){
		
	}
	public void setSpeed(double Speed) {
		this.speed=speed;
		x=speed*Math.cos(angle);
		y=speed*Math.sin(angle);
	}
	public void calculateAngle() {
		angle=Math.atan(y/x);
		if(x<0)angle=Calc.invertAngle(angle);
	}
	public void calculateSpeed() {
		 speed = Math.pow(
				Math.pow(x, 2) + Math.pow(y, 2), 0.5);
		
	}
	public Vector add(Vector b) {
		Vector result=new Vector();
		result.x=this.x+b.x;
		result.y=this.y+b.y;
		result.calculateAngle();
		result.calculateSpeed();
		return result;
	}
	
}

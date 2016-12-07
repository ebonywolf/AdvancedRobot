package Wagner.utilities;


public class RoboCalc {
	public static double timeToDest(Coord dest,Coord start,double shot) {
		double speed=20-3*shot;
		double time=Calc.pointDistance(dest, start);
		time/=speed;
		time+=2;
		return time;
		
	}

}

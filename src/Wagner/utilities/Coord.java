package Wagner.utilities;

public class Coord {
	public double x;
	public double y;
	public Coord(double x,double y){
		this.x=x;
		this.y=y;
	}
	public boolean equals(Coord a, double error) {
		boolean b=true;
		if(Math.abs(this.x-a.x)>error) {
			b=false;
		}
		if(Math.abs(this.y-a.y)>error) {
			b=false;
		}
		return b;
	}

}

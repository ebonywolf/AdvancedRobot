package Wagner.utilities;

import robocode.util.Utils;

public class Calc {
	static public double pi=Math.PI;
	static public double convertangle(double angle){
		angle=2*pi-angle+pi/2;
		while(angle-2*pi>0) {
			angle-=2*pi;
		}
	
		return angle;
	}
	static public void shuffleArray(int[] ar) {
		for (int i = ar.length - 1; i > 0; i--) {
			int index = Calc.random(i + 1);
			// Simple swap
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}
	static public double invertAngle(double angle) {
		angle+=pi;
		while(angle-2*pi>0) {
			angle-=2*pi;
		}
		return angle;
	}
	static public double shortestAngle(double angle) {
		boolean sign=false;
		if(angle<0) {
			angle*=-1;
			sign=true;
		}
		
		while(angle-2*pi>0) {
			angle-=2*pi;
		}
		
		if(angle>=pi/2 && angle<=pi) {
			angle= angle-pi;
		}else{
			if(angle>pi&& angle<= 3*pi/2) {
				angle= 3*pi/2-angle;
			}else {
				if(angle>3*pi/2 && angle < 2*pi) {
					angle= angle-2*pi;
				}
			}
		}
		if(sign) {
			angle*=-1;
		}
		return angle;
		
	}
	static public double degreeToRadian(double angle){
		return angle*Math.PI/180;
	}
	static public double radianToDegree(double angle){
		return angle*180/Math.PI;
	}
	static public boolean distanceLessThan(double x1, double x2, double distance){
		if(x1>x2-distance && x1<x2+distance)return true; else
			return false;
	}
	static public Coord moveCoord(Coord start,double angle, double speed) {
		double x=start.x + speed*Math.cos(angle);
		double y=start.y+speed*Math.sin(angle);
		return new Coord(x,y);
	}
	static public double findangle(Coord a, Coord b){
		double x=b.x-a.x;
		double y=b.y-a.y;
		double angle;
		angle=Math.atan(y/x);
		if(angle<0)angle=angle*-1;
		if(x<0 && y>0){
			angle=pi-angle;
		}
		if(x<0 && y<0){
			angle=pi+angle;
		}
		if(x>0 && y<0){
			angle=2*pi-angle;
		}
		
		return angle;
	}
	static public int random(int n){
		double a=Math.random();
		n= (int)(a*n);
		return n;
	}

	
	static public double turntoxy(double angle,Coord a,Coord b){
		double angle1=findangle(a,b);
		
		angle=angle1-angle;
		
		while(angle-2*pi>0) {
			angle-=2*pi;
		}
		if(angle>pi)angle=angle-2*pi;
		if(angle<-pi)angle=2*pi+angle;
		
		return angle;
	}
	static public double turntoangle(double angle1, double angle2){
		return angle2-angle1;
	}
	
	static public double pointDistance(Coord a, Coord b){
		double temp= Math.pow((double)(b.x-a.x),2);
		
		double temp2= Math.pow((double)(b.y-a.y),2);
		temp=Math.pow((double)temp+temp2,0.5);
		return temp;
	}
	

}

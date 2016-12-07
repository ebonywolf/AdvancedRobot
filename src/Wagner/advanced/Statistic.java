package Wagner.advanced;

import java.util.ArrayList;

import Wagner.utilities.Calc;

public class Statistic<T> {
	private ArrayList<T> data;
	private int max;
	private int numb=0;
	
	
	public Statistic(int n){
		max=n;
	
		data=new  ArrayList<T>();
	}
	public void add(T a) {
		numb++;
		if(numb>max) {
			data.remove(0);
			numb=max;
		}
		data.add(a);
	}
	public T getRandom() {
		return data.get(Calc.random(numb));
	}
	public int size() {
		return numb;
	}
	public void remove() {
	
		if(numb>0) {
			numb--;
			data.remove(numb);
		}
	}

	
}

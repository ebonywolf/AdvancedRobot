package Wagner.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TopList<T> {
	ArrayList<TipoComparavel> toplist;
	int size;
	//int amount=0;
	public TopList(int size){
		this.size=size;
		toplist=new ArrayList<TipoComparavel> ();
	
	}
	public void add(TipoComparavel a) {
		int i;
		
		for(i=0;i<size;i++) {
			if(i>=toplist.size()) {
				toplist.add(i,a);
				break;
			}
			if(a.greaterThan(toplist.get(i)) ) {
				toplist.add(i, a);
				break;
			}
		}
		
		if(toplist.size()>size) {
			toplist.remove(toplist.size()-1);
		}
		
	}
	public T get(int n) {
		return (T) toplist.get(n);
	}
	public int size() {
		return toplist.size();
	}
	public void clear() {
		toplist.clear();
	}
	public void shuffle() {
		Collections.shuffle(toplist);
	}
	
}

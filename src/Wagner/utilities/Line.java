package Wagner.utilities;

import java.util.ArrayList;

public class Line<T> {
	ArrayList<T> line;
	public Line(){
		line=new ArrayList<T>();
	}
	public void add(T a) {
		line.add(a);
	}
	public void remove() {
		T a=line.remove(0);
	}
	public T get() {
		return line.get(0);
	}
	public boolean empty() {
		return line.size()==0;
	}
	public int Size() {
		return line.size();
	}
	public void clear() {
		line.clear();
	}

}

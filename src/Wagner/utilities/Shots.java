package Wagner.utilities;

import java.util.ArrayList;


public abstract class Shots {
	public ArrayList<Shot> shots;
	protected  Tank enemy;
	protected Shots(Tank a){
		shots=new ArrayList<Shot>();
		enemy=a;
	}
	public void update() {
		for(Shot a:shots) {
			a.update();
		}
	
	}

	

}

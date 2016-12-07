package Wagner.advanced;

import Wagner.utilities.Tank;

public abstract class Module {
	public MegaRobot self;
	public Tank info;
	
	public Module(MegaRobot a){
		self=a;
		info=a.self;
	}
	abstract public void update();

}

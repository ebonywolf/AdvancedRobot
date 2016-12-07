package Wagner;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public class NovoRobo extends AdvancedRobot {
	public void run() {
		int distance=360;
		while(true) {
			setTurnGunLeft(360);
			ahead(distance);
			if(getDistanceRemaining()<=0) {
				distance*=-1;
				stop();
			}
			execute();
		}
	}
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(2);
	}
}

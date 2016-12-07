package Wagner.advanced;

import java.util.ArrayList;

import Wagner.utilities.Coord;
import Wagner.utilities.Tank;

import robocode.AdvancedRobot;
import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.DeathEvent;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import robocode.StatusEvent;
import robocode.WinEvent;

public abstract class MegaRobot extends AdvancedRobot {
	public Tank self=new Tank();
	protected boolean started=false;
	private boolean radarUpdate=false;
	public ArrayList<Module> statusModules = new ArrayList<Module>();
	public ArrayList<Module> scannedModules= new ArrayList<Module>();
	public static  ArrayList<Tank> enemies = new ArrayList<Tank>();
	private double account=0;
	private double achit=0;
	
	public void run() {
		setAdjustGunForRobotTurn(true); // Keep the gun still when we turn
		setAdjustRadarForGunTurn(true);
		started=true;
	}
	public void onScannedRobot(ScannedRobotEvent e) {
		boolean exists=false;	
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).name.equals(e.getName())) {
				exists = true;
				enemies.get(i).update(e, this);
			}
		}
		if (exists == false) {
			Tank a = new Tank();
			a.update(e, this);
			a.name = e.getName();
			a.map=new Coord(getBattleFieldWidth(),getBattleFieldHeight());
			enemies.add(a);
		
		}
		radarUpdate=true;
		onStatus(null);
		radarUpdate=false;;
		scan();
	}

	public void onStatus(StatusEvent e) {
		if(started) {
			self.update(this);
			for (Module a : statusModules) {
				a.update();
			}
			if(!radarUpdate) {
				for(Tank a:enemies) {
					
					a.update();
				}
			}else {
				for (Module a : scannedModules) {
					a.update();
				}
			}
		}
		
	}

	public void onRobotDeath(RobotDeathEvent e) {
		String s = e.getName();
		for(Tank a:enemies) {
			if(a.name.equals(s)) {
				System.out.println(a.name+"  deleted");
				a.alive=false;
			}
			
		}
	}
	public void onBulletHit(BulletHitEvent e){
		account++;
		achit++;
	}
	public void onBulletMissed(BulletMissedEvent e){
		account++;
	}
	@Override
	public void onWin(WinEvent e) {
		enemies.clear();
		System.out.println("accuracy: "+(achit/account));
	}
	@Override
	public void onDeath(DeathEvent e){
		enemies.clear();
		System.out.println("accuracy: "+(achit/account));
	}

}

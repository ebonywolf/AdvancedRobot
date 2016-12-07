package Wagner;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Wagner.advanced.GunModule;
import Wagner.advanced.MegaRobot;
import Wagner.advanced.MovementModule;
import Wagner.advanced.RadarModule;
import Wagner.utilities.Calc;
import Wagner.utilities.Coord;
import Wagner.utilities.Tank;

import robocode.AdvancedRobot;
import robocode.Bullet;
import robocode.BulletHitBulletEvent;
import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.StatusEvent;

public class Megatron extends MegaRobot {

	private MovementModule mm;
	private RadarModule rm;
	private GunModule gm;

	private boolean shift = false;
	private int shiftCount = -1;

	public void run() {
		self.map=new Coord(getBattleFieldWidth(),getBattleFieldHeight());
		self.name = this.getName();
		
		setBodyColor(new Color(40,40,40));
		setGunColor(new Color(0,0,0));
		setRadarColor(Color.red);
		setScanColor(Color.white);
		setBulletColor(Color.BLACK);

		mm = new MovementModule(this);
		rm = new RadarModule(this);
		gm=new GunModule(this);
		statusModules.add(mm);
		statusModules.add(rm);
		scannedModules.add(gm );
		turnRadarLeft(360);
	
		
		super.run();
	}
	public void onMousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {

			if (!shift) {
				shiftCount = 0;

				mm.setDestination(new Coord(e.getX(), e.getY()));
			}
			if (shift && shiftCount < 10) {
				shiftCount++;
				mm.pathway.add(new Coord(e.getX(), e.getY()));
			}

		} else if (e.getButton() == MouseEvent.BUTTON2) {
			// Button 2: fire power = 2 energy points, bullet color = orange
		}

	}
	@Override
	public void onStatus(StatusEvent e) {
		super.onStatus(e);
		//if(started)rm.update();
		
	}
	private void draw() {
		Graphics2D g = getGraphics();
		g.setColor(Color.blue);
		Tank clone;
		for(Tank a: enemies) {
			if(a.alive) {
				clone=a.clone();
				for(int i=0;i<10;i++) {
					g.drawRect((int)clone.coord.x-10, (int)clone.coord.y-10,20,20);
					
					clone.updateT();
				}
			}
		}
	}
	public void onKeyPressed(KeyEvent e) {
		if (e.getKeyCode() == 16) {// shift
			shift = true;
		}
	}
	public void onKeyReleased(KeyEvent e) {
		if (e.getKeyCode() == 16) {
			shift = false;
		}
	}
	public void onHitByBullet(HitByBulletEvent e) {

	}
	public void onHitRobot(HitRobotEvent e) {
		
	}
	public void onHitWall(HitWallEvent e) {
		
	}
	public void onBulletHitBullet(BulletHitBulletEvent e) {
		
	}
}

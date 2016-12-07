package Wagner;

import java.awt.Color;

import robocode.StatusEvent;

import static robocode.util.Utils.normalAbsoluteAngle;
import static robocode.util.Utils.normalRelativeAngle;

import java.awt.*;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import Wagner.advanced.GunModule;
import Wagner.advanced.MegaRobot;
import Wagner.advanced.MovementModule;
import Wagner.advanced.RadarModule;
import Wagner.utilities.Coord;

public class Controlled extends MegaRobot{
	private RadarModule rm;
	private GunModule gm;
	private MovementModule mm;
	boolean started= false;
	
	int moveDirection;
	int turnDirection;
	double moveAmount;
	int aimX, aimY;
	double firePower;

	public void run() {
		self.map=new Coord(getBattleFieldWidth(),getBattleFieldHeight());
		self.name = this.getName();
		
		setBodyColor(Color.red);
		setGunColor(Color.yellow);
		setRadarColor(Color.red);
		setScanColor(Color.white);
		setBulletColor(Color.BLACK);

		mm = new MovementModule(this);
		rm = new RadarModule(this);
		gm= new GunModule(this);
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		statusModules.add(mm);
		statusModules.add(rm);
		//modules.add(gm);
		started = true;
		turnRadarLeft(360);
		
		

	}
	public void onStatus(StatusEvent e){
	
		if(started) {
			doShit();
			super.onStatus(e);
			
		}
	}
	private void doShit() {
		setAhead(moveAmount * moveDirection);

		moveAmount = Math.max(0, moveAmount - 1);

		setTurnRight(45 * turnDirection); 
		double angle = normalAbsoluteAngle(Math.atan2(aimX - getX(), aimY - getY()));
		setTurnGunRightRadians(normalRelativeAngle(angle - getGunHeadingRadians()));

		if (firePower > 0) {
			setFire(firePower);
		}
	}
	public void onKeyPressed(KeyEvent e) {
		onStatus(null);
		switch (e.getKeyCode()) {
		case VK_UP:
		case VK_W:
			moveDirection = 1;
			moveAmount = Double.POSITIVE_INFINITY;
			break;

		case VK_DOWN:
		case VK_S:
			moveDirection = -1;
			moveAmount = Double.POSITIVE_INFINITY;
			break;
		case VK_RIGHT:
		case VK_D:
			turnDirection = 1;
			break;

		case VK_LEFT:
		case VK_A:
			turnDirection = -1;
			break;
		}
	}

	public void onKeyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case VK_UP:
		case VK_W:
		case VK_DOWN:
		case VK_S:
			moveDirection = 0;
			moveAmount = 0;
			break;

		case VK_RIGHT:
		case VK_D:
		case VK_LEFT:
		case VK_A:
			turnDirection = 0;
			break;
		}
	}

	public void onMouseMoved(MouseEvent e) {
		aimX = e.getX();
		aimY = e.getY();
	}
	public void onMousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			firePower = 3;
			setBulletColor(Color.RED);
		} else if (e.getButton() == MouseEvent.BUTTON2) {
			firePower = 0.5;
			setBulletColor(Color.ORANGE);
		} else {
			firePower = 1.5;
			setBulletColor(Color.YELLOW);
		}
	}
	public void onMouseReleased(MouseEvent e) {
		
		firePower = 0;
	}
	
	public void onPaint(Graphics2D g) {
		g.setColor(Color.RED);
		g.drawOval(aimX - 15, aimY - 15, 30, 30);
		g.drawLine(aimX, aimY - 4, aimX, aimY + 4);
		g.drawLine(aimX - 4, aimY, aimX + 4, aimY);
	}
}

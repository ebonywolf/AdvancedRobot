package Wagner;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import robocode.BattleEndedEvent;
import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.DeathEvent;
import robocode.HitByBulletEvent;
import robocode.RobocodeFileOutputStream;
import robocode.StatusEvent;
import robocode.WinEvent;

import Wagner.advanced.GunModule;
import Wagner.advanced.MegaRobot;
import Wagner.advanced.MovementModule;
import Wagner.advanced.RadarModule;
import Wagner.geneticAlgorithm.GeneticConstants;
import Wagner.geneticAlgorithm.Organ;
import Wagner.geneticAlgorithm.Organism;
import Wagner.geneticAlgorithm.PopulationModule;
import Wagner.geneticAlgorithm.modules.GeneticAim;
import Wagner.geneticAlgorithm.modules.GeneticColor;
import Wagner.geneticAlgorithm.modules.GeneticMovement;
import Wagner.geneticAlgorithm.modules.GeneticRadarModule;
import Wagner.utilities.Coord;
import Wagner.utilities.Pointer;


public class Dumbass extends MegaRobot {
	
	static PopulationModule pm;
	static boolean added=false;
	static double bestHit=0;
	static double bestMove=0;
	static Pointer<MegaRobot> ME=new Pointer<MegaRobot>();
	static GeneticRadarModule rm;
	static double damageDone=0;
	static int shotsHit=1;
	static double damageReceived=0;
	static int shotsDone=1;
	public void run() {
		
		damageDone=0;
		shotsHit=1;
		damageReceived=0;
		shotsDone=1;
		
		ME.object=this;
		GeneticConstants.ME=ME;
		self.map=new Coord(getBattleFieldWidth(),getBattleFieldHeight());
		self.name = this.getName();
		setAdjustGunForRobotTurn(true); // Keep the gun still when we turn
		setAdjustRadarForGunTurn(true);
		rm=new GeneticRadarModule(ME);

		Organism me= new Organism(this);
		me.add(new GeneticMovement(ME));
		me.add(new GeneticAim(ME));
		me.add(new GeneticColor(ME));
		me.add(rm);
		if(!added) {
			pm=new PopulationModule(this, me,5*8,8);
			added=true;
		}
		for(Organism org:pm.population) {
			for(Organ a:org.organs) {
				a.self=this;
				a.info=self;
			}
		}
		statusModules.add(rm);
		statusModules.add(pm);
		
		System.out.println(pm.getCurrent().organs.toString());
		turnRadarLeft(360);
		
		super.run();
	}
	@Override
	public void onStatus(StatusEvent e) {
		
			super.onStatus(e);
	
	}
	public void onBulletHit(BulletHitEvent e){
		shotsHit++;
		shotsDone++;
		double power= e.getBullet().getPower();
		damageDone=damageDone +(power* 4)*1.2;
		if(power>1) {
			damageDone+= 2*(power - 1);
		}
		
	}
	public void onBulletMissed(BulletMissedEvent e){
		damageDone=damageDone-e.getBullet().getPower();
		shotsDone++;
	}
	@Override
	public void onWin(WinEvent e) {
		double accuracy=(double)shotsHit/shotsDone;
		Organism me=pm.getCurrent();
		me.organs.get(1).fitness=damageDone;
		me.organs.get(0).fitness=-damageReceived+200;
		

		System.out.println("this tank:"+(damageDone )+ " "+(-damageReceived+200));
	//	System.out.println("best tank:"+bestHit+ " "+bestMove);
	
		pm.endRound();
		
	}
	public void onHitByBullet(HitByBulletEvent e) {
		double power=e.getBullet().getPower();
		damageReceived=damageDone +power* 4;
		if(power>1) {
			damageReceived+= 2*(power - 1);
		}
	}
	@Override
	public void onDeath(DeathEvent e){
		double accuracy=(double)shotsHit/shotsDone;
		Organism me=pm.getCurrent();
		me.organs.get(1).fitness=damageDone;
		me.organs.get(0).fitness=-damageReceived;
		
		
		System.out.println("this tank:"+(damageDone )+ " "+(-damageReceived));
		pm.endRound();
	}
	public void onKeyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode() == 'P');
		if (e.getKeyCode() == 'P') {// shift
			pm.compileTopten();
		}
		if (e.getKeyCode() == 'R') {// shift
			pm.report();
		}
		
	}
	public void onBattleEnded(BattleEndedEvent event) {
		
	       pm.compileTopten();
	   }



	
}

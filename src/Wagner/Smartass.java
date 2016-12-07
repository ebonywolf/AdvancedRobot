package Wagner;

import Wagner.advanced.MegaRobot;
import Wagner.advanced.RadarModule;
import Wagner.geneticAlgorithm.modules.GeneticAim;
import Wagner.geneticAlgorithm.modules.GeneticColor;
import Wagner.geneticAlgorithm.modules.GeneticMovement;

public class Smartass extends MegaRobot {


private String GeneA="262@#291@#226@#151@#307@#56@#299@#472@#478@#470@#670@#700@#699@#594@#394@#303@#309@#655@#164@#300@#&120@#72@#38@#2@#75@#81@#7@#-10@#47@#-5@#59@#42@#-10@#109@#104@#120@#78@#110@#35@#-6@#&";

private  String GeneB ="-503@#-66@#376@#45@#176@#674@#-661@#-35@#-304@#-525@#-161@#460@#1065@#794@#1100@#114@#-217@#691@#-584@#803@#&50@#175@#111@#63@#50@#88@#50@#208@#51@#228@#84@#50@#274@#50@#62@#109@#172@#119@#168@#50@#&";

private  String GeneC = "120@#74@#12@#&64@#100@#254@#&87@#122@#126@#&";


	public void run() { 
			statusModules.add(new GeneticMovement(this,GeneA));
			statusModules.add(new GeneticAim(this,GeneB));
			statusModules.add(new RadarModule(this));
			statusModules.add(new GeneticColor(this,GeneC));
			setTurnRadarLeft(360);
			super.run();
	}
}

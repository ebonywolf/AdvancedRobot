package Wagner.geneticAlgorithm;

import java.util.ArrayList;

import Wagner.utilities.TopList;
import Wagner.utilities.Writer;

public class SourceGenerator {
	private static String path="C:/temp";
	private boolean writing=false;

	public static void generateSource(TopList<Organism> list) {
		for(int i=0; i<list.size();i++) {
			Organism novo=list.get(i);
			System.out.println( "top"+i+": "+novo.organs.toString()+ "\n");
		}
	}

	

}

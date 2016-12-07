package Wagner.utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Writer {
	
	
	
	
	final static String JARS = new String("C:/robocode/libs/robocode.jar;");
		public static void write(String path,String name,String text){
	        try{
	                FileWriter fstream = new FileWriter(path+"/"+name+".java");
	                BufferedWriter out = new BufferedWriter(fstream);
	                out.write(text);
	                out.close();
	        }catch(Exception e){
	                System.err.println("Error: " + e.getMessage());
	        }
	        
	        // Compile code
	      
	       
	}
		public static void compile(String path) {
			  try {	
		        	String alce="D:\\eclipse\\java32\\bin\\javac.exe -cp " + JARS + " " + path ;
		        	
		                execute(alce);
		        }catch(Exception e){
		                e.printStackTrace();
		        }
		}
	    private static void execute(String command) throws Exception{
        	
            Process process = Runtime.getRuntime().exec(command);
          // System.out.println(command + " stdout:", process.getInputStream());
           // printMsg(command + " stderr:", process.getErrorStream());
            process.waitFor();
            if(process.exitValue() != 0)
                    System.out.println(command + "exited with value " + process.exitValue());
    }	
	    
}

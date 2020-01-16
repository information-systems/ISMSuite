package org.informationsystem.ismsuite.ismsuite.cpntools;

import org.informationsystem.ismsuite.ismsuite.ISMSuiteRunner;
import org.informationsystem.ismsuite.processengine.process.cpntools.CPNModel;

/**
 * Hello world!
 *
 */
public class App extends ISMSuiteRunner
{
    public static void main( String[] args )
    {
        if (args.length < 2) {
        	printDefaultUsage();
        	System.exit(1);
        }
        
        try {
	        start(
	        	CPNModel.getInstance(),
	        	openSpecificationOrExit(args[1]),
	        	openWorldOrExit(args[0])
	        );
        } catch(Exception e) {
        	e.printStackTrace();
        	System.exit(6);
        }
    }
}

package org.informationsystem.ismsuite.itsatrueworld;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.informationsystem.ismsuite.itsatrueworld.controller.Controller;
import org.informationsystem.ismsuite.itsatrueworld.gui.MainWindow;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InvocationTargetException, InterruptedException
    {
    	Controller c = new Controller();
    	if (args.length > 0) {
    		try {
    			File f = new File(args[0]);
				c.open(new FileInputStream(f), f.getAbsolutePath());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
    	}
        MainWindow.invokeUI(c);
    }
}

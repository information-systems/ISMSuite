package org.informationsystem.ismsuite.itsatrueworld;

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
        MainWindow.invokeUI(new Controller());
    }
}

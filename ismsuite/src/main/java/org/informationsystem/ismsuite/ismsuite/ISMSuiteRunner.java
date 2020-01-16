package org.informationsystem.ismsuite.ismsuite;

import java.io.IOException;

import org.informationsystem.ismsuite.ismsuite.model.Controller;
import org.informationsystem.ismsuite.ismsuite.ui.MainFrame;
import org.informationsystem.ismsuite.processengine.process.ProcessModel;
import org.informationsystem.ismsuite.prover.io.ClauseReader;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.specifier.io.SpecificationReader;
import org.informationsystem.ismsuite.specifier.model.Specification;

/**
 * Hello world!
 *
 */
abstract public class ISMSuiteRunner
{
	public static FirstOrderLogicWorld openWorldOrExit(String filename) {
		// First argument is the data model file in TFF format
		try {
			return ClauseReader.buildWorldFromFile(filename);
		} catch (IOException e1) {
			System.out.println("Please provide a reference to a TFF file.");
			System.out.println(e1.getLocalizedMessage());
			System.exit(2);
		}
		
		return null;
	}
	
	public static Specification openSpecificationOrExit(String filename) {
		// Second argument is the specification
		try {
			return SpecificationReader.fromFileName(filename);
		} catch (IOException e1) {
			System.out.println("Please provide a reference to a Specification file.");
			System.out.println(e1.getLocalizedMessage());
			System.exit(3);
		}
		
		return null;
	}
	
	public static void start(ProcessModel model, Specification spec, FirstOrderLogicWorld world) {
		try {
			Controller controller = new Controller(
				model,
				spec,
				world
				);
				
			MainFrame.invokeUI(controller);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(4);
		}

		
	}
	
	public static void printDefaultUsage() {
		System.out.println("Usage: ISMSuite <DataModel> <Specification>");
		System.out.println("Where:\n\t<DataModel> is a file in TFF-format, and");
		System.out.println("\t<Specification> is a specification file");
		
		printCopyright();
	}
	
	public static void printCopyright() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Part of the ISMSuite project");
		System.out.println("  See: www.informationsystem.org/ISMSuite\n");
		System.out.println("(c) 2019-2020 Jan Martijn van der Werf <j.m.e.m.vanderwerf@uu.nl>");
		System.out.println("              Artem Polyvyanyy <artem.polyvyanyy@unimelb.edu.au>");
		System.out.println();
	}

	
}

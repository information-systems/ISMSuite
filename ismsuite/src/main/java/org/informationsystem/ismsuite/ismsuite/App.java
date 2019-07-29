package org.informationsystem.ismsuite.ismsuite;

import java.io.IOException;

import org.informationsystem.ismsuite.ismsuite.model.Controller;
import org.informationsystem.ismsuite.ismsuite.ui.MainFrame;
import org.informationsystem.ismsuite.processengine.process.cpntools.CPNModel;
import org.informationsystem.ismsuite.prover.io.ClauseReader;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.specifier.model.Specification;
import org.informationsystem.ismsuite.specifier.parser.SpecificationReader;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String... args) {
		
		if (args.length < 2) {
			printUsage();
			System.exit(0);
		}
		
		// First argument is the data model file in TFF format
		FirstOrderLogicWorld world = null;
		try {
			world = ClauseReader.buildWorldFromFile(args[0]);
		} catch (IOException e1) {
			System.out.println("Please provide a reference to a TFF file.");
			System.out.println(e1.getLocalizedMessage());
			System.exit(1);
		}
		
		// Second argument is the specification
		Specification spec = null;
		try {
			spec = SpecificationReader.fromFileName(args[1]);
		} catch (IOException e1) {
			System.out.println("Please provide a reference to a Specification file.");
			System.out.println(e1.getLocalizedMessage());
			System.exit(1);
		}
		
		// String specification = "process Philosophers { place Philosopher(p: person) {register p;insert (p) into human;insert (p) into philosopher;}transition newHuman(nu1: person) {register nu1;insert (nu1) into human;}transition newPerson(nu1: person, p: person) {register nu1;insert (nu1) into human;insert (nu1, p) into likes;}transition newPhilosopher(nu1: person) {register nu1;insert (nu1) into human;insert (nu1) into philosopher;}transition Reads(r: person, p: person) {insert (r, p) into likes;}transition Discuss(r: person, p: person) {remove (r, p) from likes;} transition removePhilosopher(p: person) { remove (p) from philosopher; remove (p) from human; deregister p; } }";
		// String datamodel = "tff( all_philosophers_are_human, conjecture, 	! [X: person]: (  philosopher(X) => human(X) )).tff( like_domain_human, conjecture, 	! [X: person, Y: person] : (likes(X,Y) => ( human(X) & human(Y) ) )).tff( all_humans_not_a_philo_like_a_philo, conjecture,	! [X: person] : ( ~( philosopher(X) )  => ( ? [Y: person] : ( likes(X,Y) & philosopher(Y) ) ) ) ).";
		
		try {
			Controller controller = new Controller(
				CPNModel.getInstance(),
				spec,
				world
				);
				
			MainFrame.invokeUI(controller);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	private static void printUsage() {
		System.out.println("Usage: ISMSuite <DataModel> <Specification>");
		System.out.println("Where:\n\t<DataModel> is a file in TFF-format, and");
		System.out.println("\t<Specification> is a specification file");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Part of the ISMSuite project");
		System.out.println("  See: www.informationsystem.org/ISMSuite\n");
		System.out.println("(c) 2019\tJan Martijn van der Werf <j.m.e.m.vanderwerf@uu.nl>");
		System.out.println("\t\tArtem Polyvyanyy <artem.polyvyanyy@unimelb.edu.au>");
	}

	
}

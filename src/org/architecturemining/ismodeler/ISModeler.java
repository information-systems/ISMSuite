package org.architecturemining.ismodeler;

import java.io.FileNotFoundException;

import org.architecturemining.ismodeler.io.ConstraintReader;
import org.architecturemining.ismodeler.io.SpecificationReader;
import org.architecturemining.ismodeler.model.Specification;
import org.architecturemining.ismodeler.model.Constraint;
import org.architecturemining.ismodeler.model.ProcessModel;

import java.util.List;

public class ISModeler {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Syntax: <specification-file> <constraint-file>");
			System.exit(1);
		}
		try {
			Specification spec = SpecificationReader.readFrom(args[0]);
			List<Constraint> constraints = ConstraintReader.readFrom(args[1]);
			
			new ISModeler(spec, constraints, new ProcessModel());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private WorldChanger worldChanger;
	
	// Give it a Petri net as well, and the User Interface
	
	public ISModeler(Specification spec, List<Constraint> constraints, ProcessModel net) {
		worldChanger = new WorldChanger(new VirtualWorld(spec, constraints), net);
		
		// pass on the worldChanger to the UI
	}
}

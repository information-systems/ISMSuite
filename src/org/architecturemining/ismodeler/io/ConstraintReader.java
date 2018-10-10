package org.architecturemining.ismodeler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.architecturemining.ismodeller.model.Constraint;
import org.architecturemining.ismodeller.model.Specification;

public class ConstraintReader {

	public static List<Constraint> readFrom(String filename) throws FileNotFoundException {
		return ConstraintReader.readFrom(new FileInputStream(filename)  );
	}
	
	public static List<Constraint> readFrom(File f) throws FileNotFoundException {
		return ConstraintReader.readFrom(new FileInputStream(f)  );
	}
	
	public static List<Constraint> readFrom(InputStream filename) {
		ArrayList<Constraint> results = new ArrayList<Constraint>();
		
		Scanner scanner = new Scanner(filename);
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			int hO = line.indexOf('(');
			int hL = line.lastIndexOf(')');
			int komma1 = line.indexOf(',');
			int komma2 = line.indexOf(',', komma1+1);
			
			String id = line.substring(hO+1, komma1).trim();
			String constr = line.substring(komma2+1, hL).trim();
			
			results.add(new Constraint(id, id, constr));
		}
		
		return results;
	}
}

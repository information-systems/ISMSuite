package org.architecturemining.ismodeler.proving;

import java.io.IOException;
import java.util.List;

import org.architecturemining.ismodeler.proving.model.ClauseReader;
import org.architecturemining.ismodeler.proving.model.World;

public class Prover {

	public static void main(String... args) {
		
		boolean silent = false;
		boolean printWorld = false;
		String filename = "";
		
		for(String a: args) {
			if (a.equals("--silent")) {
				silent = true;
			} else if (a.equals("--printworld")) {
				printWorld = true;
			} else {
				filename = a;
			}
		}
		
		if (filename.isEmpty()) {
			System.out.println("Usage: Prover <filename>");
			System.out.println("Where <filename> is a file in TFF-format");
			System.out.println();
			System.out.println("Options:");
			System.out.println("\t--silent     no output is printed.");
			System.out.println("\t--printworld prints the world (default off)");
			System.out.println();
			System.out.println("Return states: ");
			System.out.println("\t0: Proof found");
			System.out.println("\t1  No proof found");
			System.out.println("\t3  No file name given");
			System.out.println("\t4  Generic error.");
			System.out.println();
			System.out.println("This prover is part of the ISModeler project");
			System.out.println("  See: www.architecturemining.org/tools/DPS");
			System.out.println();
			System.out.println("(c) 2018, Jan Martijn van der Werf <j.m.e.m.vanderwerf@uu.nl>");

			System.exit(3);
			return;
		}
		
		try {
			World world = ClauseReader.buildWorldFromFile(filename);
			
			if (printWorld) {
				System.out.println(world.toString());
			}
			
			List<String> invalid = world.invalidates();
			
			if (invalid.isEmpty()) {
				if (!silent) { 
					System.out.println("Proof found!");
				}
				System.exit(0);
				return;
			} else {
				if (!silent) {
					System.out.println("I could not find a proof for the following conjectures:");
				}
				for(String s: invalid) {
					if (!silent) {
						System.out.print(" *  ");
					}
					System.out.println(s);
				}
				System.exit(1);
				return;
			}
			
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.exit(4);
	}
	
}

package org.informationsystem.ismsuite.modeler.simulator;

import org.informationsystem.ismsuite.ismsuite.model.Controller;
import org.informationsystem.ismsuite.ismsuite.ui.MainFrame;
import org.informationsystem.ismsuite.modeler.process.simulator.BasicPNIDSimulator;
import org.informationsystem.ismsuite.modeler.process.simulator.exceptions.InvalidPNID;
import org.informationsystem.ismsuite.modeler.process.simulator.exceptions.UnknownNetType;
import org.informationsystem.ismsuite.pnidprocessor.PNIDModel;
import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.specifier.model.Specification;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class Simulator extends BasicPNIDSimulator {

	public Simulator(PetriNet petrinet, Specification specification, FirstOrderLogicWorld world) throws UnknownNetType, InvalidPNID {
		super(petrinet);
		
		ISMProcessModel model = new ISMProcessModel(this);
		
		Controller controller = new Controller(
				model,
				specification,
				world
				);
		
		MainFrame window = new MainFrame(controller, false); 
		window.setVisible(true);
		// Add a listener on closing the window, so that this ePNK Application is also closed.
		window.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
		        System.out.println("Uncomment following to open another window!");
		        e.getWindow().dispose();
		        System.out.println("JFrame Closed!");
		    }
		});
	}

	private class ISMProcessModel extends PNIDModel {
		
		Simulator simulator;

		public ISMProcessModel(Simulator simulator) {
			super(simulator.getEngine().getMarkedPetriNet());
			this.simulator = simulator;
		}
		
		@Override
		public boolean fire(Binding binding) {
			boolean result = this.getPetriNet().fire(binding);
			simulator.generateCurrentAnnotations();
			
			return result;
		}

		
	}
}

package org.informationsystem.ismsuite.modeler.process.simulator;

import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.informationsystem.ismsuite.modeler.process.simulator.exceptions.InvalidPNID;
import org.informationsystem.ismsuite.modeler.process.simulator.exceptions.UnknownNetType;
import org.informationsystem.ismsuite.modeler.process.simulator.handlers.SimulatorPresentationHandler;
import org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.PlaceMarkingAnnotation;
import org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.PnidsimulatorFactory;
import org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.TransitionActivationAnnotation;
import org.pnml.tools.epnk.annotations.netannotations.NetAnnotation;
import org.pnml.tools.epnk.annotations.netannotations.NetAnnotations;
import org.pnml.tools.epnk.annotations.netannotations.NetannotationsFactory;
import org.pnml.tools.epnk.applications.ApplicationWithUIManager;
import org.pnml.tools.epnk.applications.ui.ApplicationUIManager;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pnmlcoremodel.Place;
import org.pnml.tools.epnk.pnmlcoremodel.PlaceNode;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;
import org.pnml.tools.epnk.pnmlcoremodel.TransitionNode;

public class BasicPNIDSimulator extends ApplicationWithUIManager {

	private PNIDEngine engine;
	private FlatAccess flatAccess;
	private Adapter adapter;
	
	public BasicPNIDSimulator(PetriNet petrinet) {
		super(petrinet);
	}
	
	public PNIDEngine getEngine() {
		return engine;
	}
	
	@Override
	public void initializeContents() {
		initializeSimulator();
	}
	
	protected void initializeSimulator() {
		flatAccess = FlatAccess.getFlatAccess(this.getPetrinet());
		adapter = new InvalidatorAdapter(this);
		flatAccess.addInvalidationListener(adapter);
		
		try {
			engine = new PNIDEngine(this.getPetrinet());
			// engine.setPetrinet(getPetrinet());
			generateCurrentAnnotations();
			
			ApplicationUIManager manager = this.getPresentationManager();
			manager.addPresentationHandler(new SimulatorPresentationHandler());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void generateCurrentAnnotations() {
		NetAnnotations netAnnotations = this.getNetAnnotations();
		NetAnnotation netAnnotation = NetannotationsFactory.eINSTANCE.createNetAnnotation();
		netAnnotation.setNet(this.getPetrinet());
		
		highlightCurrentMarking(netAnnotation);
		highlightEnabledTransitions(netAnnotation);
		
		// netAnnotations.getNetAnnotations().clear();
		netAnnotations.getNetAnnotations().add(netAnnotation);
		netAnnotations.setCurrent(netAnnotation);
	}
	
	private NetAnnotation highlightCurrentMarking(NetAnnotation annotation) {
		if (engine == null) {
			return annotation;
		}
		
		for(Place p: flatAccess.getPlaces()) {
			int value = engine.getTokenCount(p);
			PlaceMarkingAnnotation marking = PnidsimulatorFactory.eINSTANCE.createPlaceMarkingAnnotation();
			marking.setObject(p);
			marking.setText(value);
			annotation.getObjectAnnotations().add(marking);
			
			// now for the reference places connected to this place
			for (PlaceNode ref: flatAccess.getRefPlaces(p) ) {
				PlaceMarkingAnnotation refMarking = PnidsimulatorFactory.eINSTANCE.createPlaceMarkingAnnotation();
				refMarking.setObject(ref);
				refMarking.setText(value);
				annotation.getObjectAnnotations().add(refMarking);
			}
		}			
		return annotation;
	}
	
	private NetAnnotation highlightEnabledTransitions(NetAnnotation annotation) {
		if (engine == null) {
			return annotation;
		}
		
		for(Transition transition: engine.getEnabledTransitions()) {
			TransitionActivationAnnotation ann = PnidsimulatorFactory.eINSTANCE.createTransitionActivationAnnotation();
			ann.setObject(transition);
			annotation.getObjectAnnotations().add(ann);
			
		}
		
		return annotation;
	}
	
	
	
	
	public Set<PNIDBinding> getBindings(TransitionNode transitionNode) {
		return engine.getEnabledBindings(flatAccess.resolve(transitionNode));
	}

	public void fire(PNIDBinding b) {
		engine.fire(b);
		generateCurrentAnnotations();
	}
	
	
}

package org.informationsystem.ismsuite.modeler.process.simulator;

import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.TokenBag;
import org.informationsystem.ismsuite.modeler.process.simulator.exceptions.InvalidArc;
import org.informationsystem.ismsuite.modeler.process.simulator.exceptions.InvalidPNID;
import org.informationsystem.ismsuite.modeler.process.simulator.exceptions.UnknownNetType;
import org.informationsystem.ismsuite.modeler.process.simulator.handlers.FireTransitionHandler;
import org.informationsystem.ismsuite.modeler.process.simulator.handlers.PlaceHandler;
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
	
	public FlatAccess getFlatAccess() {
		return flatAccess;
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
			manager.addActionHandler(new FireTransitionHandler(this));
			manager.addActionHandler(new PlaceHandler(this));
			
		
		} catch (UnknownNetType | InvalidPNID | InvalidArc e) {
			
			MessageDialog.openError(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					"ISMSuite - Error",
					e.getMessage());
			// e.printStackTrace();
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
		
		for(Transition transition: getEnabledTransitions()) {
			TransitionActivationAnnotation ann = PnidsimulatorFactory.eINSTANCE.createTransitionActivationAnnotation();
			ann.setObject(transition);
			annotation.getObjectAnnotations().add(ann);
		
			for(TransitionNode ref: flatAccess.getRefTransitions(transition)) {
				TransitionActivationAnnotation refTrans = PnidsimulatorFactory.eINSTANCE.createTransitionActivationAnnotation();
				refTrans.setObject(ref);
				annotation.getObjectAnnotations().add(refTrans);
			}
		}
		
		return annotation;
	}
	
	@Override
	protected void shutDown() {
		super.shutDown();
		
		if (adapter != null && flatAccess != null) {
			flatAccess.removeInvalidationListener(adapter);
		}
		
	}
	
	
	public Set<Transition> getEnabledTransitions() {
		return engine.getEnabledTransitions();
	}
	
	
	public Set<PNIDBinding> getBindings(TransitionNode transitionNode) {
		return engine.getEnabledBindings(flatAccess.resolve(transitionNode));
	}

	public void fire(PNIDBinding b) {
		engine.fire(b);
		generateCurrentAnnotations();
	}

	public TokenBag getTokensFor(PlaceNode object) {
		Place place = flatAccess.resolve(object);
		if (engine.getCurrentMarking().containsKey(place)) {
			return engine.getCurrentMarking().get(place);
		} else {
			return PnidsFactory.eINSTANCE.createTokenBag();
		}
	}
	
	
	
	
}

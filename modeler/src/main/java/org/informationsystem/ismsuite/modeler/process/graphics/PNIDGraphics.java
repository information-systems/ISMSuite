package org.informationsystem.ismsuite.modeler.process.graphics;

import java.util.List;
import java.util.ArrayList;
import org.pnml.tools.epnk.gmf.extensions.graphics.GraphicalExtension;
import org.pnml.tools.epnk.gmf.extensions.graphics.IUpdateableFigure;
import org.pnml.tools.epnk.pnmlcoremodel.PnmlcoremodelPackage;
import org.pnml.tools.epnk.pnmlcoremodel.RefPlace;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;
import org.pnml.tools.epnk.pnmlcoremodel.Place;
import org.eclipse.emf.ecore.EClass;
import org.informationsystem.ismsuite.modeler.process.graphics.figure.PNIDPlaceFigure;
import org.informationsystem.ismsuite.modeler.process.graphics.figure.PNIDTransitionFigure;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage;


public class PNIDGraphics extends GraphicalExtension {

	
	@Override
	public List<EClass> getExtendedNetTypes() {
		ArrayList<EClass> list = new ArrayList<>();
		list.add(PnidsPackage.eINSTANCE.getPNID());
		// list.add(PnmlcoremodelPackage.eINSTANCE.getTransition());
		return list;
	}
	
	@Override
	public List<EClass> getExtendedNetObjects(EClass netType) {
		ArrayList<EClass> list = new ArrayList<EClass>();
		if (netType.equals(PnidsPackage.eINSTANCE.getPNID())) {
			list.add(PnidsPackage.eINSTANCE.getPlace());
			list.add(PnidsPackage.eINSTANCE.getTransition());
		}
		
		return list;
	}
	
	@Override
	public IUpdateableFigure createTransitionFigure(Transition transition) {
		if (transition instanceof org.informationsystem.ismsuite.modeler.process.pnid.pnids.Transition) {
			return new PNIDTransitionFigure((org.informationsystem.ismsuite.modeler.process.pnid.pnids.Transition) transition);
		}
		
		return null;
	}
	
	@Override
	public IUpdateableFigure createPlaceFigure(Place place) {
		if (place instanceof org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place) {
			return new PNIDPlaceFigure((org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place) place);
		}
		return null;
	}
}

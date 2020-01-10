package org.informationsystem.ismsuite.modeler.process.simulator.handlers;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.informationsystem.ismsuite.modeler.process.simulator.graphics.MarkingOverlay;
import org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.PlaceMarkingAnnotation;
import org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.TransitionActivationAnnotation;
import org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation;
import org.pnml.tools.epnk.applications.ui.IPresentationHandler;
import org.pnml.tools.epnk.applications.ui.figures.RectangleOverlay;
import org.pnml.tools.epnk.pnmlcoremodel.PlaceNode;
import org.pnml.tools.epnk.pnmlcoremodel.TransitionNode;

public class SimulatorPresentationHandler implements IPresentationHandler {

	@Override
	public IFigure handle(ObjectAnnotation annotation, AbstractGraphicalEditPart editPart) {
		
		
		if (annotation instanceof PlaceMarkingAnnotation) {
			PlaceMarkingAnnotation markingAnnotation = (PlaceMarkingAnnotation) annotation;
			
			if (editPart instanceof GraphicalEditPart) {
				GraphicalEditPart graphicalEditPart = (GraphicalEditPart) editPart;
				java.lang.Object modelObject = graphicalEditPart.resolveSemanticElement();
				if (modelObject instanceof PlaceNode) {
					MarkingOverlay overlay = new MarkingOverlay(graphicalEditPart, markingAnnotation.getText());
					return overlay;
				}
			}
		} else if (annotation instanceof TransitionActivationAnnotation) {
			// TransitionActivationAnnotation ann = (TransitionActivationAnnotation) annotation;
			if (editPart instanceof GraphicalEditPart) {
				GraphicalEditPart graphicalEditPart = (GraphicalEditPart) editPart;
				java.lang.Object modelObject = graphicalEditPart.resolveSemanticElement();
				if (modelObject instanceof TransitionNode) {
					RectangleOverlay overlay = new RectangleOverlay(graphicalEditPart);
					overlay.setForegroundColor(ColorConstants.red);
					overlay.setFill(false);
					return overlay;
				}
			}
		}
		
		return null;
	}


}

package org.informationsystem.ismsuite.modeler.process.graphics.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.TransitionFigure;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;

public class PNIDTransitionFigure  extends TransitionFigure {
	
	public PNIDTransitionFigure(Transition transition) {
		super(transition);
	}
	
	@Override
	public void update() {
		this.repaint();
	}
	
	@Override
	protected void fillShape(Graphics graphics) {
		this.setBackgroundColor(ColorConstants.green);
		this.setAlpha(100);
		super.fillShape(graphics);
	}
}

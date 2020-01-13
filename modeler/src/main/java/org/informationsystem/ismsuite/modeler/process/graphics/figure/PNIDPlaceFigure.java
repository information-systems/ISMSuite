package org.informationsystem.ismsuite.modeler.process.graphics.figure;

import org.informationsystem.ismsuite.modeler.process.graphics.TokenGraphics;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.PlaceFigure;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place;

public class PNIDPlaceFigure extends PlaceFigure {

	public PNIDPlaceFigure(Place place) {
		super(place);
	}
	
	@Override
	public void update() {
		this.repaint();
	}
	
	@Override
	protected void fillShape(Graphics graphics) {
		super.fillShape(graphics);
		// First, get the location and dimension of the place as a rectangle
		
		int m = 0;
		if (place instanceof Place) {
			m = getMarking((Place) place);
			Place p = (Place) place;
			if (p.getType() != null && p.getType().getStructure() != null && p.getType().getStructure().getEntityType() != null) {
				if (!p.getType().getStructure().getEntityType().isEmpty()) {
					this.setBackgroundColor(ColorConstants.yellow);
				}
			}
		}
		
		drawTokens(m, graphics);
	}
	
	private int getMarking(Place place) {
		if ((place.getInitialMarking() != null) && (place.getInitialMarking().getStructure() != null) ) {
			return place.getInitialMarking().getStructure().getToken().size();
		} else {
			return 0;
		}
	}
	
	private void drawTokens(int m, Graphics graphics) {
		TokenGraphics.drawTokens(m, this.getClientArea(), getForegroundColor(), graphics);
	}

}

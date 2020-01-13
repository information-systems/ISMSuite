package org.informationsystem.ismsuite.modeler.process.simulator.graphics;

import org.informationsystem.ismsuite.modeler.process.graphics.TokenGraphics;
import org.pnml.tools.epnk.applications.ui.figures.EllipseOverlay;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;


public class MarkingOverlay extends EllipseOverlay {

static private Font defaultFont = Display.getDefault().getSystemFont();
	
	private int value = 0;
	
	public MarkingOverlay(GraphicalEditPart editPart, Font font, int value)  {
		super(editPart);
		
		this.value = value;
		this.setBackgroundColor(ColorConstants.white);
		this.setForegroundColor(ColorConstants.blue);
		this.setFill(true);
		this.setAlpha(255);
	}
	
	public MarkingOverlay(GraphicalEditPart editPart, int value)  {
		this(editPart, null, value);
	}
	
	public MarkingOverlay(GraphicalEditPart editPart)  {
		this(editPart, null, 0);
	}
	
	@Override
	protected void fillShape(Graphics graphics) {
		super.fillShape(graphics);	
		// draw the dots
		TokenGraphics.drawTokens(value, this.getClientArea(), getForegroundColor(), graphics);
	}
	
}

package org.informationsystem.ismsuite.modeler.process.graphics;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class TokenGraphics {
	public static void drawTokens(int amount, Rectangle bounds, Color tokenColor, Graphics graphics) {
		if (amount == 0) {
			return;
		}
		
		float radius = bounds.width / 2;
		int tokenRadius = Math.round(radius / 4);
		
		// As the x,y coordinate of any oval is the left upper corner,
		// we need to move it with the half the tokenRadius!
		int cx = Math.round(bounds.x + bounds.width/2 - tokenRadius / 2);
		int cy = Math.round(bounds.y + bounds.height/2 - tokenRadius / 2);
			
		if (amount > 9) {
			graphics.setForegroundColor(tokenColor);
			graphics.drawString(""+amount, cx-7, cy-10);
			return;
		}
		/* token schema:
		 * 
		 * 1   2   3
		 * 4   5   6
		 * 7   8   9
		 */
		
		
		double angle = Math.PI / 4;
		int dX = (int) Math.round(Math.cos(angle) * radius * 2 / 3);
		int dY = (int) Math.round(Math.sin(angle) * radius * 2 / 3); 
		
		// token 1 and token 9
		if (amount > 2) {
			// token 1
			drawToken(cx-dX, cy+dY, tokenRadius, tokenColor, graphics);
			
			// token 9
			drawToken(cx+dX, cy-dY, tokenRadius, tokenColor, graphics);
		}
		
		// token 2 and token 8
		if (amount == 2 || amount > 7) {
			// token 2
			drawToken(cx, cy+dY, tokenRadius, tokenColor, graphics);
			// token 8
			drawToken(cx, cy-dY, tokenRadius, tokenColor, graphics);
		}
		
		// token 3 and token 7
		if (amount > 3) {
			// token 3
			drawToken(cx+dX, cy+dY, tokenRadius, tokenColor, graphics);
			
			// token 7
			drawToken(cx-dX, cy-dY, tokenRadius, tokenColor, graphics);
		}
		
		// token 4 and token 6
		if (amount > 5) {
			// token 4
			drawToken(cx-dX, cy, tokenRadius, tokenColor, graphics);
			
			// token 6
			drawToken(cx+dX, cy, tokenRadius, tokenColor, graphics);
		}
		
		// token 5
		if (amount % 2 == 1) {
			drawToken(cx, cy, tokenRadius, tokenColor, graphics);
		}
	}
	
	public static void drawToken(int cx, int cy, int tokenRadius, Color tokenColor, Graphics graphics) {
		graphics.setBackgroundColor(tokenColor);
		graphics.fillOval(cx, cy, tokenRadius, tokenRadius);
	}
}

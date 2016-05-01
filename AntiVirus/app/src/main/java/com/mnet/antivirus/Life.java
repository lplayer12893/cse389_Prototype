/**
 * 
 */
package com.mnet.antivirus;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * @author Lucas Stuyvesant, Joshua Garcia, Benjamin Morales
 */
public class Life {

	private int health;
	private Coordinate location;
    private Bitmap bmp;

    Life() {
		this(100, new Coordinate(), null);
	}
	
	Life(int h, Coordinate c, Bitmap b) {
		health = h;
		location = c;
        bmp = b;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return the location
	 */
	public Coordinate getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Coordinate location) {
		this.location = location;
	}

	public void onDraw(Canvas canvas) {
		canvas.drawBitmap(bmp, location.getX(), location.getY(), null);
	}
}

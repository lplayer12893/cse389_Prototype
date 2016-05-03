/**
 * 
 */
package com.mnet.antivirus;

/**
 * @author Lucas Stuyvesant, Joshua Garcia, Benjamin Morales
 */
public class Coordinate {

	private double x;
	private double y;
	
	Coordinate() {
		this(0,0);
	}
	
	Coordinate(double xx, double yy) {
		x = xx;
		y = yy;
	}
	
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	public double distance(Coordinate b) {
		return (double)Math.ceil(Math.sqrt((x-b.getX()) * (x-b.getX()) + (y-b.getY()) * (y-b.getY())));
	}
}

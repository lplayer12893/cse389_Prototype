/**
 * 
 */
package main_activity;

/**
 * @author Lucas Stuyvesant, Joshua Garcia, Benjamin Morales
 */
public class Coordinate {

	private int x;
	private int y;
	
	Coordinate() {
		this(0,0);
	}
	
	Coordinate(int xx, int yy) {
		x = xx;
		y = yy;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	public int distance(Coordinate b) {
		return (int)Math.ceil(Math.sqrt((x-b.getX()) * (x-b.getX()) + (y-b.getY()) * (y-b.getY())));
	}
}

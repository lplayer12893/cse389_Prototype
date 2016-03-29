/**
 * 
 */
package main_activity;

/**
 * @author Lucas Stuyvesant, Joshua Garcia, Benjamin Morales
 */
public class Life {

	private int health;
	private Coordinate location;
	
	Life() {
		this(0, new Coordinate());
	}
	
	Life(int h, Coordinate c) {
		health = h;
		location = c;
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
}

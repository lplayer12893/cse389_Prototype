/**
 * 
 */
package main_activity;

/**
 * @author lplayer12893
 *
 */
public class Virus {

	private float speed;
	private Coordinate location;
	private Life target;
	private int radius;
	private int damageRate;
	
	Virus() {
		this(new Coordinate(0,0), null);
	}
	
	Virus(Coordinate cur, Life tgt) {
		location = cur;
		target = tgt;
		
		/*
		 * TODO: speed is random, radius is a constant
		 */
		
		damageRate = 1;
	}
	
	/**
	 * @param c
	 * @return true if coordinate c is within the radius of the Virus
	 */
	boolean isHit(Coordinate c) {
		if(location.distance(c) > radius) {
			return false;
		}
		return true;
	}
	
	
	void damageLife() {
		if(location.distance(target.getLocation()) <= radius) {
			target.setHealth(target.getHealth() - damageRate);
			speed = 0;
			damageRate = damageRate * 2;
		}
	}
}

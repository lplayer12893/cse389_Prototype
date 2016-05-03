/**
 *
 */
package com.mnet.antivirus;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;

import java.util.Random;

/**
 * @author lplayer12893
 *
 */
public class Virus {

	private double xSpeed;
    private double ySpeed;
    private double speedRatio;
    private double maxSpeed = 10;
    private double minSpeed = 7;
	private Coordinate location;
	private Life target;
	private int radius;
	private int damageRate;
    private Context ctx;
    private Bitmap bmp;
    private View gameView;
	
	/*Virus() {
        this(new Coordinate(0,0), null, null, null);
	}*/

    Virus(GameView gameView, Context context) {
	//Virus(Coordinate cur, Life tgt, GameView gameView, Context context) {
        this.gameView = gameView;
        this.ctx = context;
        this.radius = 75;
		
		/*
		 * TODO: speed is random, radius is a constant
		 */
        location = new Coordinate();
        xSpeed = 0;
        ySpeed = 0;
		damageRate = 1;

        bmp = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.basic_virus);
        scaleBitmap(bmp);
    }
	
	/**
	 * @param c
	 * @return true if coordinate c is within the radius of the Virus
	 */
	boolean isHit(Coordinate c) {
        double x = location.getX();
        double y = location.getY();
        return c.getX() > x && c.getX() < x + bmp.getWidth() && c.getY() > y && c.getY() < y + bmp.getHeight();
    }
	
	
	void damageLife() {
		if(location.distance(target.getLocation()) <= radius) {
			target.setHealth(target.getHealth() - damageRate);
			xSpeed = 0;
            ySpeed = 0;
			//damageRate = damageRate * 2;
		}
	}

    public Life getTarget() {
        return target;
    }

    public void setTarget(Life trgt) {
        this.target = trgt;
        Random rnd = new Random();

        Coordinate loc = target.getLocation();

        int xSign = 1;
        int ySign = 1;
        speedRatio = (double) (loc.getY() - location.getY()) / (double) (loc.getX() - location.getX());

        if(loc.getX() < location.getX()){
            xSign = -1;
        }
        if(loc.getY() < location.getY()){
            ySign = -1;
        }

        if(loc.getX() > loc.getY()) {
            ySpeed = ySign * rnd.nextDouble() * Math.abs((maxSpeed * speedRatio - minSpeed * speedRatio) + minSpeed * speedRatio);
            xSpeed = xSign * Math.abs(ySpeed / speedRatio);
        }

        else {
            xSpeed = xSign * rnd.nextDouble() * Math.abs((maxSpeed / speedRatio - minSpeed / speedRatio) + minSpeed / speedRatio);
            ySpeed = ySign * Math.abs(xSpeed * speedRatio);
        }
    }

    public void scaleBitmap(Bitmap btmp) {
        int width = btmp.getWidth();
        int height = btmp.getHeight();

        //big virus scale down by 200
        float scaleWidth = ((float) 150) / width;
        float scaleHeight = ((float) 150) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        bmp = Bitmap.createBitmap(btmp, 0, 0, width, height, matrix, false);
    }

    public void update() {
        double x = location.getX();
        double y = location.getY();

        damageLife();

        if(x >= gameView.getWidth() - bmp.getWidth() - xSpeed || x + xSpeed <= 0) {
            xSpeed = -xSpeed;
        }
        x = x + xSpeed;
        location.setX(x);

        if(y >= gameView.getHeight() - bmp.getHeight() - ySpeed || y + ySpeed <=0) {
            ySpeed = -ySpeed;
        }
        y = y + ySpeed;
        location.setY(y);
    }

    public void onDraw(Canvas canvas) {
        update();
        canvas.drawBitmap(bmp, (int)location.getX(), (int)location.getY(), null);
    }
}

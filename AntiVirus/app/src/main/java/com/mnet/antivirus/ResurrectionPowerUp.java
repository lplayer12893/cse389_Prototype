package com.mnet.antivirus;

import android.app.Activity;
import android.widget.ImageView;

import java.util.List;

/**
 * @author Lucas Stuyvesant, Joshua Garcia, Benjamin Morales
 * @notes look at webpage: http://stackoverflow.com/questions/16906528/change-image-of-imageview-programmatically-android
 *      to figure out how to set images for power ups
 */
public class ResurrectionPowerUp implements PowerUp {

    protected List<Life> apps;
    protected ImageView image;
    protected Activity activity;

    public ResurrectionPowerUp(List appList, Activity activity) {
        this.apps = appList;
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Resurrection Power Up:\n" + "Returns a lost app with full health.\n" + "Tap on the power-up icon to activate.\n";
    }

    /**
     * Implements the power-up action
     */
    @Override
    public void powerUpAction() {
        for(Life l : apps) {
            //Just go through array and return first dead app to full health
            if(l.getHealth() == 0) {
                l.setHealth(100);
                break;
            }
        }
    }

    /**
     * prints a description of the power-up and how to use it
     */
    @Override
    public void description() {

    }

    /**
     * Shows power-up icon by replacing the temp icon
     */
    @Override
    public void showIcon() {
        image = (ImageView) activity.findViewById(R.id.power_up);
        image.setImageResource(R.drawable.caps);
    }

    /**
     * Hides power-up icon by replacing with temp icon
     */
    @Override
    public void hideIcon() {
        image = (ImageView) activity.findViewById(R.id.power_up);
        image.setImageResource(R.drawable.temp);
    }


}

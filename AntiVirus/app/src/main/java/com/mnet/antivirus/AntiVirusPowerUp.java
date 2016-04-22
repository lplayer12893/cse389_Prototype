package com.mnet.antivirus;

import android.app.Activity;
import android.widget.ImageView;

import java.util.List;
import java.util.Random;

/**
 * @author Lucas Stuyvesant, Joshua Garcia, Benjamin Morales
 * @notes look at webpage: http://stackoverflow.com/questions/16906528/change-image-of-imageview-programmatically-android
 *      to figure out how to set images for power ups
 */
public class AntiVirusPowerUp implements PowerUp {

    List<Virus> viruses;
    protected ImageView image;
    protected Activity activity;

    public AntiVirusPowerUp(List virusList, Activity activity) {
        this.viruses = virusList;
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "AntiVirus Power Up:\n" + "Eliminates 3 viruses.\n" + "Tap on the power-up icon to activate.\n";
    }

    /**
     * Implements the power-up action
     */
    @Override
    public void powerUpAction() {
        //remove special viruses first?

        Random rand = new Random();
        Integer i = 0;
        for(;i < 3; i++) {
            Integer random = rand.nextInt(viruses.size());
            viruses.remove(random);
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

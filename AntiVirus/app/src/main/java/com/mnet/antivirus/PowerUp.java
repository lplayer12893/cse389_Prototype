package com.mnet.antivirus;

/**
 * Created by Joshua on 4/21/2016.
 */
public interface PowerUp {
    /**
     * Implements the power-up action
     */
    void powerUpAction();

    /**
     * prints a description of the power-up and how to use it
     */
    void description();

    /**
     * Shows power-up icon by replacing the temp icon
     */
    void showIcon();

    /**
     * Hides power-up icon by replacing with temp icon
     */
    void hideIcon();

}

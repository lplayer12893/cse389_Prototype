package com.mnet.antivirus;

import android.content.Intent;
import android.graphics.Canvas;

/**
 * Created by Josh on 4/30/2016.
 */
public class GameLoopThread extends Thread {
    private GameView view;
    private boolean running;
    private int spawnDelay;
    private int downIters;

    public GameLoopThread(GameView view) {
        this.view = view;
        spawnDelay = 5000;
        downIters = 0;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        while (running) {
            Canvas c = null;
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    if (c != null) {
                        view.onDraw(c);
                    }
                    if (view.lives.isEmpty()) {
                        running = false;
                        view.viruses.clear();
                        Intent scores = new Intent(view.ctx, scores.class);
                        scores.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.ctx.startActivity(scores);
                    }
                    else {
                        if (downIters == 0 && spawnDelay > 100) {
                            spawnDelay -= 50;
                            downIters = spawnDelay / 100;
                            view.createVirus();
                        } else if (downIters != 0) {
                            downIters--;
                        }
                        view.checkLife();
                        view.removeDead();
                    }
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }
}

package com.mnet.antivirus;

import android.graphics.Canvas;

/**
 * Created by Josh on 4/30/2016.
 */
public class GameLoopThread extends Thread {
    //static final long fps = 30;
    private GameView view;
    private boolean running = false;

    public GameLoopThread(GameView view) {
        this.view = view;
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
                    view.onDraw(c);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }
}

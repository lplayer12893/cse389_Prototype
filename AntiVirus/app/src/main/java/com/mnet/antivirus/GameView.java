package com.mnet.antivirus;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh on 4/30/2016.
 */
public class GameView extends SurfaceView {
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private List<Virus> viruses;

    public GameView(final Context context) {
        super(context);
        viruses = new ArrayList<>();
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {

                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                for(int i = 0; i < 10; i++) {
                    createVirusList();
                }
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });

    }

    private void createVirusList() {
        viruses.add(new Virus(this, getContext()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        for(Virus v : viruses) {
            v.onDraw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Coordinate cEvent = new Coordinate((int) event.getX(), (int) event.getY());
        synchronized (getHolder()) {
            for(int i = 0; i < viruses.size(); i++) {
                Virus virus = viruses.get(i);
                if(virus.isHit(cEvent)) {
                    viruses.remove(virus);
                    break;
                }
            }
        }
        return true;
    }
}

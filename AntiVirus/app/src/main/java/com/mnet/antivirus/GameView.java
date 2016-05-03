package com.mnet.antivirus;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Josh on 4/30/2016.
 *
 * @notes http://www.edu4java.com/en/androidgame/androidgame7.html
 */
public class GameView extends SurfaceView {
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private List<Virus> viruses;
    private List<Life> lives;
    private Bitmap appMap;
    private List<PackageInfo> allApps;
    private PackageManager pm;
    private long click;

    public GameView(final Context context) {
        super(context);
        viruses = new ArrayList<Virus>();
        lives = new ArrayList<Life>();

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
                /*for(int i = 0; i < 10; i++) {
                    createVirusList();
                }*/



                createLifeList();

                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
        for(int i = 0; i < 10; i++) {
            createVirusList();
        }
    }

    private void createVirusList() {
        viruses.add(new Virus(this, getContext()));
    }

    private void createLifeList() {

        pm = getContext().getPackageManager();
        allApps = pm.getInstalledPackages(0);

        if(allApps.size() < 32){    // duplicate apps until you have 32
            for(PackageInfo a : allApps){
                allApps.add(a);
                if(allApps.size() == 32){
                    break;
                }
            }
        }

        Random r = new Random();

        while(allApps.size() > 32){ // delete apps until you have 32
            allApps.remove(r.nextInt(allApps.size()));
        }
        
        Drawable appIcon;
        appMap = null;

        int j = 0;
        for(int x = ((int)Math.ceil(getWidth() / 5.0)) - 75; x + 75 < getWidth(); x += (int)Math.ceil(getWidth() / 5.0)){
            for(int y = ((int)Math.ceil(getHeight() / 9.0)) - 75; y + 75 < getHeight(); y += (int)Math.ceil(getHeight() / 9.0), j++){
                appIcon = pm.getApplicationIcon(allApps.get(j).applicationInfo);
                appMap = drawableToBitmap(appIcon);
                lives.add(new Life(100, new Coordinate(x,y), appMap));
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.BLACK);

        for (Life l : lives) {
            l.onDraw(canvas);
        }

        for(Virus v : viruses) {
            v.onDraw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(System.currentTimeMillis() - click > 300) {
            click = System.currentTimeMillis();
            Coordinate cEvent = new Coordinate((int) event.getX(), (int) event.getY());
            synchronized (getHolder()) {
                boolean hitVirus = false;
                for (Virus virus : viruses) {
                    if (virus.isHit(cEvent)) {
                        viruses.remove(virus);
                        hitVirus = true;
                        break;
                    }
                }
                if (!hitVirus) {
                    for (Life life : lives) {
                        if (life.isHit(cEvent)) {
                            lives.remove(life);
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}

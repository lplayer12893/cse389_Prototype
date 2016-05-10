package com.mnet.antivirus;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.FrameLayout;

/**
 * @author Benjamin Morales, Lucas Stuyvesent, Joshua Garcia.
 */
public class AVgame extends Activity {
    GameView gv;
    Bitmap bmp;

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.game_layout);
        gv = (GameView) findViewById(R.id.myGameView);
        FrameLayout app = (FrameLayout) findViewById(R.id.fl);

        /* gets home screen wallpaper amd sets it as background */
        final WallpaperManager wallpaper = WallpaperManager.getInstance(this);
        final Drawable wallD = wallpaper.getDrawable();

        app.setBackground(wallD);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    /**
     * Checks whether app is a SYSTEM installed app
     * @param pkgInfo the information of the package
     * @return true if app is a SYSTEM app, false otherwise
     */
    public boolean isSystemPackage(PackageInfo pkgInfo) {

        if((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
            return true;
        }

        return false;
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
}
package com.mnet.antivirus;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Benjamin Morales, Lucas Stuyvesent, Joshua Garcia.
 */
public class AVgame extends Activity {
    List<PackageInfo> allApps;
    List<PackageInfo> noSystemApps;
    AppIconAdapter ap;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        /* gets home screen wallpaper amd sets it as background */
        final WallpaperManager wallpaper = WallpaperManager.getInstance(this);
        final Drawable wallD = wallpaper.getFastDrawable();


        /* sets up the view in a grid */
        setContentView(R.layout.game_layout);
        GridView appGrid = (GridView) findViewById(R.id.appGridView);

        appGrid.setBackground(wallD);

        PackageManager pm = getPackageManager();
        allApps = pm.getInstalledPackages(0);
        noSystemApps = new ArrayList<>();

        for(PackageInfo p : allApps) {
            if(!isSystemPackage(p)) {
                noSystemApps.add(p);
            }
        }

        /* uses view adapter to populate grid view */
        ap = new AppIconAdapter(this, noSystemApps, pm);
        System.out.println("Number of app = " + ap.getCount());
        appGrid.setAdapter(ap);
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

}
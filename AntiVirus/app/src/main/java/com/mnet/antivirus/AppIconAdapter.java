package com.mnet.antivirus;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

/**
 * Adapter used to populate a gridview with app icons
 *
 * @author Joshua Garcia, Lucas Stuyvesent, Benjamin Morales.
 */
public class AppIconAdapter extends BaseAdapter {
    List<PackageInfo> appList;
    PackageManager packageManager;
    private Context context;

    public AppIconAdapter(Context context, List<PackageInfo> packageList,
                          PackageManager packageManager) {
        super();
        this.context = context;
        this.appList = packageList;
        this.packageManager = packageManager;
    }

    /**
     * Gets the count of items in the arrayList
     * @return the size of the arrayList
     */
    public int getCount() {
        return appList.size();
    }

    /**
     * Gets app from the list based on position
     * @param position, the objects position in the list
     * @return the object at the given position
     */
    public Object getItem(int position) {
        return appList.get(position);
    }

    /**
     * Gets the items ID based on position (Needed to be implemented from interface)
     * @param position, the position of the object in the list
     * @return 0 (needed to be implemented from the interface, but is no used)
     */
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Gets the view after it is populated with the app icons
     * @param position, the position of the app in the list
     * @param convertView, the view being updated
     * @param parent, the parent group view
     * @return the updated view
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView iv;

        if (convertView == null) {
            iv = new ImageView(context);
            iv.setLayoutParams(new GridView.LayoutParams(85, 85));
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setPadding(8, 8, 8, 8);
        }
        else
        {
            iv = (ImageView) convertView;
        }

        PackageInfo pInfo = (PackageInfo) getItem(position);
        Drawable appIcon = packageManager.getApplicationIcon(pInfo.applicationInfo);
        appIcon.setBounds(0, 0, 40, 40);

        iv.setImageDrawable(appIcon);
        return iv;
    }

}


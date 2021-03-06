package com.mnet.antivirus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Help extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private static final int NUM_PAGES = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Intent i = new Intent(getApplicationContext(), AVgame.class);
                startActivity(i);
            }
        });

    }

    @Override
    public  void onBackPressed() {
        if(mViewPager.getCurrentItem() == 0) {
            //do default action when on first page
            super.onBackPressed();
        } else {
            //return to previous page
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            showPopUp();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    PopupWindow popup;
    private void showPopUp() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.about_popup, (ViewGroup)findViewById(R.id.popUpAbout));
        popup = new PopupWindow(layout, 800, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        TextView mini = (TextView)layout.findViewById(R.id.about_title);
        Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/divlit.ttf");
        mini.setTypeface(customFont);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //if(popup.isShowing()) {
        if(popup != null) {
            popup.dismiss();
            popup = null;
        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            switch (position) {
                case 0:
                    return new help_fragments.helpFragment();
                case 1:
                    return new help_fragments.helpFragmentOne();
                case 2:
                    return new help_fragments.helpFragmentTwo();
                case 3:
                    return new help_fragments.helpFragmentThree();
                case 4:
                    return new help_fragments.helpFragmentFour();
                default:
                    return new help_fragments.helpFragment();
            }
        }

        @Override
        public int getCount() {
            // total pages.
            return NUM_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
                case 4:
                    return "SECTION 5";
            }
            return null;
        }
    }
}

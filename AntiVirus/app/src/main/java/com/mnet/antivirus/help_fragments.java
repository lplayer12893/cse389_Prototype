package com.mnet.antivirus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by bmoral on 5/2/16.
 */
public class help_fragments {

    /**
     * Page one
     */
    public static class helpFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_help, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format));
            return rootView;
        }
    }

    /**
     * Page two
     */
    public static class helpFragmentOne extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_help_one, container, false);
            return rootView;

        }
    }

    /**
     * Page three
     */
    public static class helpFragmentTwo extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_help_two, container, false);
            return rootView;

        }
    }

    /**
     * Page four
     */
    public static class helpFragmentThree extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_help_three, container, false);
            return rootView;

        }
    }

    /**
     * Page five
     */
    public static class helpFragmentFour extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_help_four, container, false);
            return rootView;

        }
    }
}

package com.example.learningandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DescriptionFragment extends Fragment {
    String[] mCourseDescriptions;
    TextView mCourseDescriptionTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCourseDescriptions = getResources().getStringArray(R.array.course_descriptions);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View theView = inflater.inflate(R.layout.fragment_description, container, false);
        mCourseDescriptionTextView = (TextView) theView.findViewById(R.id.courseDescription);
        return theView;
    }

    public void  setCourse(int courseIndex){
        mCourseDescriptionTextView.setText(mCourseDescriptions[courseIndex]);

    }

}

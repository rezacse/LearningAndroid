package com.example.learningandroid;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by ### on 13-03-2017.
 */

public class CourseFragment extends Fragment {

    public  final static  String CourseTitle = "course title";
    public  final static  String CourseDescription = "course description";
    public  final static  String TopCard = "top card";
    public  final static  String CourseTypeLogo = "course type logo";
    public  final static  String CourseHasReferencesList = "course has references list";

    private final static int CourseActionNotSet=-1;
    private final static boolean CourseHasReferencesListNotSet=false;

    String mCourseTitle;
    String mCourseDescription;
    int mTopCardResourceId;
    int mCourseTypeLogoResourceId;
    boolean mCourseHasRefUrls;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View theView = inflater.inflate(R.layout.fragment_course_info, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mCourseTitle = arguments.getString(CourseTitle);
            mCourseDescription = arguments.getString(CourseDescription);
            mTopCardResourceId = arguments.getInt(TopCard);
            mCourseTypeLogoResourceId = arguments.getInt(CourseTypeLogo);
            mCourseHasRefUrls = arguments.getBoolean(CourseHasReferencesList, CourseHasReferencesListNotSet);

            displayValues(theView, mCourseTitle, mCourseDescription, mTopCardResourceId, mCourseTypeLogoResourceId);
        }
        return theView;
    }


    private void displayValues(View theView, String courseTitle, String courseDescription, int topCardResourceId, int courseTypeLogoResourceId) {

        TextView courseTitleTextView = (TextView) theView.findViewById(R.id.courseTitle);
        TextView courseDescriptionTextView = (TextView) theView.findViewById(R.id.courseDescription);
        ImageView topCardImageView = (ImageView) theView.findViewById(R.id.topCard);
        ImageView courseTypeLogoImageView = (ImageView) theView.findViewById(R.id.courseTypeLogo);

        courseTitleTextView.setText(courseTitle);
        courseDescriptionTextView.setText(courseDescription);
        topCardImageView.setImageResource(topCardResourceId);
        courseTypeLogoImageView.setImageResource(courseTypeLogoResourceId);
    }


    @Override
    public  void  onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        if(mCourseHasRefUrls){
            inflater.inflate(R.menu.course_refs, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean handled = true;
        int courseActionResourceId = CourseActionNotSet;

        switch (item.getItemId()) {
            case R.id.action_view:
                courseActionResourceId = R.string.action_view;
                break;

            case R.id.action_contents:
                courseActionResourceId = R.string.action_contents;
                break;

            case R.id.action_description:
                courseActionResourceId = R.string.action_description;
                break;

            case R.id.action_assessment:
                courseActionResourceId = R.string.action_assessment;
                break;

            case R.id.action_exercises:
                courseActionResourceId = R.string.action_exercises;
                break;

            case R.id.action_refs:
                courseActionResourceId = R.string.action_refs;
                break;

            default:
                handled = super.onOptionsItemSelected(item);
        }

        if (courseActionResourceId != CourseActionNotSet)
            showActivity(courseActionResourceId);

        return handled;
    }

    private void showActivity(int courseActionResourceId){

        Intent intent = new Intent(getActivity(), CourseActionsActivity.class);
        intent.putExtra(CourseActionsActivity.CourseAction, getString(courseActionResourceId));
        intent.putExtra(CourseActionsActivity.CourseTitle, mCourseTitle);
        intent.putExtra(CourseActionsActivity.TopCard, mTopCardResourceId);

        startActivity(intent);
    }

}

package com.example.learningandroid;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toast;

/**
 * Created by ### on 13-03-2017.
 */

public class CoursePagerAdapter extends FragmentStatePagerAdapter {

    public static  final int CourseLibAndroid = 0;
    public static  final int CourseLibIos = 1;
    public static  final int CourseLibWindowsPhone = 2;
    String[] mCourseTitles;
    String[] mCourseTitlesShort;
    String[] mCourseDescriptions;
    int mCourseLogoResourceId;

    Context mContext;

    public CoursePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        Resources resources = context.getResources();
        mCourseTitles = resources.getStringArray(R.array.course_titles);
        mCourseTitlesShort = resources.getStringArray(R.array.course_titles_short);
        mCourseDescriptions = resources.getStringArray(R.array.course_descriptions);
        mCourseLogoResourceId = R.drawable.ps_android_logo;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle arguments = new Bundle();
        arguments.putString(CourseFragment.CourseTitle, mCourseTitles[position]);
        arguments.putString(CourseFragment.CourseDescription, mCourseDescriptions[position]);
        arguments.putInt(CourseFragment.TopCard, translateTopCardItem(position));
        arguments.putInt(CourseFragment.CourseTypeLogo, R.drawable.ps_android_logo);

        CourseFragment courseFragment = new CourseFragment();
        courseFragment.setArguments(arguments);
        return courseFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCourseTitlesShort[position];
    }

    @Override
    public int getCount() {
        return mCourseTitles.length;
    }

    private int translateTopCardItem(int position) {
        int resourceId =0;

        switch (position){
            case 0:
                resourceId = R.drawable.ps_top_card_01;
                break;
            case 1:
                resourceId = R.drawable.ps_top_card_02;
                break;

            case 2:
                resourceId = R.drawable.ps_top_card_03;
                break;

            case 3:
                resourceId = R.drawable.ps_top_card_04;
                break;

            case 4:
                resourceId = R.drawable.ps_top_card_05;
                break;

            case 5:
                resourceId = R.drawable.ps_top_card_06;
                break;

            case 6:
                resourceId = R.drawable.ps_top_card_07;
                break;
        }
        return resourceId;
    }

    public void setCourseLib(int courseLib) {
        boolean isValid = true;
        Resources resources = mContext.getResources();

        switch (courseLib) {
            case CourseLibAndroid:
                mCourseTitles = resources.getStringArray(R.array.android_course_titles);
                mCourseTitlesShort = resources.getStringArray(R.array.android_course_titles_short);
                mCourseDescriptions = resources.getStringArray(R.array.android_course_descriptions);
                mCourseLogoResourceId = R.drawable.ps_android_logo;
                break;
            case CourseLibIos:
                mCourseTitles = resources.getStringArray(R.array.ios_course_titles);
                mCourseTitlesShort = resources.getStringArray(R.array.ios_course_titles_short);
                mCourseDescriptions = resources.getStringArray(R.array.ios_course_descriptions);
                mCourseLogoResourceId = R.drawable.ps_ios_logo;
                break;
            case CourseLibWindowsPhone:
                mCourseTitles = resources.getStringArray(R.array.windows_phone_course_titles);
                mCourseTitlesShort = resources.getStringArray(R.array.windows_phone_course_titles_short);
                mCourseDescriptions = resources.getStringArray(R.array.windows_phone_course_descriptions);
                mCourseLogoResourceId = R.drawable.ps_windows_phone_logo;
                break;
            default:
                Toast.makeText(mContext, "Invalid library name", Toast.LENGTH_LONG).show();
                isValid = false;
                break;
        }

        if(isValid)
            notifyDataSetChanged();
    }
}

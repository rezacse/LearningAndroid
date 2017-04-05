package com.example.learningandroid;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentActivity extends AppCompatActivity  implements OnCourseSelectionChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }

    @Override
    public void onCourseSelectionChanged(int courseIndex){
        FragmentManager fragmentManager = getSupportFragmentManager();
        DescriptionFragment descriptionFragment = (DescriptionFragment) fragmentManager.findFragmentById(R.id.descriptionFragment);
        descriptionFragment.setCourse(courseIndex);
    }
}

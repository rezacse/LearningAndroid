package com.example.learningandroid;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by ### on 12-03-2017.
 */

public class TitlesFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] titles = getResources().getStringArray(R.array.course_titles);

        ArrayAdapter<String> titleAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, titles);
        setListAdapter(titleAdapter);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        OnCourseSelectionChangeListener listener = (OnCourseSelectionChangeListener) getActivity();
        listener.onCourseSelectionChanged(position);
    }
}

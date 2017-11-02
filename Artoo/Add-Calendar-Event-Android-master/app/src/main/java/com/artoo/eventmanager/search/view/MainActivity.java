package com.artoo.eventmanager.search.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.artoo.eventmanager.R;
import com.artoo.eventmanager.addevent.view.AddEventActivity;
import com.artoo.eventmanager.search.model.Event;
import com.artoo.eventmanager.search.viewmodel.EventListViewModel;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_EVENT_DETAIL = "key_event_detail";
    public final int KEY_ADD_EVENT_REQUEST = 1000;
    private Event selectedEvent;
    private EventListViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(EventListViewModel.class);
    }

    public void onAddEventClicked(View view) {
        selectedEvent = new Event();
        Intent addEventIntent = new Intent(this, AddEventActivity.class);
        addEventIntent.putExtra(KEY_EVENT_DETAIL, selectedEvent);
        startActivityForResult(addEventIntent, KEY_ADD_EVENT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == KEY_ADD_EVENT_REQUEST) {
            selectedEvent = data.getParcelableExtra(KEY_EVENT_DETAIL);
            if (selectedEvent != null) {
                mViewModel.addEvent(this, selectedEvent);
            }
        }
    }
}

package com.artoo.eventmanager.search.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.provider.Settings;

import com.artoo.eventmanager.search.model.ApiResponse;
import com.artoo.eventmanager.search.model.Event;

public class SaveEventRepositoryImpl implements SaveEventRepository {

    public SaveEventRepositoryImpl() {
        //TODO setup network client eg retrofit
    }

    public LiveData<ApiResponse> addEvent(Context context, Event event) {
        final MutableLiveData<ApiResponse> liveData = new MutableLiveData<>();

        //TODO fetch response from server with synctime

        save(context, event);
        liveData.setValue(new ApiResponse(event));
        return liveData;
    }


    public void save(Context context, Event event) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.Events.TITLE, event.getTitle());
        intent.putExtra(CalendarContract.Events.DESCRIPTION, event.getDescription());
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, event.getLocation().toString());
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, event.getStartTime());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, event.getEndTime());
        intent.putExtra(CalendarContract.Events.STATUS, 1);
        intent.putExtra(CalendarContract.Events.VISIBLE, 0);
        intent.putExtra(CalendarContract.Events.HAS_ALARM, 1);
        long timeFromServer = System.currentTimeMillis();//dymmy sync time
        intent.putExtra(CalendarContract.Events.LAST_SYNCED, timeFromServer);
        context.startActivity(intent);
    }

}

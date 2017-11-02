package com.artoo.eventmanager.search.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.artoo.eventmanager.search.model.ApiResponse;
import com.artoo.eventmanager.search.model.Event;

public interface SaveEventRepository {

    LiveData<ApiResponse> addEvent(Context context, Event event);
}

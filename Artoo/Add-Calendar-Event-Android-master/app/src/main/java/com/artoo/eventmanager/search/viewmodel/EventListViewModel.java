package com.artoo.eventmanager.search.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.artoo.eventmanager.search.model.Event;
import com.artoo.eventmanager.search.repository.SaveEventRepository;
import com.artoo.eventmanager.search.repository.SaveEventRepositoryImpl;

public class EventListViewModel extends ViewModel {

    private MediatorLiveData<Event> mApiResponse;
    private SaveEventRepository saveEventRepository;

    public EventListViewModel() {
        mApiResponse = new MediatorLiveData<>();
        saveEventRepository = new SaveEventRepositoryImpl();
    }

    @NonNull
    public LiveData<Event> getApiResponse() {
        return mApiResponse;
    }

    public LiveData<Event> addEvent(Context context, Event event) {
        mApiResponse.addSource(
                saveEventRepository.addEvent(context, event),
                apiResponse -> {
                    mApiResponse.setValue(event);
                }
        );
        return mApiResponse;
    }

}
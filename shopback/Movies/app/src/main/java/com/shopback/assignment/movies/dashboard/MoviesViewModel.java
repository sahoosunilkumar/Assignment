package com.shopback.assignment.movies.dashboard;

import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.shopback.assignment.movies.utilities.ReleaseDateComparator;
import com.shopback.assignment.movies.detail.DetailActivity;
import com.sunilsahoo.inventorycontroller.Controller;
import com.sunilsahoo.inventorycontroller.ITaskListener;
import com.sunilsahoo.inventorycontroller.MoviesListController;
import com.sunilsahoo.inventorycontroller.entity.MetaInfo;
import com.sunilsahoo.inventorycontroller.entity.Movie;
import com.sunilsahoo.networkmanager.beans.NetResponse;
import com.sunilsahoo.viewmodelbinding.common.ViewModel;
import com.shopback.assignment.movies.BR;
import com.shopback.assignment.movies.common.MessageHelper;

import java.util.Collections;
import java.util.List;


public class MoviesViewModel extends ViewModel {

    public ObservableField<Boolean> showProgress = new ObservableField<>(false);
    private String title = "";
    private ObservableList<Movie> favoriteList = new ObservableArrayList<>();
    private ITaskListener callback ;
    private MessageHelper messageHelper;

    private int currentPageNumber;
    private final Controller controller = new MoviesListController();

    public MoviesViewModel(int variableId, MessageHelper messageHelper) {
        super(variableId);
        this.messageHelper = messageHelper;
        this.callback = new
                GetItemsInteractorCallback();
        this.addOnPropertyChangedCallback(new PropertyChangeCallback());
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public ObservableList<Movie> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<Movie> favoriteList) {
        this.favoriteList.addAll(favoriteList);
        Collections.sort(this.favoriteList, new ReleaseDateComparator());
    }


    public void onItemClicked(int position) {
        launchDetailScreen(this.favoriteList.get(position));
    }

    public void launchDetailScreen(Movie movie) {
        Intent intent = new Intent(getView().getActivity(), DetailActivity.class);
        intent.putExtra("ID", movie.getId());
        getView().getActivity().startActivity(intent);
    }

    public void getItems() {
        showProgress.set(true);
        controller.getResults(callback, String.valueOf(++currentPageNumber));
    }


    public void onFailure(String message) {
        showProgress.set(false);
        messageHelper.showMessage(message);
    }

    class GetItemsInteractorCallback implements ITaskListener {


        @Override
        public void onSuccess(NetResponse response) {
            MetaInfo metaInfo = (MetaInfo) response.getResponse();
            setFavoriteList(metaInfo.getResults());
            showProgress.set(false);
        }

        @Override
        public void onError(String error, int code) {
            onFailure(error);
        }
    }

    class PropertyChangeCallback extends OnPropertyChangedCallback {

        @Override
        public void onPropertyChanged(Observable observable, int i) {
            if (i == BR.title) {
                getItems();
            }
        }
    }
}


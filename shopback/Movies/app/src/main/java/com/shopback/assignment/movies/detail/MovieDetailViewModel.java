package com.shopback.assignment.movies.detail;

import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.shopback.assignment.movies.BR;
import com.shopback.assignment.movies.common.MessageHelper;
import com.sunilsahoo.inventorycontroller.Controller;
import com.sunilsahoo.inventorycontroller.ITaskListener;
import com.sunilsahoo.inventorycontroller.MoviesDetailController;
import com.sunilsahoo.inventorycontroller.entity.MovieDetail;
import com.sunilsahoo.networkmanager.beans.NetResponse;
import com.sunilsahoo.viewmodelbinding.common.ViewModel;

/**
 * Created by sunilkumarsahoo on 2/27/17.
 */

public class MovieDetailViewModel extends ViewModel{
    private MessageHelper messageHelper;
    private final Controller controller = new MoviesDetailController();
    private ITaskListener callback ;

    @Bindable
    public MovieDetail movieDetail;
    public ObservableField<Boolean> showProgress = new ObservableField<>(false);

    public MovieDetailViewModel(int variableId, MessageHelper messageHelper) {
        super(variableId);
        showProgress.set(false);
        this.callback = new GetMovieDetailCallback();
        this.messageHelper = messageHelper;
    }

    public void getMovieDetail(String id){
        showProgress.set(true);
        controller.getResults(callback, id);
    }

    public void onBooked() {
        if (messageHelper != null) {
            messageHelper.showMessage("Book Now clicked");
        }
    }

    public void onFailure(String message) {
        showProgress.set(false);
        messageHelper.showMessage(message);
    }

    class GetMovieDetailCallback implements ITaskListener {


        @Override
        public void onSuccess(NetResponse response) {
            movieDetail = (MovieDetail) response.getResponse();

            String imageUrl = movieDetail.getBackdropPath();
            ((DetailActivity)getView().getActivity()).setImage(imageUrl);

            showProgress.set(false);
            notifyPropertyChanged(BR.movieDetail);
        }

        @Override
        public void onError(String error, int code) {
            onFailure(error);
        }
    }
}

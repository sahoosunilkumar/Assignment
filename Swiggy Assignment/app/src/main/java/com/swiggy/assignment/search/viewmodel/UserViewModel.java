package com.swiggy.assignment.search.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.swiggy.assignment.common.repository.State;
import com.swiggy.assignment.search.model.ApiResponse;
import com.swiggy.assignment.search.model.Variation;
import com.swiggy.assignment.search.repository.CartEvaluator;
import com.swiggy.assignment.search.repository.ISearchRepositoryCallback;
import com.swiggy.assignment.search.repository.SearchRepository;
import com.swiggy.assignment.search.repository.SearchRepositoryImpl;

public class UserViewModel extends ViewModel implements ISearchRepositoryCallback {

    public ObservableBoolean showError = new ObservableBoolean();
    public ObservableField<Boolean> showProgress = new ObservableField<>(false);
    private MediatorLiveData<ApiResponse> mApiResponse;
    private SearchRepository mIssueRepository;
    private final CartEvaluator cartEvaluator = new CartEvaluator();

    public UserViewModel() {
        mApiResponse = new MediatorLiveData<>();
        mIssueRepository = new SearchRepositoryImpl(this);
    }

    @NonNull
    public LiveData<ApiResponse> getApiResponse() {
        return mApiResponse;
    }

    public LiveData<ApiResponse> loadVariants() {
        showProgress.set(true);
        mApiResponse.addSource(
                mIssueRepository.getVariants(),
                apiResponse -> {
                    showProgress.set(false);
                    mApiResponse.setValue(apiResponse);
                }
        );
        return mApiResponse;
    }

    @Override
    public void onStateChanged(int state) {
        showProgress.set(state == State.IN_PROGRESS);
        showError.set(state == State.FAILURE);
    }

    public boolean addToCart(Variation variation) {
        cartEvaluator.getCartList().add(variation);
        boolean existInExcludeGroup = false;
        int positionInExcludeGroup = cartEvaluator.existInExcludeGroup(variation, mApiResponse.getValue().getIssues().getVariants().getExcludeList());
        if (positionInExcludeGroup != -1) {
            existInExcludeGroup = cartEvaluator.existInExcludeGroup(positionInExcludeGroup, cartEvaluator.getCartList(), mApiResponse.getValue().getIssues().getVariants().getExcludeList());
        }
        return existInExcludeGroup;
    }
}

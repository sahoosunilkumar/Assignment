package com.iec.locationfinder;

import android.app.Fragment;

import com.sunilsahoo.inventorycontroller.entity.Result;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 12/21/16.
 */

public abstract class BaseFragment extends Fragment {
    private static List<Result> results;

    protected abstract void refresh();

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}

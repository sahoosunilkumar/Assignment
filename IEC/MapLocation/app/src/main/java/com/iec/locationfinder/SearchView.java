package com.iec.locationfinder;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by sunilkumarsahoo on 12/21/16.
 */

public class SearchView extends RelativeLayout implements View.OnClickListener {
    private ImageView searchIV;
    private ISearchListener searchListener;
    private EditText searchText;
    private View parentView;

    public SearchView(Context context) {
        super(context);
        initialize(context);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
        parentView = LayoutInflater.from(context).inflate(R.layout
                .search_layout, this);
        searchIV = (ImageView) parentView.findViewById(R.id.searchIV);
        searchText = (EditText) parentView.findViewById(R.id.searchText);
        searchIV.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (searchListener != null) {
            searchListener.onSearch(searchText.getText().toString(),
                    parentView);
        }
    }

    /**
     * set hint
     *
     * @param hint
     */
    public void setHint(String hint) {
        searchText.setHint(hint);
    }

    public void setOnSearchListener(ISearchListener iSearchListener) {
        searchListener = iSearchListener;
    }

    public interface ISearchListener {
        void onSearch(String query, View view);
    }
}

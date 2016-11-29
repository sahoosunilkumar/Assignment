package com.landmarkshopping.assignment.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.landmarkshopping.assignment.Constants;
import com.landmarkshopping.assignment.R;
import com.landmarkshopping.assignment.model.GridDetail;
import com.landmarkshopping.assignment.viewmodel.ShoppingFragmentViewModel;

public class GridFragment extends Fragment implements OnItemClickListener {

    private int mSelectedPosition = Constants.EOF;
    private GridView mGridView = null;
    private GridImageAdapter mGridAdapter = null;
    private GridDetail mGridDetail = null;
    private View gridContainerView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSelectedPosition = Constants.EOF;
        mGridDetail = (GridDetail) getArguments()
                .getSerializable(Constants.KEY_BUNDLE_LAUNCHER_DETAIL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Log.d("GridFragment", "inside oncreateview");
        mSelectedPosition = Constants.EOF;
        gridContainerView = inflater.inflate(R.layout.grid_container,
                container, false);
        mGridAdapter = new GridImageAdapter(getActivity(),
                ShoppingFragmentViewModel.getItemList(),
                mGridDetail.getStartPosition(),
                mGridDetail.getEndPosition());
        mGridView = (GridView) gridContainerView.findViewById(R.id.grid_view);

        if (ShoppingFragmentViewModel.getItemList() == null) {
            return gridContainerView;
        }

        mGridView.setAdapter(mGridAdapter);
        mGridView.setNumColumns(mGridDetail.getColumnCount());
        mGridView.setOnItemClickListener(this);
        return gridContainerView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGridDetail = null;
        mGridAdapter = null;
        mGridView = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        mSelectedPosition = (int) id;
        Toast.makeText(getActivity(), "item clicked ", Toast.LENGTH_SHORT).show();

    }
}

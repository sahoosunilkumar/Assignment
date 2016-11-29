package com.landmarkshopping.assignment.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.landmarkshopping.assignment.R;
import com.landmarkshopping.assignment.databinding.ShoppingFragmentBinding;
import com.landmarkshopping.assignment.model.GridDetail;
import com.landmarkshopping.assignment.model.Product;
import com.landmarkshopping.assignment.viewmodel.ShoppingFragmentViewModel;

import java.util.List;

public class ShoppingFragment extends Fragment implements
        ShoppingFragmentViewModel.DataListener {

    private static final String TAG = ShoppingFragment.class.getName();
    private static final int UPDATE_UI = 1;
    private static ShoppingFragment instance;
    ShoppingFragmentViewModel shoppingFragmentViewModel;
    private ViewPager mPager;
    private LinearLayout mPageIndicatorContainerLL;
    //    private ProductListPagerAdapter mAdapter;
    private PagerIndicator mIndicator;
    //    private List<Fragment> fragments = new ArrayList<>();
//    List<Product> productList;
    private CarouselGridPagerAdapter mGridPagerAdapter;
    private ShoppingFragmentBinding binding;

    public static ShoppingFragment getInstance() {
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout
                .shopping_fragment, container, false);
        shoppingFragmentViewModel = new ShoppingFragmentViewModel(getActivity
                (), this);
        binding.setViewModel(shoppingFragmentViewModel);
        View view = inflater.inflate(R.layout.shopping_fragment, container,
                false);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPageIndicatorContainerLL = (LinearLayout) view.findViewById(R.id
                .pageIndicatorContainer);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mIndicator != null) {
            mIndicator.cleanup();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        handler.sendEmptyMessage(UPDATE_UI);
//        initializePageAppView();
        shoppingFragmentViewModel.doUpdateProductList();
    }

    private final android.os.Handler handler = new android.os.Handler() {
        public void handleMessage(Message msg) {
            initializePageAppView(ShoppingFragmentViewModel.getItemList());
        }
    };

    private void initializePageAppView(List<Product> productList) {
//        Log.d(TAG, "inside initializepageappview start: " + productList);
        mPager.removeAllViews();
        GridDetail gridDetail = setupGridDetails(productList);
        mGridPagerAdapter = new CarouselGridPagerAdapter(
                getActivity().getSupportFragmentManager(), gridDetail,
                productList);
        mPager.setAdapter(mGridPagerAdapter);
        initializeDynamicPaginationView(gridDetail.getTotalNoOfFragments());
//        mPager.setOnPageChangeListener(this);
        mPager.invalidate();
//        Log.d(TAG, "inside initializepageappview end: " + productList);
    }

    private void initializeDynamicPaginationView(int totalNoOfFragments) {
        mIndicator = new PagerIndicator(getActivity(),
                mPageIndicatorContainerLL, mPager, R.drawable.indicator_circle);
        mIndicator.setPageCount(totalNoOfFragments);
        mIndicator.show();
    }

    private GridDetail setupGridDetails(List<Product> gridList) {
        int numItemsPerPage = 0;
        int numFragments = 0;
        GridDetail gridDetails = new GridDetail();
        if (gridList != null) {
            int rowCount = getResources().getInteger(
                    R.integer.row_count);
            int colCount = getResources().getInteger(
                    R.integer.column_count);
            int numTopics = gridList.size();
            gridDetails.setRowCount(rowCount);
            gridDetails.setColumnCount(colCount);
            numItemsPerPage = rowCount * colCount;
            numFragments = numTopics / numItemsPerPage;
            if (numTopics % numItemsPerPage != 0)
                numFragments++;
        }
        gridDetails.setTotalNoOfFragments(numFragments);
        gridDetails.setItemCountPerFragment(numItemsPerPage);

        return gridDetails;
    }

    @Override
    public void onProductsUpdated() {
        //TODO
        Log.d(TAG, "inside on products updated ");
//        initializePageAppView(repositories);
        handler.sendEmptyMessage(UPDATE_UI);
    }
}

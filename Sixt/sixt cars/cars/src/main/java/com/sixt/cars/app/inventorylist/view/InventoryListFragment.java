package com.sixt.cars.app.inventorylist.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixt.cars.R;
import com.sixt.cars.app.home.BaseFragment;
import com.sixt.cars.app.home.view.HomeView;
import com.sixt.cars.app.inventorylist.presenter.IInventoryListPresenter;
import com.sixt.cars.app.inventorylist.presenter.InventoryListPresenter;
import com.sixt.inventorycontroller.entity.CarInfo;

import java.util.ArrayList;


public class InventoryListFragment extends BaseFragment {
    private static final String TAG = "PlayerInfoFragment";
    private IInventoryListPresenter presenter;
    private RecyclerView list;
    private HomeView homeView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeView = ((HomeView) getActivity());
        presenter = new InventoryListPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_list, container,
                false);
        renderView(view);
        return view;
    }

    public void renderView(View view) {
        list = (RecyclerView) view.findViewById(R.id.list);

        list.setHasFixedSize(true);
        list.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));

        list.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void update(ArrayList<CarInfo> carInfoList) {
        InventoryAdapter adapter = new InventoryAdapter(carInfoList,
                new InventoryAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(CarInfo info) {


                    }
                });

        list.setAdapter(adapter);
    }
}

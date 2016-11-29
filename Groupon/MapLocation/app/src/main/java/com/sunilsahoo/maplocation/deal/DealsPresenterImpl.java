package com.sunilsahoo.maplocation.deal;

import com.google.android.gms.maps.model.LatLng;
import com.sunilsahoo.maplocation.model.Deal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunilkumarsahoo on 10/11/16.
 */
public class DealsPresenterImpl implements DealsPresenter {
    private DealsView dealsView;
    private List<Deal> dealList = new ArrayList<>();
    DealsPresenterImpl(DealsView dealsView){
        this.dealsView = dealsView;
    }
    @Override
    public void onSuccess(List<Deal> dealList) {
        dealsView.hideProgressBar();
        dealsView.refreshList(dealList);
    }

    @Override
    public void onDestroy() {
        dealList.clear();
        dealList = null;
    }

    @Override
    public void getItems() {
        dealsView.showProgressBar();
        dealList = new ArrayList();
        dealList.add(new Deal("1", "Earn Upto Rs 5000 Discount Voucher On Referring Friends", "Fashion, Kids Lifestyle", "Hot Deal", "Jabong is giving away discount voucher upto Rs 5000", "Ends On 30 Nov 2016", new LatLng(17.43126076,78.44504345)));

        dealList.add(new Deal("1",
                "Buy 3 Get 1 For Free On Women's Clothing & Undergarments", "Fashion, Women's Innerwear", "Hot Deal", "Look beautiful within by donning this beautiful undergarments", "Ends On 30 Nov 2016", new LatLng(17.43617404,78.39406002)));

        dealList.add(new Deal("1", "Upto 60% OFF On Puma Products", "Footwear", "60% OFF", "Shop from widest collection of Puma", "Ends On 30 Nov 2016", new LatLng(17.4399408,78.40075481)));

        dealList.add(new Deal("1", "Brand Day Sale - Flat 50% OFF on 1500+ Products", "Fashion, Men's Lifestyle", "50% OFF", "Style up this monsoon by donning high fashion apparels at Jabong", "Ends On 21 Oct 2016", new LatLng(17.43912195,78.38822353)));

        dealList.add(new Deal("1", "Buy 2 & Get 20% OFF On Selected Products", "Fashion, Men's Lifestyle", "Hot Deal", "Buy 2 & Get 20% OFF On Selected Products from", "Ongoing Offer", new LatLng(17.43012195,78.3811)));
        onSuccess(dealList);
    }
}

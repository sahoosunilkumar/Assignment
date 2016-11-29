package com.sunilsahoo.maplocation.coupon;

import com.google.android.gms.maps.model.LatLng;
import com.sunilsahoo.maplocation.model.Coupon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunilkumarsahoo on 10/11/16.
 */
public class CouponPresenterImpl implements CouponPresenter{
    private CouponView couponView;
    private List<Coupon> couponList = new ArrayList();
    CouponPresenterImpl(CouponView couponView){
        this.couponView = couponView;
    }
    @Override
    public void onSuccess(List<Coupon> couponList) {
        couponView.hideProgressBar();
        couponView.refreshList(couponList);
    }

    @Override
    public void onDestroy() {
        couponList.clear();
        couponList = null;
    }

    @Override
    public void getItems() {
        couponView.showProgressBar();
        couponList.clear();
        couponList.add(new Coupon("1", "Get Flat 10% OFF On No Minimum Orders", "Fashion, Kids Lifestyle", "30% OFF", "Use the above Jabong Coupon", "COUPONJBNG1", "Ends On 31 Oct 2016", new LatLng(17.4411,78.3911)));

        couponList.add(new Coupon("2", "Flat Rs 500 OFF On Minimum Purchase Of Rs 1299 & Above", "Fashion, Kids Lifestyle", "Rs.500 OFF", "Use the above Jabong Coupon", "COUPONJBNG2", "Ends On 31 Oct 2016", new LatLng(17.45353324,78.40521801)));

        couponList.add(new Coupon("3", "ICICI Bank Offer: Flat Rs 250 OFF", "Fashion, Men's Lifestyle", "Rs.250 OFF", "Use the above Jabong Coupon", "COUPONICICI1", "Ends On 31 Oct 2016", new LatLng(17.44698279,78.41483105)));

        couponList.add(new Coupon("4", "Get Extra 15% OFF On American Express Cards Payment", "Fashion, Footwear", "15% OFF", "Latest offer for all American Express Cards", "COUPONAMEX", "Ends On 29 Oct 2016", new LatLng(17.44174227,78.42444408)));

        couponList.add(new Coupon("5", "Flat Rs 500 OFF On Minimum Purchase Of Rs 1299 & Above", "Fashion, Kids Lifestyle", "30% OFF", "Make a style statement with the new arrival at Jabong", "COUPONJBNG3", "Ends On 31 Oct 2016", new LatLng(17.43781177,78.43955029)));
        onSuccess(couponList);
    }
}

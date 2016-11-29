package com.sunilsahoo.maplocation.maplocator;

import com.google.android.gms.maps.model.LatLng;
import com.sunilsahoo.maplocation.deal.DealsView;
import com.sunilsahoo.maplocation.model.Coupon;
import com.sunilsahoo.maplocation.model.Deal;
import com.sunilsahoo.maplocation.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunilkumarsahoo on 10/11/16.
 */
public class ItemPresenterImpl implements ItemPresenter {
    private ItemView itemView;
    private List<Item> itemList = new ArrayList<>();
    ItemPresenterImpl(ItemView itemView){
        this.itemView = itemView;
    }
    @Override
    public void onSuccess(List<Item> itemList) {
        itemView.refreshList(itemList);
    }

    @Override
    public void onDestroy() {
        itemList.clear();
        itemList = null;
    }

    @Override
    public void getItems() {
        itemList = new ArrayList();
        //add dummy coupons
        itemList.add(new Coupon("1", "Get Flat 10% OFF On No Minimum Orders", "Fashion, Kids Lifestyle", "30% OFF", "Use the above Jabong Coupon", "COUPONJBNG1", "Ends On 31 Oct 2016", new LatLng(17.4411,78.3911)));

        itemList.add(new Coupon("2", "Flat Rs 500 OFF On Minimum Purchase Of Rs 1299 & Above", "Fashion, Kids Lifestyle", "Rs.500 OFF", "Use the above Jabong Coupon", "COUPONJBNG2", "31 Oct 2016", new LatLng(17.45353324,78.40521801)));

        itemList.add(new Coupon("3", "ICICI Bank Offer: Flat Rs 250 OFF", "Fashion, Men's Lifestyle", "Rs.250 OFF", "Use the above Jabong Coupon", "COUPONICICI1", "Ends On 31 Oct 2016", new LatLng(17.44698279,78.41483105)));

        itemList.add(new Coupon("4", "Get Extra 15% OFF On American Express Cards Payment", "Fashion, Footwear", "15% OFF", "Latest offer for all American Express Cards", "COUPONAMEX", "Ends On 29 Oct 2016", new LatLng(17.44174227,78.42444408)));

        itemList.add(new Coupon("5", "Flat Rs 500 OFF On Minimum Purchase Of Rs 1299 & Above", "Fashion, Kids Lifestyle", "30% OFF", "Make a style statement with the new arrival at Jabong", "COUPONJBNG3", "Ends On 31 Oct 2016", new LatLng(17.43781177,78.43955029)));

        //add dummy deals
        itemList.add(new Deal("1", "Earn Upto Rs 5000 Discount Voucher On Referring Friends", "Fashion, Kids Lifestyle", "Hot Deal", "Jabong is giving away discount voucher upto Rs 5000", "Ends On 30 Nov 2016", new LatLng(17.43126076,78.44504345)));

        itemList.add(new Deal("1",
                "Buy 3 Get 1 For Free On Women's Clothing & Undergarments", "Fashion, Women's Innerwear", "Hot Deal", "Look beautiful within by donning this beautiful undergarments", "Ends On 30 Nov 2016", new LatLng(17.43617404,78.39406002)));

        itemList.add(new Deal("1", "Upto 60% OFF On Puma Products", "Footwear", "60% OFF", "Shop from widest collection of Puma", "Ends On 30 Nov 2016", new LatLng(17.4399408,78.40075481)));

        itemList.add(new Deal("1", "Brand Day Sale - Flat 50% OFF on 1500+ Products", "Fashion, Men's Lifestyle", "50% OFF", "Style up this monsoon by donning high fashion apparels at Jabong", "Ends On 21 Oct 2016", new LatLng(17.43912195,78.38822353)));

        itemList.add(new Deal("1", "Buy 2 & Get 20% OFF On Selected Products", "Fashion, Men's Lifestyle", "Hot Deal", "Buy 2 & Get 20% OFF On Selected Products from", "Ongoing Offer", new LatLng(17.43012195,78.3811)));
        onSuccess(itemList);
    }
}

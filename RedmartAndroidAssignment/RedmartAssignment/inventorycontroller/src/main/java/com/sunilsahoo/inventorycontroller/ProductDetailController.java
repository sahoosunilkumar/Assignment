package com.sunilsahoo.inventorycontroller;

import com.sunilsahoo.inventorycontroller.entity.ProductDetailResponse;
import com.sunilsahoo.inventorycontroller.utils.ControllerConstants;

public final class ProductDetailController extends Controller{
    private static final String END_POINT = "https://api.redmart.com/v1.5.7/catalog/products/%1s";


    private static final String TAG = ProductDetailController.class.getName();


    public ProductDetailController() {
        super();
    }

    @Override
    public void getResults(ITaskListener taskListener, Object...
            additionalInfo) {
        super.setEntity(ProductDetailResponse.class);

        String uri = String.format(END_POINT, additionalInfo[0]);
        super.execute(ControllerConstants.RequestCodes.GET_ITEM_DETAIL, uri, ControllerConstants.HttpMethods.HTTP_GET, null, taskListener);

    }
}

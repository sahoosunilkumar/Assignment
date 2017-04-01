package com.sunilsahoo.inventorycontroller;

import com.sunilsahoo.inventorycontroller.entity.ProductListResponse;
import com.sunilsahoo.inventorycontroller.utils.ControllerConstants;

public final class ProductListController extends Controller{
    private static final String END_POINT = "https://api.redmart.com/v1.5.7/catalog/search?page=%1s&pageSize=20";


    private static final String TAG = ProductListController.class.getName();


    public ProductListController() {
        super();
    }

    @Override
    public void getResults(ITaskListener taskListener, Object...
            additionalInfo) {
        super.setEntity(ProductListResponse.class);

        String uri = String.format(END_POINT, additionalInfo[0]);
        super.execute(ControllerConstants.RequestCodes.GET_ITEM_LIST, uri, ControllerConstants.HttpMethods.HTTP_GET, null, taskListener);

    }
}

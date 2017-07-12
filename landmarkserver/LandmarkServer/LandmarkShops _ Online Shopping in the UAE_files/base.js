define(['lib/strands/data_mapper', 'underscore'], function() {
    'use strict';

    LMS.StrandsTrack = (function() {
        function StrandsTrack() {}

        StrandsTrack.initialize = function() {
            var apiKey, e;
            apiKey = LMS.pageData.STRANDS_APIKEY;
            require(['strands_track'], function() {
                if (apiKey && !_.isEmpty(apiKey.trim())) {
                    try {
                        if (_.isUndefined(window.StrandsTrack)) {
                            window.StrandsTrack = [];
                        }

                        if (_.isFunction(LMS[LMS.pageData.pageJsName].getStrandsBlocks)){

                        	var requiredStrandsBlocks = LMS[LMS.pageData.pageJsName].getStrandsBlocks();

                        	$.each(requiredStrandsBlocks,function( index, value){
                        		 SBS.Recs.setRenderer(LMS[LMS.pageData.pageJsName].renderStrandsProducts, value);
                        	})
                    	}


                        SBS.Worker.go(apiKey);
                        LMS.pageData.startStrandsRender = new Date().getTime();
                        if (_.isFunction(LMS[LMS.pageData.pageJsName].handleStrandsRecommendation)) {
                            LMS[LMS.pageData.pageJsName].handleStrandsRecommendation();
                        }
                    } catch (_error) {
                        e = _error;
                    }
                }
            });
        };

        StrandsTrack.initializeForLogin = function() {

            var apiKey, e;
            apiKey = LMS.pageData.STRANDS_APIKEY;
            require(['strands_track'], function() {
                if (apiKey && !_.isEmpty(apiKey.trim())) {
                    try {
                        if (_.isUndefined(window.StrandsTrack)) {
                            window.StrandsTrack = [];
                        }

                        SBS.Worker.go(apiKey);
                        LMS.pageData.startStrandsRender = new Date().getTime();

                    } catch (_error) {
                        e = _error;
                    }
                }
            });
        };

        StrandsTrack.pushVisitedEvent = function(itemId) {
            window.StrandsTrack.push({
                event: 'visited',
                item: '' + itemId
            });
        };

        StrandsTrack.pushUserLoggedInEvent = function(userId) {
            window.StrandsTrack.push({
                event: 'userlogged',
                user: '' + userId
            });
        };

        StrandsTrack.pushUserSaleEvent = function(_orderData) {
            var entry, i, item, items, len, ref;
            items = [];
            ref = _orderData.entries;
            for (i = 0, len = ref.length; i < len; i++) {
                entry = ref[i];

                item = {
                    id: entry.baseProductPK,
                    price: entry.totalPrice.value,
                    quantity: entry.quantity
                };
                items.push(item);
            }
            window.StrandsTrack.push({
                event: 'purchased',
                orderid: '' + _orderData.code,
                items: items
            });
        };

        StrandsTrack.pushUpdateshoppingcart = function(cartItems) {

            var items = [];
            for (var i = 0; i < cartItems.length; i++) {
                items.push(cartItems[i].baseProductPK);
            }

            items = $.unique(items);

            window.StrandsTrack.push({
                event: 'updateshoppingcart',
                items: items
            });
        };

        return StrandsTrack;

    })();
    return LMS.StrandsTrack;
});

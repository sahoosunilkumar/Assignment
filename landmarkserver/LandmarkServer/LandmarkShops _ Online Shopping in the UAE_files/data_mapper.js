define([], function() {
    'use strict';

    LMS.StrandsTrackDataMapper = (function() {
        function StrandsTrackDataMapper() {}

        StrandsTrackDataMapper.mapProductGrid = function(rec_info) {
            var i, item, len, pdct, product, products, ref, wasprice, employeeprice, reviewavgrating;
            products = [];
            ref = rec_info.recommendations;
            for (i = 0, len = ref.length; i < len; i++) {
                pdct = ref[i];
                product = {};
                item = pdct.metadata;
                product.name = item.name;
                product.url = item.link;
                product.concept = item.properties.concept[0] || '';
                product.tpl = rec_info.tpl;
                product.brand = _.isUndefined(item.properties.brand) ? '' : ' - ' + (item.properties.brand[0] || '');
                product.brand = item.properties.concept[0] + product.brand;
                product.productCode = item.properties.code[0] || '';
                product.baseProductPK = pdct.itemId;
                wasprice = _.isUndefined(item.properties.wasprice) ? 0 : item.properties.wasprice[0];
                employeeprice = _.isUndefined(item.properties.employeeprice) ? 0 : item.properties.employeeprice[0] ;
                reviewavgrating = _.isUndefined(item.properties.reviewavgrating) ? 0 : item.properties.reviewavgrating[0];

                if (parseFloat(wasprice) !== item.price) {
                    product.basePrice = Handlebars.helpers.format_money(wasprice, 2, false);
                }

                if (LMS.pageData.isEmployee === 'true' && parseFloat(employeeprice) !== item.price) {
                    product.basePrice = Handlebars.helpers.format_money(wasprice, 2, false);
                    product.employeePrice = Handlebars.helpers.format_money(employeeprice, 2, false);
                }
                product.price = Handlebars.helpers.format_money(item.price, 2, false);
                product.media = {
                    url: item.picture,
                    title: item.name
                };

                product.badges = generateBadges(item.properties);

                if (item.properties.reviewavgrating) {
                    product.averageRating = Math.floor(reviewavgrating);
                }
                products.push(product);
            }
            return products;
        };

        var generateBadges = function(properties) {

            var i = 1,
                badges = [],
                positionFilled = [],
                positionSelected;

            while (!_.isUndefined(properties['badgecode' + i])) {
                positionSelected = (properties['badgeposition' + i] || []).pop();

                if (_.indexOf(positionFilled, positionSelected) === -1 && (properties['badgevisible' + i][0]) === 'true') {
                    positionFilled.push(positionSelected);
                    badges.push({
                        'code': (properties['badgecode' + i] || []).pop() || '',
                        'title': (properties['badgetitle' + i] || []).pop() || '',
                        'position': positionSelected,
                        'cssStyle': (properties['badgecssstyle' + i] || []).pop() || ''
                    });
                }
                i++;
            }

            return badges;
        };

        return StrandsTrackDataMapper;

    })();
    return LMS.StrandsTrackDataMapper;
});

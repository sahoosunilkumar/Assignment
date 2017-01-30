package com.landmarkshopping.network;

import android.util.Log;

import com.landmarkshopping.assignment.model.Product;
import com.landmarkshopping.network.model.Request;
import com.landmarkshopping.network.model.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Responsibility : uses Jsoup parser to parse html
 * Created by sunilkumarsahoo on 9/16/16.
 */
public class JSoupParser {
    public void parse(Request request) {
        Document doc = null;
        Response response = null;
        try {
            doc = Jsoup.connect(request.getUrl()).get();
            response = parseProductDetail(doc, request);
            if (request.getCallback() != null) {
                request.getCallback().onSuccess(response);
            }
        } catch (Exception ex) {
            if (request.getCallback() != null && response != null) {
                request.getCallback().onError(response.getInfo(), response
                        .getResponseCode());
            }
        } finally {
            //TODO
        }
    }


    /**
     * parse carousel product details from html doc
     * @param doc
     * @param request
     * @return
     */
    private Response parseProductDetail(Document doc, Request request) {

        Elements carouselDtlElems = doc.getElementsByClass("products-carousel");
        Response response = new Response(request.getRequestId());
        ArrayList<Product> productArrayList = new ArrayList<>();
//            Product product = new Product();
        for (Element carouselDtl : carouselDtlElems) {
            Product product = new Product();
            product.setTitle(carouselDtl.getElementsByClass("title").get(0)
                    .text());
            product.setName(carouselDtl.getElementsByClass("title").get(1)
                    .text());
            product.setImagePath(carouselDtl.getElementsByClass("img-holder")
                    .get(0).getElementsByTag("img").attr("data-src"));

            product.setBadge(carouselDtl.getElementsByClass("img-holder").get
                    (0).getElementsByClass("badge").text());
            product.setPrice(carouselDtl.getElementsByClass("price").first()
                    .text());
            product.setDelPrice(carouselDtl.getElementsByClass("price").first
                    ().getElementsByTag("del").first().text());
            product.setPrice(deleteString(new StringBuffer(carouselDtl
                    .getElementsByClass("price").first().text()), product
                    .getDelPrice()));
            productArrayList.add(product);
        }
        response.setData(productArrayList);
        return response;

    }

    /**
     * deletes substring from string
     * @param strbuf
     * @param substr
     * @return
     */
    private String deleteString(StringBuffer strbuf, String substr) {
        if (strbuf == null) {
            return null;
        }
        if (substr == null) {
            return strbuf.toString();
        }
        int i = strbuf.lastIndexOf(substr);
        if (i != -1) {
            strbuf.delete(i, i + strbuf.length());
        }
        return strbuf.toString();
    }
}

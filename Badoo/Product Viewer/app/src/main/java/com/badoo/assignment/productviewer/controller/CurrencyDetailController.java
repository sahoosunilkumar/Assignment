package com.badoo.assignment.productviewer.controller;

import android.content.Context;
import android.util.Log;

import com.badoo.assignment.productviewer.model.Currency;
import com.badoo.assignment.productviewer.model.CurrencyMapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class CurrencyDetailController {
    public final static CurrencyDetailController INSTANCE = new
            CurrencyDetailController();
    private final String fileName = "rates.json";
    private String TAG = "CurrencyDetailController";
    private CurrencyMapping currencyMapping;

    /**
     * sends getdevice request to server
     *
     * @param taskListener
     */
    public void getCurrencyList(final Context context, final String
            parentDirectory, final ITaskListener taskListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream is = null;
                try {
                    is = context.getAssets().open(parentDirectory + "/" +
                            fileName);

                    int size = is.available();

                    byte[] buffer = new byte[size];

                    is.read(buffer);

                    is.close();

                    String json = new String(buffer, "UTF-8");
                    Response response = new Response();

                    currencyMapping = new CurrencyMapping();
                    currencyMapping.setCurrencyMap(createMap(
                            (Currency[]) Parser.INSTANCE.parse(json,
                                    Currency[].class)));
                    response.setResponse(currencyMapping);
                    if (taskListener != null) {
                        taskListener.onSuccess(response);
                    }
                } catch (Exception ex) {
                    if (taskListener != null) {
                        Log.e(TAG, ex.getMessage());
                        taskListener.onError("ex.getMessage()", 400);

                    }
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();
    }

    private HashMap<String, List<Currency>> createMap
            (Currency[] currencyArr) {
        HashMap<String, List<Currency>> currencyMap = new HashMap<>();
        for (Currency currency : currencyArr) {
            currencyMap.put(currency.getFrom(), addToList(currencyMap
                    .get(currency.getFrom()), currency));
        }
        return currencyMap;
    }

    private HashMap<String, Float> calculateDistance(Currency[] currencyArr){
        CurrencyConverterNew graphObj = new CurrencyConverterNew();
        for(Currency currency : currencyArr){
            graphObj.
        }
        graphObj.addNode("GBP");
        graphObj.addNode("USD");
        graphObj.addNode("CAD");
        graphObj.addNode("AUD");
        graphObj.addEdge("GBP", "USD", 1.3f);
        graphObj.addEdge("USD", "GBP", 0.77f);
        graphObj.addEdge("USD", "CAD", 1.09f);
        graphObj.addEdge("CAD", "USD", 0.92f);
        graphObj.addEdge("GBP", "AUD", 0.83f);
        graphObj.addEdge("AUD", "GBP", 1.2f);
        System.out.println(graphObj);
        HashMap<String, Float> distance = graphObj.findShortestDijkstra("GBP");
    }

    private List<Currency> addToList(List<Currency> list,
                                     Currency currency) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(currency);
        return list;
    }

    /**
     * returns cached currencys
     *
     * @return
     */
    public CurrencyMapping getCurrencyMapping() {
        return currencyMapping;
    }

}

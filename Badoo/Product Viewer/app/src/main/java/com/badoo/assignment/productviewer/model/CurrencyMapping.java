package com.badoo.assignment.productviewer.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sunilkumarsahoo on 11/16/16.
 */
public class CurrencyMapping {
    private HashMap<String, List<Currency>> currencyMap;
    private String[] key = {};

    public HashMap<String, List<Currency>> getCurrencyMap() {
        return currencyMap;
    }

    public void setCurrencyMap(HashMap<String, List<Currency>>
                                       currencyMap) {
        this.currencyMap = currencyMap;
    }

}

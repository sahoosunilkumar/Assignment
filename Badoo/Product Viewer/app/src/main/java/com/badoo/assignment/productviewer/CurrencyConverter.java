package com.badoo.assignment.productviewer;

import com.badoo.assignment.productviewer.model.Currency;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sunilkumarsahoo on 11/17/16.
 */
public class CurrencyConverter {
    private HashMap<String, List<Currency>> currencyMap;
    CurrencyConverter(HashMap<String, List<Currency>> currencyMap){
        this.currencyMap = currencyMap;
    }

    public HashMap<String, List<Currency>> getCurrencyMap() {
        return currencyMap;
    }
}

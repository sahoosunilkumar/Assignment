package com.badoo.assignment.productviewer;

import android.util.Log;

import com.badoo.assignment.productviewer.model.Currency;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by sunilkumarsahoo on 11/17/16.
 */
public class GBPConverter extends CurrencyConverter{

    private HashMap<String, Float> currencyMap = new HashMap<>();
    private static final String TO_CURRENCY = "GBP";

    public GBPConverter(HashMap<String, List<Currency>> currencyMap) {
        super(currencyMap);
        init();
    }

    private void init(){
        Set<String> key = getCurrencyMap().keySet();
        Iterator<String> keyItr = key.iterator();
        while(keyItr.hasNext()) {
            String keyStr = keyItr.next();
            if (keyStr.equals(TO_CURRENCY)) {
                currencyMap.put(keyStr, 1.0f);
            } else {
                int counter = 0;
                while(true) {

                    float tempinitValue = 1.0f;

                    String tempKey = getCurrencyMap().get(keyStr).get(counter).getTo();
                    tempinitValue = tempinitValue*Float.parseFloat(getCurrencyMap().get(keyStr).get(counter).getRate());
                    if(tempKey.equals(TO_CURRENCY) && !currencyMap.containsKey(keyStr)){
                        currencyMap.put(keyStr, tempinitValue);
                    }
                    counter++;
                    if(counter >=getCurrencyMap().get(keyStr).size()){
                        if(!currencyMap.containsKey(keyStr)) {
                            currencyMap.put(keyStr, tempinitValue);
                        }
                        break;
                    }
                }

            }
        }

    }

    public float get(String sourceCurrency){
        return currencyMap.get(sourceCurrency);
    }
    public String getBaseType(){
        return TO_CURRENCY;
    }



}

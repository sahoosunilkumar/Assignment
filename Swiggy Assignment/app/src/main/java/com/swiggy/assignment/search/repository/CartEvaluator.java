package com.swiggy.assignment.search.repository;

import com.swiggy.assignment.search.model.ExcludeList;
import com.swiggy.assignment.search.model.Variation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunilkumarsahoo on 12/8/17.
 */

public class CartEvaluator {

    private List<Variation> cartList = new ArrayList<>();

    public int existInExcludeGroup(Variation variation, List<List<ExcludeList>> excludeList){

        int matchId = -1;
        for(int i = 0; i<excludeList.size(); i++){
            for(int j=0;j<excludeList.get(i).size();j++){
                String varId = ((excludeList.get(i)).get(j)).getVariationId();
                if(varId.equals(variation.getId())){
                    matchId = i;
                    break;
                }
            }
        }
        return matchId;
    }

    public boolean existInExcludeGroup(int position, List<Variation> variationList, List<List<ExcludeList>> excludeList){
        int matchCount = 0;
        for (int j = 0; j < excludeList.get(position).size(); j++) {

            for(Variation variation : variationList) {
                if (excludeList.get(position).get(j).getVariationId().equals(variation.getId())) {
                    ++matchCount;
                }
            }
        }

        int totalexcludeListSize = excludeList.get(position).size();
        return matchCount == totalexcludeListSize;
    }

    public List<Variation> getCartList() {
        return cartList;
    }
}

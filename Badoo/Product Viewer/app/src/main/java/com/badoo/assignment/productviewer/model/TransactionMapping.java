package com.badoo.assignment.productviewer.model;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by sunilkumarsahoo on 11/16/16.
 */
public class TransactionMapping {
    private TreeMap<String, List<Transaction>> transactionMap;
    private String[] key = {};

    public TreeMap<String, List<Transaction>> getTransactionBeanList() {
        return transactionMap;
    }

    public void setTransactionBeanList(TreeMap<String, List<Transaction>>
                                               transactionMap) {
        this.transactionMap = transactionMap;
        if (this.transactionMap != null) {
            key = this.transactionMap.keySet().toArray(key);
        }
    }

    public String[] getKeys() {
        return key;
    }

    public String getKey(int position) {
        return key[position];
    }

    public List<Transaction> getList(int position) {
        return transactionMap.get(getKey(position));
    }
}

package com.badoo.assignment.productviewer.controller;

import android.content.Context;
import android.util.Log;

import com.badoo.assignment.productviewer.model.Transaction;
import com.badoo.assignment.productviewer.model.TransactionMapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public final class DeviceDetailController {
    public final static DeviceDetailController INSTANCE = new
            DeviceDetailController();
    private final String fileName = "transactions.json";
    TransactionMapping transactionMapping;
    private String TAG = "DeviceDetailController";

    private DeviceDetailController() {

    }

    /**
     * sends getdevice request to server
     *
     * @param taskListener
     */
    public void getTransactionList(final Context context, final String
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
//                    Log.d(TAG, "json file :"+json);
                    Response response = new Response();

                    transactionMapping = new TransactionMapping();
                    transactionMapping.setTransactionBeanList
                            (createTransactionMap(
                                    (Transaction[]) Parser.INSTANCE.parse(json,
                                            Transaction[].class)));
                    response.setResponse(transactionMapping);
                    taskListener.onSuccess(response);
                } catch (Exception ex) {
                    Log.e(TAG, "onError : " + ex.getMessage());
                    taskListener.onError(ex.getMessage(), 400);
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

    private TreeMap<String, List<Transaction>> createTransactionMap
            (Transaction[] transactionArr) {
        TreeMap<String, List<Transaction>> transactionMap = new TreeMap<>();
        for (Transaction transaction : transactionArr) {
            transactionMap.put(transaction.getSku(), addToList(transactionMap
                    .get(transaction.getSku()), transaction));
        }
        return transactionMap;
    }

    private List<Transaction> addToList(List<Transaction> list,
                                        Transaction transaction) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(transaction);
        return list;
    }

    /**
     * returns cached transactions
     *
     * @return
     */
    public TransactionMapping getTransactionList() {
        return transactionMapping;
    }

}

package com.landmarkshopping.assignment.model;

import java.io.Serializable;

public class GridDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    private int columnCount;
    private int rowCount;
    private int itemCountPerFragment;
    private int totalNoOfFragments;
    private int currentGridPosition;
    private int startPosition;
    private int endPosition;
    private String key = "";
    private GridDetail launcherGridDetail = null;


    public GridDetail() {
    }

    public GridDetail(GridDetail launcherGridDetail) {
        this.columnCount = launcherGridDetail.columnCount;
        this.rowCount = launcherGridDetail.rowCount;
        this.itemCountPerFragment = launcherGridDetail.itemCountPerFragment;
        this.startPosition = launcherGridDetail.startPosition;
        this.endPosition = launcherGridDetail.endPosition;
        this.currentGridPosition = launcherGridDetail.currentGridPosition;
        this.totalNoOfFragments = launcherGridDetail.totalNoOfFragments;
        this.key = launcherGridDetail.key;
        this.launcherGridDetail = launcherGridDetail.launcherGridDetail;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getItemCountPerFragment() {
        return itemCountPerFragment;
    }

    public void setItemCountPerFragment(int itemCountPerFragment) {
        this.itemCountPerFragment = itemCountPerFragment;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public int getTotalNoOfFragments() {
        return totalNoOfFragments;
    }

    public void setTotalNoOfFragments(int totalNoOfFragments) {
        this.totalNoOfFragments = totalNoOfFragments;
    }

    public int getCurrentGridPosition() {
        return currentGridPosition;
    }

    public void setCurrentGridPosition(int currentGridPosition) {
        this.currentGridPosition = currentGridPosition;
    }


}

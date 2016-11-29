package com.sunilsahoo.httpconnection.databinding;
import com.sunilsahoo.httpconnection.R;
import com.sunilsahoo.httpconnection.BR;
import android.view.View;
public class ForecastListItemBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.forecastIV, 4);
        sViewsWithIds.put(R.id.tempInfo, 5);
    }
    // views
    public final android.widget.TextView dateTV;
    public final android.widget.TextView dayTV;
    public final android.widget.ImageView forecastIV;
    private final android.widget.RelativeLayout mboundView0;
    public final android.widget.TextView tempInfo;
    public final android.widget.TextView weatcherTypeTV;
    // variables
    private com.sunilsahoo.inventorycontoller.entity.Forecast mForecast;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ForecastListItemBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.dateTV = (android.widget.TextView) bindings[1];
        this.dateTV.setTag(null);
        this.dayTV = (android.widget.TextView) bindings[2];
        this.dayTV.setTag(null);
        this.forecastIV = (android.widget.ImageView) bindings[4];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tempInfo = (android.widget.TextView) bindings[5];
        this.weatcherTypeTV = (android.widget.TextView) bindings[3];
        this.weatcherTypeTV.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean setVariable(int variableId, Object variable) {
        switch(variableId) {
            case BR.forecast :
                setForecast((com.sunilsahoo.inventorycontoller.entity.Forecast) variable);
                return true;
        }
        return false;
    }

    public void setForecast(com.sunilsahoo.inventorycontoller.entity.Forecast forecast) {
        this.mForecast = forecast;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.forecast);
        super.requestRebind();
    }
    public com.sunilsahoo.inventorycontoller.entity.Forecast getForecast() {
        return mForecast;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String dateForecast = null;
        java.lang.String textForecast = null;
        com.sunilsahoo.inventorycontoller.entity.Forecast forecast = mForecast;
        java.lang.String dayForecast = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (forecast != null) {
                    // read forecast.date
                    dateForecast = forecast.getDate();
                    // read forecast.text
                    textForecast = forecast.getText();
                    // read forecast.day
                    dayForecast = forecast.getDay();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.dateTV, dateForecast);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.dayTV, dayForecast);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.weatcherTypeTV, textForecast);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ForecastListItemBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ForecastListItemBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ForecastListItemBinding>inflate(inflater, com.sunilsahoo.httpconnection.R.layout.forecast_list_item, root, attachToRoot, bindingComponent);
    }
    public static ForecastListItemBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ForecastListItemBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.sunilsahoo.httpconnection.R.layout.forecast_list_item, null, false), bindingComponent);
    }
    public static ForecastListItemBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ForecastListItemBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/forecast_list_item_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ForecastListItemBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): forecast
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}
package com.sunilsahoo.httpconnection.databinding;
import com.sunilsahoo.httpconnection.R;
import com.sunilsahoo.httpconnection.BR;
import android.view.View;
public class WeatherNowFragmentBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.currentTemp, 4);
        sViewsWithIds.put(R.id.maxMinTemp, 5);
        sViewsWithIds.put(R.id.humidityTV, 6);
        sViewsWithIds.put(R.id.windTV, 7);
        sViewsWithIds.put(R.id.visibilityTV, 8);
        sViewsWithIds.put(R.id.sunriseTV, 9);
        sViewsWithIds.put(R.id.sunsetTV, 10);
    }
    // views
    public final android.widget.TextView climateTV;
    public final android.widget.TextView currentTemp;
    public final android.widget.TextView humidityTV;
    public final android.widget.TextView maxMinTemp;
    public final android.widget.TextView price;
    public final android.widget.LinearLayout productDtlContainerLL;
    public final android.widget.TextView sunriseTV;
    public final android.widget.TextView sunsetTV;
    public final android.widget.TextView title;
    public final android.widget.TextView visibilityTV;
    public final android.widget.TextView windTV;
    // variables
    private com.sunilsahoo.inventorycontoller.entity.Channel mChannel;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public WeatherNowFragmentBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.climateTV = (android.widget.TextView) bindings[3];
        this.climateTV.setTag(null);
        this.currentTemp = (android.widget.TextView) bindings[4];
        this.humidityTV = (android.widget.TextView) bindings[6];
        this.maxMinTemp = (android.widget.TextView) bindings[5];
        this.price = (android.widget.TextView) bindings[2];
        this.price.setTag(null);
        this.productDtlContainerLL = (android.widget.LinearLayout) bindings[0];
        this.productDtlContainerLL.setTag(null);
        this.sunriseTV = (android.widget.TextView) bindings[9];
        this.sunsetTV = (android.widget.TextView) bindings[10];
        this.title = (android.widget.TextView) bindings[1];
        this.title.setTag(null);
        this.visibilityTV = (android.widget.TextView) bindings[8];
        this.windTV = (android.widget.TextView) bindings[7];
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
            case BR.channel :
                setChannel((com.sunilsahoo.inventorycontoller.entity.Channel) variable);
                return true;
        }
        return false;
    }

    public void setChannel(com.sunilsahoo.inventorycontoller.entity.Channel channel) {
        this.mChannel = channel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.channel);
        super.requestRebind();
    }
    public com.sunilsahoo.inventorycontoller.entity.Channel getChannel() {
        return mChannel;
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
        java.lang.String linkChannel = null;
        com.sunilsahoo.inventorycontoller.entity.Condition conditionItemChannel = null;
        com.sunilsahoo.inventorycontoller.entity.Item itemChannel = null;
        java.lang.String titleChannel = null;
        java.lang.String textConditionItemCha = null;
        com.sunilsahoo.inventorycontoller.entity.Channel channel = mChannel;

        if ((dirtyFlags & 0x3L) != 0) {



                if (channel != null) {
                    // read channel.link
                    linkChannel = channel.getLink();
                    // read channel.item
                    itemChannel = channel.getItem();
                    // read channel.title
                    titleChannel = channel.getTitle();
                }


                if (itemChannel != null) {
                    // read channel.item.condition
                    conditionItemChannel = itemChannel.getCondition();
                }


                if (conditionItemChannel != null) {
                    // read channel.item.condition.text
                    textConditionItemCha = conditionItemChannel.getText();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.climateTV, textConditionItemCha);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.price, linkChannel);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.title, titleChannel);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static WeatherNowFragmentBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static WeatherNowFragmentBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<WeatherNowFragmentBinding>inflate(inflater, com.sunilsahoo.httpconnection.R.layout.weather_now_fragment, root, attachToRoot, bindingComponent);
    }
    public static WeatherNowFragmentBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static WeatherNowFragmentBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.sunilsahoo.httpconnection.R.layout.weather_now_fragment, null, false), bindingComponent);
    }
    public static WeatherNowFragmentBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static WeatherNowFragmentBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/weather_now_fragment_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new WeatherNowFragmentBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): channel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}
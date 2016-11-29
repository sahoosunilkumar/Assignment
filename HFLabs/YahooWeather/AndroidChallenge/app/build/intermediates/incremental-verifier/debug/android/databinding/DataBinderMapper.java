
package android.databinding;
import com.sunilsahoo.httpconnection.BR;
class DataBinderMapper {
    final static int TARGET_MIN_SDK = 18;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.sunilsahoo.httpconnection.R.layout.weather_now_fragment:
                    return com.sunilsahoo.httpconnection.databinding.WeatherNowFragmentBinding.bind(view, bindingComponent);
                case com.sunilsahoo.httpconnection.R.layout.forecast_list_item:
                    return com.sunilsahoo.httpconnection.databinding.ForecastListItemBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case -938718406: {
                if(tag.equals("layout/weather_now_fragment_0")) {
                    return com.sunilsahoo.httpconnection.R.layout.weather_now_fragment;
                }
                break;
            }
            case 1489349190: {
                if(tag.equals("layout/forecast_list_item_0")) {
                    return com.sunilsahoo.httpconnection.R.layout.forecast_list_item;
                }
                break;
            }
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"channel"
            ,"forecast"};
    }
}
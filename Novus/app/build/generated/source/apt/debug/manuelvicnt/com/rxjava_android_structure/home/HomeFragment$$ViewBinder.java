// Generated code from Butter Knife. Do not modify!
package manuelvicnt.com.rxjava_android_structure.home;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HomeFragment$$ViewBinder<T extends manuelvicnt.com.rxjava_android_structure.home.HomeFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492969, "field 'userDataText'");
    target.userDataText = finder.castView(view, 2131492969, "field 'userDataText'");
    view = finder.findRequiredView(source, 2131492968, "field 'swipeRefreshLayout'");
    target.swipeRefreshLayout = finder.castView(view, 2131492968, "field 'swipeRefreshLayout'");
  }

  @Override public void unbind(T target) {
    target.userDataText = null;
    target.swipeRefreshLayout = null;
  }
}

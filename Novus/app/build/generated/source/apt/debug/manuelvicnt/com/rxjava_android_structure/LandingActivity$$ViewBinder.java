// Generated code from Butter Knife. Do not modify!
package manuelvicnt.com.rxjava_android_structure;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LandingActivity$$ViewBinder<T extends manuelvicnt.com.rxjava_android_structure.LandingActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492966, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131492966, "field 'toolbar'");
  }

  @Override public void unbind(T target) {
    target.toolbar = null;
  }
}

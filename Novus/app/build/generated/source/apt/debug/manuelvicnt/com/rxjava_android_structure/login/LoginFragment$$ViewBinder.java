// Generated code from Butter Knife. Do not modify!
package manuelvicnt.com.rxjava_android_structure.login;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginFragment$$ViewBinder<T extends manuelvicnt.com.rxjava_android_structure.login.LoginFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492970, "method 'loginButtonTap'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.loginButtonTap(p0);
        }
      });
  }

  @Override public void unbind(T target) {
  }
}

//package com.sixt.cars.gamelist;
//
//import android.support.v7.widget.RecyclerView;
//
//import com.sixt.cars.app.home.presenter.IHomePresenter;
//import com.sixt.cars.app.home.view.HomeActivity;
//import com.sixt.cars.app.home.view.HomeView;
//import com.sixt.cars.app.inventorylist.view.InventoryListFragment;
//import com.sixt.inventorycontroller.entity.CarInfo;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.robolectric.RobolectricTestRunner;
//
//import java.util.List;
//
//import static org.mockito.BDDMockito.then;
//import static org.mockito.Mockito.times;
//import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil
//        .startFragment;
//
///**
// * Created by sunilkumarsahoo on 8/21/17.
// */
//
//@RunWith(RobolectricTestRunner.class)
//public class GameListFragmentTest {
//
//    @Mock
//    RecyclerView list;
//    @Mock
//    List<CarInfo> response;
//    @Mock
//    HomeActivity activity;
//    @Mock
//    HomeView homeView;
//    @InjectMocks
//    private InventoryListFragment gameListFragment;
//    @Mock
//    private IHomePresenter presenter;
//
//    @Before
//    public void setUp() throws Exception {
//        gameListFragment = new InventoryListFragment();
//
//        startFragment(gameListFragment, HomeActivity.class);
//        MockitoAnnotations.initMocks(this);
//
//    }
//
//    @Test
//    public void onActivityCreated() throws Exception {
//        gameListFragment.onActivityCreated(null);
//        then(presenter).should(times(1)).getCars();
//    }
//
////    @Test
////    public void onSuccess() throws Exception {
////        gameListFragment.onSuccess(response);
////        then(list).should(times(1)).setAdapter(any(RecyclerView.Adapter
/// .class));
////    }
////
////
////
////    @Test
////    public void showWait() throws Exception {
////        gameListFragment.showWait();
////        then(homeView).should(times(1)).showWait();
////    }
////
////    @Test
////    public void removeWait() throws Exception {
////        gameListFragment.removeWait();
////        then(homeView).should(times(1)).removeWait();
////    }
//
//}

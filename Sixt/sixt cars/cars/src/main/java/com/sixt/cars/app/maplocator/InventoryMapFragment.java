package com.sixt.cars.app.maplocator;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sixt.cars.R;
import com.sixt.cars.app.home.BaseFragment;
import com.sixt.inventorycontroller.entity.CarInfo;

import java.util.ArrayList;


public class InventoryMapFragment extends BaseFragment implements
        OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener, InventoryMapView {
    private static final String TAG_ERROR_DIALOG_FRAGMENT = "ERROR_DIALOG_FRAG";
    private GoogleMap map;
    private View view = null;
    private ItemPresenter presentor;

    private static int getVersionFromPackageManager(Context context) {
        PackageManager packageManager = context.getPackageManager();
        FeatureInfo[] featureInfos =
                packageManager.getSystemAvailableFeatures();
        if (featureInfos != null && featureInfos.length > 0) {
            for (FeatureInfo featureInfo : featureInfos) {
                // Null feature name means this feature is the open
                // gl es version feature.
                if (featureInfo.name == null) {
                    if (featureInfo.reqGlEsVersion != FeatureInfo
                            .GL_ES_VERSION_UNDEFINED) {
                        return getMajorVersion(featureInfo.reqGlEsVersion);
                    } else {
                        return 1; // Lack of property means OpenGL ES
                        // version 1
                    }
                }
            }
        }
        return 1;
    }

    /**
     * @see FeatureInfo#getGlEsVersion()
     */
    private static int getMajorVersion(int glEsVersion) {
        return ((glEsVersion & 0xffff0000) >> 16);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        presentor.onMapReady();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presentor = new ItemPresenterImpl(this);
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_map, null, false);
        if (readyToGo()) {
            SupportMapFragment mapFrag =
                    (SupportMapFragment) this.getChildFragmentManager()
                            .findFragmentById(R.id.map);


            if (mapFrag != null) {
                mapFrag.getMapAsync(this);
            }
        }
        return view;
    }

    protected boolean readyToGo() {
        GoogleApiAvailability checker =
                GoogleApiAvailability.getInstance();

        int status = checker.isGooglePlayServicesAvailable(getActivity());

        if (status == ConnectionResult.SUCCESS) {
            if (getVersionFromPackageManager(getActivity()) >= 2) {
                return (true);
            } else {
                Toast.makeText(getActivity(), R.string.no_maps, Toast
                        .LENGTH_LONG).show();
            }
        } else if (checker.isUserResolvableError(status)) {
            ErrorDialogFragment.newInstance(status)
                    .show(getFragmentManager(),
                            TAG_ERROR_DIALOG_FRAGMENT);
        } else {
            Toast.makeText(getActivity(), R.string.no_maps, Toast
                    .LENGTH_LONG).show();
        }

        return (false);
    }

    @Override
    public void update(ArrayList<CarInfo> carInfoList) {
        presentor.setItems(carInfoList);
    }

    @Override
    public void showMarkersFor(final ArrayList<CarInfo> carInfoList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (CarInfo item : carInfoList) {
                    map.addMarker(new MarkerOptions()
                            .position(new LatLng(item.getLatitude(), item
                                    .getLongitude()))
                            .title(item.getName())
                            .snippet(item.getModelName())
                            .icon(BitmapDescriptorFactory.fromResource(R
                                    .drawable.map_marker)));

                }
                map.setInfoWindowAdapter(new PopupAdapter(
                        getActivity().getLayoutInflater()));
                map.setOnInfoWindowClickListener(InventoryMapFragment.this);
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(new
                        LatLng(carInfoList.get(0).getLatitude(),
                        carInfoList.get(0).getLongitude()), 10.0f));
            }
        });
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(getActivity(), marker.getTitle(), Toast.LENGTH_LONG)
                .show();
    }

    public static class ErrorDialogFragment extends DialogFragment {
        static final String ARG_ERROR_CODE = "errorCode";

        static ErrorDialogFragment newInstance(int errorCode) {
            Bundle args = new Bundle();
            ErrorDialogFragment result = new ErrorDialogFragment();

            args.putInt(ARG_ERROR_CODE, errorCode);
            result.setArguments(args);

            return (result);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Bundle args = getArguments();
            GoogleApiAvailability checker =
                    GoogleApiAvailability.getInstance();

            return (checker.getErrorDialog(getActivity(),
                    args.getInt(ARG_ERROR_CODE), 0));
        }

        @Override
        public void onDismiss(DialogInterface dlg) {
            if (getActivity() != null) {
                getActivity().finish();
            }
        }
    }
}


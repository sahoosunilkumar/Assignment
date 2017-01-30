package com.iec.locationfinder.maplocator;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sunilsahoo.inventorycontroller.entity.Result;
import com.iec.locationfinder.BaseFragment;
import com.iec.locationfinder.R;


public class MapLocatorFragment extends BaseFragment implements
        OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener {
    private static final String TAG_ERROR_DIALOG_FRAGMENT = "ERROR_DIALOG_FRAG";
    private GoogleMap map;
    private View view = null;
    private boolean isMapReady = false;

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
        isMapReady = true;
        refresh();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.map_fragment, null, false);
        if (readyToGo()) {
            MapFragment mapFrag =
                    (MapFragment) this.getChildFragmentManager()
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
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(getActivity(), marker.getTitle(), Toast.LENGTH_LONG)
                .show();
    }


    @Override
    public void refresh() {
        if (!this.isMapReady || getResults() == null) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (Result address : getResults()) {
                    Log.d("Sunil", "add marker at " + address.getLat() + " " +
                            "long : " + address.getLng());
                    map.addMarker(new MarkerOptions()
                            .position(new LatLng(Double.parseDouble(address
                                    .getLat()), Double.parseDouble(address
                                    .getLng())))

                            .title(address.getZipcode())
                            .snippet(address.getAddress().toString()));
                    map.setInfoWindowAdapter(new PopupAdapter(
                            getActivity().getLayoutInflater()));
                    map.setOnInfoWindowClickListener(MapLocatorFragment.this);
                    map.animateCamera(CameraUpdateFactory.newLatLng(new
                            LatLng(Double.parseDouble(address.getLat()),
                            Double.parseDouble(address.getLng()))));
                }

            }
        });
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


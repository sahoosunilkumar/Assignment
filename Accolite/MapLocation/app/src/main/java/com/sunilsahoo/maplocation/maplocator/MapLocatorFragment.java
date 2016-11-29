package com.sunilsahoo.maplocation.maplocator;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sunilsahoo.inventorycontroller.entity.IPDetail;
import com.sunilsahoo.maplocation.Constants;
import com.sunilsahoo.maplocation.R;

import java.util.List;


/**
 * Created by sunilkumarsahoo on 10/9/16.
 */
public class MapLocatorFragment extends Fragment implements
        OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener, MapLocationView {
    private static final String TAG_ERROR_DIALOG_FRAGMENT = "ERROR_DIALOG_FRAG";
    private GoogleMap map;
    private View view = null;
    private MapIPPresenter presentor;
    private String query;

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
        presentor.getItems(query);
    }

    private void addMarker(GoogleMap map, double lat, double lon,
                           String title, String snippet) {
        map.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
                .title(title)
                .snippet(snippet).icon(BitmapDescriptorFactory.fromResource(R
                        .drawable.current_loc_marker)));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        query = getArguments().getString(Constants.KEY_QUERY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presentor = new MapIPPresenterImpl(this);
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
    public void showMap(final IPDetail ipDetail) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.parseDouble(ipDetail.getLatitude()), Double.parseDouble(ipDetail.getLongitude())))
                        .title(ipDetail.getIp())
                        .snippet(ipDetail.toString()));
                map.setInfoWindowAdapter(new PopupAdapter(
                        getActivity().getLayoutInflater()));
                map.setOnInfoWindowClickListener(MapLocatorFragment.this);
                map.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(Double.parseDouble(ipDetail.getLatitude()), Double.parseDouble(ipDetail.getLongitude()))));

            }
        });

    }

    @Override
    public void showToastMessage(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT)
                        .show();
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


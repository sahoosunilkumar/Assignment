package com.sunilsahoo.maplocation.maplocator;

import android.Manifest;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient
        .OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sunilsahoo.maplocation.Constants;
import com.sunilsahoo.maplocation.R;
import com.sunilsahoo.maplocation.model.Coupon;
import com.sunilsahoo.maplocation.model.Item;

import java.util.List;


/**
 * Created by sunilkumarsahoo on 10/9/16.
 */
public class CouponsLocatorFragment extends Fragment implements
        OnMapReadyCallback, ConnectionCallbacks, OnConnectionFailedListener,
        GoogleMap.OnInfoWindowClickListener, ItemView {
    private static final int GRANT_MAP_PERMISSION_REQUEST_CODE = 1;
    private boolean needsInit = false;
    private static final String TAG_ERROR_DIALOG_FRAGMENT = "ERROR_DIALOG_FRAG";
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private boolean isLocationDetected;
    private double lattitude = Constants.DEFAULT_LATITUDE;
    private double longitude = Constants.DEFAULT_LONGITUDE;
    private GoogleMap map;
    private View view = null;
    private ItemPresenter presentor;
    private static final String[] MAP_PERMISSIONS = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};


    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        setUpcurrentLocation(this.map, lattitude, longitude);
    }

    private void addMarker(GoogleMap map, double lat, double lon,
                           String title, String snippet) {
        map.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
                .title(title)
                .snippet(snippet).icon(BitmapDescriptorFactory.fromResource(R
                        .drawable.current_loc_marker)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presentor = new ItemPresenterImpl(this);
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.activity_main, null, false);
        if (readyToGo()) {
            MapFragment mapFrag =
                    (MapFragment) this.getChildFragmentManager()
                            .findFragmentById(R.id.map);

            if (savedInstanceState == null) {
                needsInit = true;
            }

            if (mapFrag != null) {
                mapFrag.getMapAsync(this);
            }
        }
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onConnectionFailed(ConnectionResult arg0) {
        Toast.makeText(getActivity(), "Failed to connect...", Toast
                .LENGTH_SHORT).show();

    }

    @Override
    public void onConnected(Bundle arg0) {

        if (!isPermissionsAvail()) {
            ActivityCompat.requestPermissions(getActivity(), MAP_PERMISSIONS,
                    GRANT_MAP_PERMISSION_REQUEST_CODE);
            return;
        }
        fetchLocation();

    }

    private void fetchLocation(){
        try {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);

            if (mLastLocation != null) {
                isLocationDetected = true;
                lattitude = mLastLocation.getLatitude();
                longitude = mLastLocation.getLongitude();
                setUpcurrentLocation(this.map, this.lattitude, this.longitude);
            }
        }catch (SecurityException ex){
            Log.w(CouponsLocatorFragment.class.getName(), ex.getMessage());
        }
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        Toast.makeText(getActivity(), "Connection suspended...", Toast
                .LENGTH_SHORT).show();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (isPermissionsAvail()) {
            fetchLocation();
        }

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
    public void refreshList(List<Item> itemList) {
        for (Item item : itemList) {
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(item.getLatLong().latitude, item
                            .getLatLong().longitude))
                    .title(item.getTitle())
                    .snippet(item.getDescription())
                    .icon(BitmapDescriptorFactory.fromResource(item
                            instanceof Coupon ? R
                            .drawable.coupon_marker : R
                            .drawable.deals_loc_marker)));
        }
        map.setInfoWindowAdapter(new PopupAdapter(
                getActivity().getLayoutInflater()));
        map.setOnInfoWindowClickListener(this);
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

    private void setUpcurrentLocation(GoogleMap map, double latitude, double
            longitude) {
        if (getResources().getBoolean(R.bool
                .use_hardcode_location)) {
            isLocationDetected = true;
            latitude = Constants.DEFAULT_LATITUDE;
            longitude = Constants.DEFAULT_LONGITUDE;
        }
        if (needsInit && isLocationDetected) {
            CameraUpdate center =
                    CameraUpdateFactory.newLatLng(new LatLng(latitude,
                            longitude));
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(12);

            map.moveCamera(center);
            map.animateCamera(zoom);
            addMarker(map, latitude, longitude,
                    "Current Location", "Current Location");
            presentor.getItems();
        }
    }

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
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(getActivity(), marker.getTitle(), Toast.LENGTH_LONG)
                .show();
    }

    private boolean isPermissionsAvail(){
        for(String permission : MAP_PERMISSIONS) {
            if(ActivityCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }
}


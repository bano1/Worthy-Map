package com.example.worthymap;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity2 extends FragmentActivity  implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener  {
// , GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerDragListener
    private GoogleMap mMap;
    private Geocoder geocoder;
    private static final String TAG = "MapsActivity";
    String Log;
    String Lat;
    String LogLat;
    String localityy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        geocoder = new Geocoder(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
       // mMap.setOnMapLongClickListener(new mMap.setOnMapLongClickListener()
        //mMap.setOnMarkerDragListener(this);
        mMap = googleMap;
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
               // Toast.makeText(getApplicationContext(),"Button two Clicked" + latLng.toString(),Toast.LENGTH_LONG).show();
                try {
                    List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    if (addresses.size() > 0) {
                        Address address = addresses.get(0);
                        String streetAddress = address.getAddressLine(0);
                        mMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title(streetAddress)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicon2))
                                .draggable(true)
                        );
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        try {

            List<Address> addresses = geocoder.getFromLocationName("Alamdar Chowk", 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                LatLng loc = new LatLng(address.getLatitude(), address.getLongitude());
                //Log = String.valueOf(address.getLongitude());
                //Lat = String.valueOf(address.getLatitude());
                //localityy = address.getLocality() + " " + address.getSubLocality() + " " + address.getCountryName();
                //LogLat = Log + " , " + Lat;

                MarkerOptions markerOptions = new MarkerOptions()
                        .position(loc)
                        .title(address.getLocality())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicon2));
                ;
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 14));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMap.setOnMarkerDragListener(this);

    }

   /* @Override
    public void onMapLongClick(LatLng latLng) {
        Toast.makeText(getApplicationContext(),"Button two Clicked" + latLng.toString(),Toast.LENGTH_LONG).show();
        // Log.d(TAG, "onMapLongClick: " + latLng.toString());
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                String streetAddress = address.getAddressLine(0);
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(streetAddress)
                        .draggable(true)
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } */

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

   @Override
    public void onMarkerDragEnd(Marker marker) {
       // Log.d(TAG, "onMarkerDragEnd: ");
        LatLng latLng = marker.getPosition();
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                String streetAddress = address.getAddressLine(0);
                marker.setTitle(streetAddress);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
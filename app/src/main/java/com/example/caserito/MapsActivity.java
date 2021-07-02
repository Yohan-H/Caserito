package com.example.caserito;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //OBTENER LOCALIZACIÓN
        getLocalizacion();
    }

    //METODO PARA PEDIR PERMISO AL ACTIVAR LA UBICACIÓN I GPS
    private void getLocalizacion() {
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        Antut(googleMap);


        //ACTUALIZACION DE GEOLOCALIZACION
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        LocationManager locationManager = (LocationManager) MapsActivity.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(miUbicacion).title("ubicacion actual"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(miUbicacion));
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(miUbicacion)
                        .zoom(11)
                        .bearing(90)
                        .tilt(45)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);


    }
















    //DEFINIENDO LAS UBICACIONES PREESTABLECIDAS DE LAS TIENDAS AFILIADAS
    public void Antut (GoogleMap googleMap) {
        mMap = googleMap;

        final LatLng T1 = new LatLng(-16.405072,-71.5480528);
        mMap.addMarker(new MarkerOptions().position(T1).title("La Case").icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_marca_mapa_foreground)).anchor(0.0f,1.0f));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(T1,11));

        final LatLng T2 = new LatLng(-16.3430262,-71.5815054);
        mMap.addMarker(new MarkerOptions().position(T2).title("Bodega Cerro Colorado Caserito").icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_marca_mapa_foreground)).anchor(0.0f,1.0f));

        final LatLng T3 = new LatLng(-16.3446661,-71.541234);
        mMap.addMarker(new MarkerOptions().position(T3).title("Bodega Cayma Caserito").icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_marca_mapa_foreground)).anchor(0.0f,1.0f));
        final LatLng T4 = new LatLng(-16.4041542,-71.51872);
        mMap.addMarker(new MarkerOptions().position(T4).title("Bodega La Negrita Caserito").icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_marca_mapa_foreground)).anchor(0.0f,1.0f));
    }
}
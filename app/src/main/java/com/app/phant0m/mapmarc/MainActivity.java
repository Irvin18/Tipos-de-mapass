package com.app.phant0m.mapmarc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.view.MenuItem;
import android.support.v4.app.FragmentActivity;
import android.widget.Switch;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;




// aqui se extienede FragmentActivity para que se pueda mostrar el mapa e implements GoogleMap.OnMapClickListener por si se
//hace un click en el mapa se agrege un marcador
public class MainActivity  extends FragmentActivity implements GoogleMap.OnMapClickListener {

    //Aqui declare una posicion en el mapa que estara fica, en este caso mi casa
    private final LatLng casa = new LatLng(18.365379, -100.662904);

    private GoogleMap mapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// en esta parte se le dan algunas propiedades al mapa como son el tipo de mapa inicial, que la camara se mueva
        //que optenga mi ubicacion y se agrege un marcador con las latitudes y longitudes anteriores y el tipo y ancho del marcador
        mapa = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(casa, 15));
        mapa.setMyLocationEnabled(true);
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.getUiSettings().setCompassEnabled(true);
        mapa.addMarker(new MarkerOptions()
                .position(casa)
                .title("CASA")
                .snippet("Mi Casa")
                .icon(BitmapDescriptorFactory
                        .fromResource(android.R.drawable.ic_menu_compass))
                .anchor(0.5f, 0.5f));
        mapa.setOnMapClickListener(this);
        mapa.moveCamera(CameraUpdateFactory.newLatLng(casa));
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //Aqui se modifica el action bar para que se puedan seleccionar los difrentes tipos de mapas
        switch (item.getItemId()) {
            case R.id.Hibrido:
                mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;

            case R.id.Satelital:
                mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;

            case R.id.Terreno:
                mapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            case R.id.Normal:
                mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.Ninguno:
                mapa.setMapType(GoogleMap.MAP_TYPE_NONE);
                return true;
            default:


                return super.onOptionsItemSelected(item);
        }


    }

    //Al hacer click en cualquier punto del mapa se agregara un nuevo marcador

    @Override
    public void onMapClick(LatLng puntoPulsado) {

        mapa.addMarker(new MarkerOptions().position(puntoPulsado).
                icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
    }



}

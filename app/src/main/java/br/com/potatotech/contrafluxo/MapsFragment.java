package br.com.potatotech.contrafluxo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.potatotech.contrafluxo.Client.BusClient;
import br.com.potatotech.contrafluxo.Model.BusLocation;
import br.com.potatotech.contrafluxo.Model.BusLocationXY;
import br.com.potatotech.contrafluxo.Model.BusSearchHistory;
import br.com.potatotech.contrafluxo.Model.BusSearchResult;
import br.com.potatotech.contrafluxo.Model.BusStop;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled(true);
        }
        else{
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
            mMap.setMyLocationEnabled(true);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-23.5892118,-46.6142369), 11));
    }

    /*public void updateMapBusPath(int busLineNumber){
        BusClient busClient = new BusClient(getContext());
        busClient.getStops(busLineNumber, new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                if(e == null){
                    BusStop[] stopArray = new Gson().fromJson(result, BusStop[].class);
                    List<BusStop> stopList = Arrays.asList(stopArray);
                    List<LatLng> latLngList = new ArrayList<LatLng>();

                    for(BusStop stop : stopList){
                        //latLngList.add(new LatLng(stop.getLatitude(), stop.getLongitude()));
                        mapsFragment.addMarker(new MarkerOptions()
                                .position(new LatLng(stop.getLatitude(), stop.getLongitude()))
                                .title(stop.getNome())
                                .snippet(stop.getEndereco()));
                    }

                }
            }
        });

    }*/

    public void updateMapBusLocations(final BusSearchResult busSearchResult) {
        BusClient client = new BusClient(getContext());
        client.getBusPositions(busSearchResult.getCodigoLinha(), new FutureCallback<Response<String>>() {
            @Override
            public void onCompleted(Exception e, Response<String> result) {
                if (e == null && result.getHeaders().code() == 200){
                    BusLocation busLocations = new Gson().fromJson(result.getResult(), BusLocation.class);
                    mMap.clear();
                    for (BusLocationXY locationXY : busLocations.getVs()) {
                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(locationXY.getPy(), locationXY.getPx()))
                                .title(busSearchResult.getLetreiro() + "-" + busSearchResult.getTipo())
                                .snippet(busSearchResult.getSentido() == 1 ? busSearchResult.getDenominacaoTPTS() : busSearchResult.getDenominacaoTSTP()));
                    }


                }
                else{
                    Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void updateMapBusLocations(final BusSearchHistory busSearchHistory){
        BusClient client = new BusClient(getContext());
        client.getBusPositions(busSearchHistory.getCodigoLinha(), new FutureCallback<Response<String>>() {
            @Override
            public void onCompleted(Exception e, Response<String> result) {
                if(e == null && result.getHeaders().code() == 200){
                    BusLocation busLocations = new Gson().fromJson(result.getResult(), BusLocation.class);
                    mMap.clear();
                    for(BusLocationXY locationXY : busLocations.getVs()){
                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(locationXY.getPy(), locationXY.getPx()))
                                .title(busSearchHistory.getLetreiro())
                                .snippet(busSearchHistory.getDenominacao()));

                    }

                }
                else{
                    Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_LONG).show();

                }
            }
        });

    }

}

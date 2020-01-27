package com.example.debarembar;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String[] permissoes = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    private LocationManager locationManager;
    private LocationListener locationListener;
    private Context context;
    private View fRoot;
    private MapView mapView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        context = getActivity();
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mapa, container, false);


        // Validar Permissoes
        Permissoes.validarPermissoes(permissoes, getActivity(), 1);
        fRoot = root;
        return  root;

    }

    public void onViewCreated (View view,                               Bundle savedInstanceState){
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
       Log.e("map ready","Map ok00");
        mMap = googleMap;

        // Objeto responsavel por gerenciar a localizacao do usuario
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                Log.d("Localizaçõa","onLocationChanged"+ location.toString());

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                mMap.clear();

                // Adicione um marcador em Blumenau e mova a câmera
                LatLng localUsuario = new LatLng(latitude,longitude);
                //-26.9150432,-49.0656267

                mMap.addMarker(new MarkerOptions()
                                .position(localUsuario)
                                .title("\n" + "Meu Local ")
                                // Trocando a cor do icone
                                .icon(
                                        BitmapDescriptorFactory.defaultMarker(
                                                BitmapDescriptorFactory.HUE_MAGENTA)
                                )
                        // Colocando uma img como icone
                        // .icon(BitmapDescriptorFactory.fromResource(R.drawable.spaceship))
                );


                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localUsuario,18));

                /*Geocoding processo de tranformar um endereço
                ou descricao de um local em lattidute/longitude
                Reverse Geocoding processo de tranformar latitude/longitude
                em um endereço
                */

                Geocoder geocoder = new Geocoder(context, Locale.getDefault());

                try {
                    //List<Address> listaEndereco = geocoder.getFromLocation(latitude, longitude, 1);

                    String stringEndereco = "R. Benjamin Constant,\n" +
                            "                        1648 - Asilo, Blumenau - SC, 89037-500, Brasil";

                    List<Address> listaEndereco = geocoder.getFromLocationName
                            (stringEndereco,1);

                    if (listaEndereco != null && listaEndereco.size()>0){
                        Address endereco = listaEndereco.get(0);
                        /*
                        Address[addressLines=[0:"R. Benjamin Constant,
                        1648 - Asilo, Blumenau - SC, 89037-500, Brasil"],
                        feature=1648,
                        admin=Santa Catarina,
                        sub-admin=Blumenau,
                        locality=null,
                        thoroughfare=Rua Benjamin Constant,
                        postalCode=89037-500,
                        countryCode=BR,
                        countryName=Brasil,
                        hasLatitude=true,
                        latitude=-26.9009923,
                        hasLongitude=true,
                        longitude=-49.0980639,
                        phone=null,
                        url=null,
                        extras=null]
                        */

                        Log.d("Local ", "onLocationChanged: " + endereco.toString());
                        //Log.d("Local ", "onLocationChanged: " + endereco.getAddressLine(0));

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }


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

        if (ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    10000,
                    10,
                    locationListener
            );

        }

        // Metodo p mudar o mapa
        // mMap.setMapType(googleMap.MAP_TYPE_SATELLITE);

        // Adicionando evento de clic no mapa

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                double latitude = latLng.latitude;
                double longitude = latLng.longitude;

                Toast.makeText(context,
                        "onCLick Lat: " + latitude + "long: " + longitude,
                        Toast.LENGTH_SHORT).show();

                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Local")
                        .snippet("Descrição")
                        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.spaceship))
                );
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int permissaoResultado : grantResults) {
            if (permissaoResultado == PackageManager.PERMISSION_DENIED) {
                //Alerta
                alertaValidacaoPermissao();

            } else if (permissaoResultado == PackageManager.PERMISSION_GRANTED) {
                //Recuperar localizacao do usuario
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            10000,
                            10,
                            locationListener
                    );

                }

            }
        }
    }

    private void alertaValidacaoPermissao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões: ");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
package fr.esiea.mobile.lostpets.activity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import fr.esiea.mobile.lostpets.R;
import fr.esiea.mobile.lostpets.dao.WebServiceDAO;
import fr.esiea.mobile.lostpets.model.Pet;
import fr.esiea.mobile.lostpets.model.Pets;

//This class is the MapsActivity linked to activity_maps.xml
public class MapsActivity extends FragmentActivity implements LocationListener, Observer {

    private static int TEMPS_ENTRE_DEUX_PRISES = 5000;//millisecondes
    private static int DISTANCE_ENTRE_DEUX_PRISES = 50;//metres

    private GoogleMap m_map; // Might be null if Google Play services APK is not available.
    private LocationManager m_locationMngr;
    private WebServiceDAO m_webServiceDAO;
    private Context m_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        m_webServiceDAO = new WebServiceDAO(this);
        m_locationMngr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //Set the MapActivity as an observer of the lost pet list object
        Pets.getInstance().addObserver(this);
        m_context = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();

        //Get lost pet list
        getPets();

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        String theBestProvider = m_locationMngr.getBestProvider(criteria,true);
        //Load user location with different criteria in function of enabled gps device
        if (m_locationMngr.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            m_locationMngr.requestLocationUpdates(
                    theBestProvider,
                    TEMPS_ENTRE_DEUX_PRISES,
                    DISTANCE_ENTRE_DEUX_PRISES,
                    this);
        }
        else {
            m_locationMngr.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    TEMPS_ENTRE_DEUX_PRISES,
                    DISTANCE_ENTRE_DEUX_PRISES,
                    this);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        m_locationMngr.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location myPosition) {
        if (m_map != null) {
            //Load user location
            LatLng myLatLng = new LatLng(myPosition.getLatitude(), myPosition.getLongitude());
            //Center camera on this new location
            m_map.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #m_map} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (m_map == null) {
            // Try to obtain the map from the SupportMapFragment.
            m_map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (m_map != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #m_map} is not null.
     */
    private void setUpMap() {
        m_map.setMyLocationEnabled(true);
    }

    public void update(Observable obs, Object obj) {
        if(obs instanceof Pets){
            //Add pet markers if the lost pet list model has changed
            for (Pet pet : Pets.getInstance().getPets()) {
                addPetMarker(pet);
            }
        }
    }

    //Add lost pet marker on the map
    private void addPetMarker(Pet pet)
    {
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        try
        {
            //Create an address object in function of the pet address
            List<Address> addresses = geoCoder.getFromLocationName(pet.getM_petLostAddress() + " "
                    + pet.getM_petLostZipcode() + " " + pet.getM_petLostCity() , 1);
            if (addresses.size() > 0)
            {
                //Add a marker on this pet location
                m_map.addMarker(new MarkerOptions()
                        .position(new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude()))
                        .title(String.valueOf(pet.getM_petId())));
                //Set onMarkerClickListener on each created marker
                m_map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
                {
                    @Override
                    public boolean onMarkerClick(Marker thisMarker) {
                        Intent nextActivity = new Intent(m_context, PetMarkerActivity.class);
                        nextActivity.putExtra("petId", Integer.valueOf(thisMarker.getTitle()));
                        startActivity(nextActivity);
                        return true;
                    }

                });
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, R.string.err_dataWebApi, Toast.LENGTH_LONG).show();
        }
    }

    //Call the webservice
    private void getPets() {
        Toast.makeText(this, R.string.msg_contactWebApi, Toast.LENGTH_LONG).show();
        try {
            m_webServiceDAO.getInfos();
        }
        catch (Exception e) {
            Toast.makeText(this, R.string.err_contactWebApi, Toast.LENGTH_LONG).show();
        }
    }
}

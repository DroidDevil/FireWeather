package com.droiddevil.fireweather;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String FIREBASE_WEATHER_ROOT = "https://publicdata-weather.firebaseio.com/";

    private GoogleMap mMap;

    private Firebase mWeatherRootRef;

    private Set<City> mCities = new HashSet<City>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();
        mMap = ((MapFragment) fm.findFragmentById(R.id.map)).getMap();

        mWeatherRootRef = new Firebase(FIREBASE_WEATHER_ROOT);

        // mWeatherRootRef.addChildEventListener(mCitiesEventListener);
        mWeatherRootRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    City city = child.getValue(City.class);
                    city.setName(child.getName());
                    
                    mCities.add(city);

                    MarkerOptions options = new MarkerOptions();
                    LatLng position = new LatLng(city.getLatitude(), city.getLongitude());
                    options.position(position);
                    mMap.addMarker(options);
                }
            }

            @Override
            public void onCancelled(FirebaseError e) {
                
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeatherRootRef.removeEventListener(mCitiesEventListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // mWeatherRootRef.addChildEventListener(mWeatherEventListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private ChildEventListener mCitiesEventListener = new SimpleChildEventListener() {

        @Override
        public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
            City city = snapshot.getValue(City.class);
            mCities.add(city);

            MarkerOptions options = new MarkerOptions();
            LatLng position = new LatLng(city.getLatitude(), city.getLongitude());
            options.position(position);
            mMap.addMarker(options);
        }

        @Override
        public void onCancelled(FirebaseError e) {

        }
    };

}

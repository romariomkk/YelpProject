package com.romariomkk.yelpproject.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.romariomkk.yelpproject.R;
import com.romariomkk.yelpproject.core.models.Conditions;
import com.romariomkk.yelpproject.core.main_func.MainApp;
import com.romariomkk.yelpproject.core.models.content.Business;
import com.romariomkk.yelpproject.core.models.content.BusinessXtra;
import com.romariomkk.yelpproject.core.models.content.SurroundingBusinesses;
import com.romariomkk.yelpproject.core.util.PrefUtils;
import com.romariomkk.yelpproject.mvp.MvpPresenter;
import com.romariomkk.yelpproject.mvp.MvpView;
import com.romariomkk.yelpproject.ui.adapters.PinInfoWindowAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        MvpView,
        GoogleMap.OnCameraIdleListener,
        GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private MvpPresenter presenter;
    private PinInfoWindowAdapter infoWindowAdapter;

    private List<Business> businessList;

    @BindView(R.id.searchButton)
    Button searchAreaButton;
    @BindView(R.id.searchTermButton)
    ImageButton searchTermButton;
    @BindView(R.id.filterText)
    EditText filterTextEdit;

    @OnClick(R.id.searchButton)
    public void onSearchAreaClicked()
    {
        searchAreaButton.setVisibility(View.INVISIBLE);
        mMap.clear();
        LatLng coords = mMap.getCameraPosition().target;
        presenter.requestAllBusinesses(new Conditions()
                .add("latitude", String.valueOf(coords.latitude))
                .add("longitude", String.valueOf(coords.longitude))
                .add("radius", String.valueOf(2000))); //default radius for search, 2000m
    }

    @OnClick(R.id.searchTermButton)
    public void onTermSearchClicked()
    {
        searchAreaButton.setVisibility(View.INVISIBLE);
        mMap.clear();
        LatLng coords = mMap.getCameraPosition().target;
        presenter.requestAllBusinesses(new Conditions()
                .add("latitude", String.valueOf(coords.latitude))
                .add("longitude", String.valueOf(coords.longitude))
                .add("radius", String.valueOf(2000))
                .add("term", filterTextEdit.getText().toString())); //default radius for search, 2000m
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        presenter = new MvpPresenter(this);
        infoWindowAdapter = new PinInfoWindowAdapter(this);

        //just for test of access token
        Toast.makeText(MapsActivity.this, PrefUtils.getUserToken(this), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        mMap.setOnCameraIdleListener(this);
        mMap.setInfoWindowAdapter(infoWindowAdapter);
        mMap.setOnInfoWindowClickListener(this);

        // Sydney and move the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-34, 151)));
    }

    @Override
    public void onCameraIdle()
    {
        Timber.i("Idled");

        if (searchAreaButton.getVisibility() != View.VISIBLE)
        {
            searchAreaButton.setVisibility(View.VISIBLE);
            searchAreaButton.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.show_button));
        }
    }

    @Override
    public void refreshPoints(SurroundingBusinesses businesses)
    {
        businessList = businesses.getBusinesses();
        if (businessList.size() != 0)
        {
            infoWindowAdapter.invalidate(businessList);
            for (int i = 0; i < businessList.size(); i++)
                setMarker(i);
        }
        else
        {
            Toast.makeText(MapsActivity.this, "No results found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void moveToSingleBusiness(BusinessXtra businessXtra)
    {
        BusinessDetailActivity.launch(this, businessXtra);
    }

    void setMarker(int pos)
    {
        Business bus = businessList.get(pos);
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.geopoint))
                .position(new LatLng(bus.getCoordinates().getLatitude(), bus.getCoordinates().getLongitude()))
                .title(Integer.toString(pos))
                .snippet(bus.getId()));
    }


    @Override
    public void onInfoWindowClick(Marker marker)
    {
        presenter.requestSingleBusiness(marker.getSnippet());
    }

}

package com.romariomkk.yelpproject.ui.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.romariomkk.yelpproject.R;
import com.romariomkk.yelpproject.core.models.content.Business;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by romariomkk on 15.06.2017.
 */
public class PinInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;
    private List<Business> businessList;

    public PinInfoWindowAdapter(Context context)
    {
        this.context = context;
    }

    public void invalidate(List<Business> businesses)
    {
        this.businessList = businesses;
    }

    @BindView(R.id.pin_logo)
    ImageView logoImg;
    @BindView(R.id.business_address)
    TextView address;
    @BindView(R.id.business_title)
    TextView title;
    @BindView(R.id.rating_text)
    TextView rating;

    @Override
    public View getInfoWindow(Marker marker)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(R.layout.info_pin_layout, null, false);
        ButterKnife.bind(this, convertView);

        int pos = Integer.valueOf(marker.getTitle());
        Business business = businessList.get(pos);

        if (business.getImageUrl() != null && !TextUtils.isEmpty(business.getImageUrl()))
            Picasso.with(context).load(business.getImageUrl()).fit().into(logoImg);
        title.setText(business.getName());
        address.setText(String.format("%s, %s", business.getLocation().getCity(), business.getLocation().getAddress1()));
        rating.setText(String.format("%.1f", business.getRating()));

        return convertView;
    }

    @Override
    public View getInfoContents(Marker marker)
    {
        return null;
    }
}

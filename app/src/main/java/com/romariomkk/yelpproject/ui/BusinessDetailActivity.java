package com.romariomkk.yelpproject.ui;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.romariomkk.yelpproject.R;
import com.romariomkk.yelpproject.core.models.content.BusinessXtra;
import com.romariomkk.yelpproject.core.models.content.Category;
import com.romariomkk.yelpproject.core.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import java8.util.StringJoiner;

public class BusinessDetailActivity extends AppCompatActivity {

    private final static String BUS_TAG = "bus";

    public static void launch(Context ctxt, BusinessXtra business)
    {
        Intent intent = new Intent(ctxt, BusinessDetailActivity.class);
        Bundle animations = ActivityOptions.makeCustomAnimation(ctxt,
                R.anim.enter_anim, R.anim.out_anim)
                .toBundle();

        Bundle bundle = new Bundle();
        bundle.putAll(animations);
        bundle.putSerializable(BUS_TAG, business);

        intent.putExtras(bundle);

        ctxt.startActivity(intent);
    }

    @BindView(R.id.slider)
    SliderLayout sliderLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.bus_site)
    TextView siteText;
    @BindView(R.id.categories_text)
    TextView categoriesText;
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;
    @BindView(R.id.open_status)
    TextView openStatus;

    private BusinessXtra skeletonModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_detail);
        ButterKnife.bind(this);

        Bundle args = getIntent().getExtras();
        if (args != null)
        {
            skeletonModel = (BusinessXtra) args.getSerializable(BUS_TAG);
            if (skeletonModel != null)
                updateUI();
        }

        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (skeletonModel.getPhone() != null)
                {
                    Utils.callToNumber(BusinessDetailActivity.this, skeletonModel.getPhone());
                }
            }
        });
    }

    private void updateUI()
    {
        TextSliderView textSlider;
        for (int i = 0; i < skeletonModel.getPhotos().length; i++)
        {
            textSlider = new TextSliderView(this);
            String photoUrl = skeletonModel.getPhotos()[i];
            textSlider.image(photoUrl)
                    .description("Photo " + i);

            sliderLayout.addSlider(textSlider);
        }

        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        toolbar.setTitle(skeletonModel.getName());
        toolbar.setSubtitle(String.format("%s, %s", skeletonModel.getLocation().getCity(),
                skeletonModel.getLocation().getAddress1()));

        String htmlText = String.format("<a href=\'%s\'>%s</a>", skeletonModel.getUrl(), getString(R.string.site_text));
        siteText.setMovementMethod(LinkMovementMethod.getInstance());
        siteText.setText(Html.fromHtml(htmlText));

        ratingBar.setRating((float)skeletonModel.getRating());

        StringJoiner joiner = new StringJoiner(", ");
        for (Category category : skeletonModel.getCategories())
            joiner.add(category.getTitle());

        categoriesText.setText(joiner.toString());

        boolean isClosed = skeletonModel.isIsClosed();
        openStatus.setText(getString(isClosed ? R.string.closed : R.string.open));
        openStatus.setTextColor(getResources().getColor(isClosed ? android.R.color.holo_red_dark : android.R.color.holo_green_light));
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        sliderLayout.stopAutoCycle();
    }
}

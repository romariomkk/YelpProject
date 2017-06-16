package com.romariomkk.yelpproject.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.romariomkk.yelpproject.R;
import com.romariomkk.yelpproject.core.main_func.ApiRetrofit;
import com.romariomkk.yelpproject.core.models.TokenResponse;
import com.romariomkk.yelpproject.core.util.PrefUtils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class SplashActivity extends BaseActivity {

    private static long DELAY = 1000L;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (PrefUtils.getUserToken(this) == null)
        {
            ApiRetrofit.requestAuth(getString(R.string.user_id), getString(R.string.client_secret))
                    .enqueue(new Callback<TokenResponse>() {
                        @Override
                        public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response)
                        {
                            if (response != null)
                            {
                                if (response.isSuccessful())
                                {
                                    process(response);
                                }
                                else if (response.errorBody() != null)
                                {
                                    try
                                    {
                                        String error = response.errorBody().string();
                                        showDialog(error);
                                    } catch (IOException e)
                                    {
                                        Timber.e("Exception parsing error message", e);
                                    }
                                }
                                else
                                {
                                    Toast.makeText(SplashActivity.this, "Authentication failed, review your code",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<TokenResponse> call, Throwable t)
                        {
                            Timber.e("Failure during HTTP request execution", t);

                        }
                    });
        }
        executeTransfer();
    }

    void process(Response<TokenResponse> response)
    {
        TokenResponse tokenResponse = response.body();
        PrefUtils.setUserToken(this, tokenResponse.getAccessToken());
    }

    void executeTransfer()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent intent = new Intent(SplashActivity.this, MapsActivity.class);
                Bundle animations = ActivityOptions.makeCustomAnimation(SplashActivity.this,
                        R.anim.enter_anim, R.anim.out_anim)
                        .toBundle();

                startActivity(intent, animations);

                finish();
            }
        }, DELAY);
    }

}

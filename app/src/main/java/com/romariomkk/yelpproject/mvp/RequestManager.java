package com.romariomkk.yelpproject.mvp;

import android.content.Context;

import com.romariomkk.yelpproject.core.main_func.ApiRetrofit;
import com.romariomkk.yelpproject.core.models.Conditions;
import com.romariomkk.yelpproject.core.models.content.BusinessXtra;
import com.romariomkk.yelpproject.core.models.content.SurroundingBusinesses;
import com.romariomkk.yelpproject.core.util.PrefUtils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by romariomkk on 15.06.2017.
 */
public class RequestManager {

    private Context context;

    public RequestManager(Context ctxt)
    {
        this.context = ctxt;
    }

    public void requestAllBusinesses(Conditions conditions, final OnBusinessLoadedListener listener)
    {
        ApiRetrofit.requestAllBusinesses(PrefUtils.getUserToken(context), conditions)
                .enqueue(new Callback<SurroundingBusinesses>() {
                    @Override
                    public void onResponse(Call<SurroundingBusinesses> call,
                                           Response<SurroundingBusinesses> response)
                    {
                        if (response != null)
                        {
                            if (response.isSuccessful())
                            {
                                SurroundingBusinesses businesses = response.body();
                                listener.onBusinessLoaded(businesses);
                            }
                            else if (response.errorBody() != null)
                            {
                                try
                                {
                                    String error = response.errorBody().string();
                                    Timber.e(error);
                                } catch (IOException e)
                                {
                                    Timber.e("Error occurred parsing answer of unsuccessful response", e);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SurroundingBusinesses> call, Throwable t)
                    {
                        Timber.e("Error occurred during all businesses data fetching", t);
                    }
                });
    }

    public void requestSingleBusiness(String id, final OnBusinessLoadedListener listener)
    {
        ApiRetrofit.requestSingleBusiness(PrefUtils.getUserToken(context), id)
                .enqueue(new Callback<BusinessXtra>() {
                    @Override
                    public void onResponse(Call<BusinessXtra> call, Response<BusinessXtra> response)
                    {
                        if (response != null)
                        {
                            if (response.isSuccessful())
                            {
                                BusinessXtra business = response.body();
                                listener.onBusinessLoaded(business);
                            }
                            else if (response.errorBody() != null)
                            {
                                try
                                {
                                    String error = response.errorBody().string();
                                    Timber.e(error);
                                } catch (IOException e)
                                {
                                    Timber.e("Error occurred parsing single-business answer of unsuccessful response", e);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BusinessXtra> call, Throwable t)
                    {
                        Timber.e("Error occurred during single business data fetching", t);
                    }
                });
    }

    public interface OnBusinessLoadedListener {
        void onBusinessLoaded(SurroundingBusinesses businesses);
        void onBusinessLoaded(BusinessXtra business);
    }
}

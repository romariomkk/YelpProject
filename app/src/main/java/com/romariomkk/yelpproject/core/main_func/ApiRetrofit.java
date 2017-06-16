package com.romariomkk.yelpproject.core.main_func;

import com.romariomkk.yelpproject.BuildConfig;
import com.romariomkk.yelpproject.core.models.Conditions;
import com.romariomkk.yelpproject.core.models.TokenResponse;
import com.romariomkk.yelpproject.core.models.content.Business;
import com.romariomkk.yelpproject.core.models.content.BusinessXtra;
import com.romariomkk.yelpproject.core.models.content.SurroundingBusinesses;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by romariomkk on 15.06.2017.
 */
public class ApiRetrofit {

    static Retrofit retrofit;

    static
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException
            {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        .build();
                return chain.proceed(request);
            }
        });
        if (BuildConfig.DEBUG)
        {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        OkHttpClient client = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.yelp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }


    public static Call<TokenResponse> requestAuth(String clientId, String clientSecret)
    {
        ITokenAccessor accessor = retrofit.create(ITokenAccessor.class);
        return accessor.requestAuth(clientId, clientSecret);
    }

    public static Call<BusinessXtra> requestSingleBusiness(String accessToken, String id)
    {
        Searchable searchable = retrofit.create(Searchable.class);
        return searchable.requestSingleBusinessInfo(id, String.format("Bearer %s", accessToken));
    }

    public static Call<SurroundingBusinesses> requestAllBusinesses(String accessToken, Conditions params)
    {
        Searchable searchable = retrofit.create(Searchable.class);
        return searchable.requestModels(params.unstashConditions(), String.format("Bearer %s", accessToken));
    }

    private interface ITokenAccessor {
        @Headers("Content-Type:application/x-www-form-urlencoded")
        @POST("/oauth2/token?grant_type=client_credentials")
        Call<TokenResponse> requestAuth(@Query("client_id") String clientId,
                                        @Query("client_secret") String clientSecret);
    }

    private interface Searchable {

        @GET("/v3/businesses/search")
        Call<SurroundingBusinesses> requestModels(@QueryMap Map<String, String> params,
                                                  @Header("Authorization") String accessToken);

        @GET("/v3/businesses/{id}")
        Call<BusinessXtra> requestSingleBusinessInfo(@Path("id") String businessId,
                                                     @Header("Authorization") String accessToken);

//        @GET("/v3/businesses/{id}/reviews")
//        Call<Reviews> requestReviews(@Path("id") String businessId,
//                                     @Header("Authorization") String accessToken);
    }

}

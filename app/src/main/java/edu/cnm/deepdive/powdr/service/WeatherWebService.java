package edu.cnm.deepdive.powdr.service;

import com.google.android.gms.common.api.internal.ApiKey;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.powdr.BuildConfig;
import edu.cnm.deepdive.powdr.model.dto.WeatherResponse;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherWebService {

  @GET
  Single<WeatherResponse> getWeather(@Query("key") String apiKey,
      @Query("num_of_days") int numberOfDays,
      @Query("lat") double latitude, @Query("lon") double longitude);

  @GET("getKey")
  Single<ApiKey> getApiKey();

  // all the things we do in postman, we will implement in the interface
  // going to define all the requests we can send to the webservice

  /**
   * Returns a singleton instance of the WeatherWebService
   */
  static WeatherWebService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Gson builder to handle endpoints
   */
  class InstanceHolder {

    private static final WeatherWebService INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          //.excludeFieldsWithoutExposeAnnotation()
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE); // based on the status of this build, build everything, or build nothing
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor) // any traffic uses this interceptor for logging
          .build();
      Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson)) // add converter to retrofit object
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // specifies the connection to reactivex
          .client(client)
          .baseUrl(BuildConfig.BASE_URL)
          .build();
      INSTANCE = retrofit.create(WeatherWebService.class);
    }

  }

}

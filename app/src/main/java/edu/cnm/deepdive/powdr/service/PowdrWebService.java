package edu.cnm.deepdive.powdr.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.powdr.BuildConfig;
import edu.cnm.deepdive.powdr.model.dto.User;
import edu.cnm.deepdive.powdr.model.dto.Post;
import io.reactivex.Single;
import java.util.Date;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface PowdrWebService {

  // User Endpoints
  @GET("users/me")
  Single<User> getProfile(@Header("Authorization") String bearerToken);

  @Multipart
  @POST("users/images")
  Single<User> post(@Header("Authorization") String bearerToken, @Part MultipartBody.Part file);


  // Post Endpoints
  @GET("posts")
  Single<List<Post>> getPosts(@Header("Authorization") String bearerToken);

  @GET("posts")
  Single<List<Post>> getPosts(@Header("Authorization") String bearerToken,
      @Query("start") Date start, @Query("end") Date end);

  @GET("posts")
  Single<List<Post>> getPosts(@Header("Authorization") String bearerToken,
      @Query("days") int days);

  @POST("posts")
  Single<Post> post(@Header("Authorization") String bearerToken, @Body Post post);

  @Multipart
  @POST("posts/images")
  Single<Post> postPicture(@Header("Authorization") String bearerToken, @Part MultipartBody.Part file);


  static PowdrWebService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  class InstanceHolder {

    private static final PowdrWebService INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(client)
          .baseUrl(BuildConfig.BASE_URL)
          .build();
      INSTANCE = retrofit.create(PowdrWebService.class);
    }

  }

}

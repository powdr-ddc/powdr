package edu.cnm.deepdive.powdr.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.powdr.BuildConfig;
import edu.cnm.deepdive.powdr.model.dto.FavoriteSkiResort;
import edu.cnm.deepdive.powdr.model.dto.Message;
import edu.cnm.deepdive.powdr.model.dto.Post;
import edu.cnm.deepdive.powdr.model.dto.SkiResort;
import edu.cnm.deepdive.powdr.model.dto.User;
import io.reactivex.Single;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
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
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * For handling REST endpoints from the server side API of Powdr.
 */
public interface PowdrWebService {

  // User Endpoints
  @GET("users/me")
  Single<User> getProfile(@Header("Authorization") String bearerToken);

  @GET("users/me/image")
  Single<ResponseBody> getProfilePic(@Header("Authorization") String bearerToken);

  @Multipart
  @PUT("users/me/image")
  Single<User> putProfilePic(@Header("Authorization") String bearerToken, @Part MultipartBody.Part file);

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
  Single<Post> putProfilePic(@Header("Authorization") String bearerToken, @Body Post post);

  @Multipart
  @POST("posts/images")
  Single<Post> postPicture(@Header("Authorization") String bearerToken, @Part MultipartBody.Part file);

  // Ski Resort Endpoints
  @GET("ski-resorts")
  Single<List<SkiResort>> getSkiResorts(@Header("Authorization") String bearerToken);

  @GET("ski-resorts/{skiResortId}")
  Single<SkiResort> getSkiResort(@Header("Authorization") String bearerToken, @Path("skiResortId")
      UUID id);

  @GET("ski-resorts/{skiResortId}/favorite")
  Single<List<FavoriteSkiResort>> getFavorites(@Header("Authorization") String bearerToken);

  @GET("messages")
  Single<List<Message>> getMessages(@Header("Authorization") String bearerToken);

  /**
   * Returns a singleton instance of the PowdrWebService.
   */
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

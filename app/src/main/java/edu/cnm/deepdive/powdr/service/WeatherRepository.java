package edu.cnm.deepdive.powdr.service;

import android.content.Context;
import edu.cnm.deepdive.powdr.R;
import edu.cnm.deepdive.powdr.model.dto.WeatherResponse;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Repository class to communicate with the {@link WeatherWebService} for the weather API.
 */
public class WeatherRepository {

  private final Context context;
  private final WeatherWebService weatherWebService;
//  private final PowdrWebService powdrWebService;
//  private final GoogleSignInService signInService;

  /**
   * Constructs an instance of the weather repository.
   * @param context Context of the application
   */
  public WeatherRepository(Context context) {
    this.context = context;
    weatherWebService = WeatherWebService.getInstance();
//    powdrWebService = PowdrWebService.getInstance();
//    signInService = GoogleSignInService.getInstance();
  }

  /**
   * Gets the latitude and longitude from the weather API to display the weather for the specified
   * location.
   * @param latitude latitude of location
   * @param longitude longitude of location
   * @return A {@link WeatherResponse}
   */
  public Single<WeatherResponse> get(double latitude, double longitude) {
    return weatherWebService
        .getWeather(context.getString(R.string.api_key), "metric", latitude, longitude)
        .subscribeOn(Schedulers.io());
  }

//  public Single<List<SkiResort>> getAll() {
//    return signInService.refreshBearerToken()
//        .observeOn(Schedulers.io())
//        .flatMap(powdrWebService::getSkiResorts);
//  }
}

package edu.cnm.deepdive.powdr.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.powdr.model.dto.WeatherResponse;
import edu.cnm.deepdive.powdr.service.WeatherRepository;

public class WeatherViewModel extends AndroidViewModel {

  private final MutableLiveData<WeatherResponse> weather;
  private final WeatherRepository weatherRepository;
  private final MutableLiveData<Throwable> throwable;

  public WeatherViewModel(@NonNull Application application) {
    super(application);
    weatherRepository = new WeatherRepository(application);
    weather = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
  }

  /**
   * Returns the weather from {@link WeatherResponse}
   */
  public LiveData<WeatherResponse> getWeather() {
    return weather;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  /**
   * Requests the weather forecast for the specified latitude and longitude.
   * @param latitude Latitude of location
   * @param longitude Longitude of location
   */
  public void requestWeather(int numOfDays, float latitude, float longitude) {
    weatherRepository.get(numOfDays, latitude, longitude)
        .subscribe(
            weather::postValue,
            throwable::postValue
        );
  }
}

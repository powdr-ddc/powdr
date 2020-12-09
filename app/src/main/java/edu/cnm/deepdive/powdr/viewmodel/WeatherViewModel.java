package edu.cnm.deepdive.powdr.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import edu.cnm.deepdive.powdr.model.dto.SkiResort;
import edu.cnm.deepdive.powdr.model.dto.WeatherResponse;
import edu.cnm.deepdive.powdr.service.WeatherRepository;
import io.reactivex.disposables.CompositeDisposable;

public class WeatherViewModel extends AndroidViewModel implements LifecycleObserver {

  private final MutableLiveData<WeatherResponse> weather;
  private final WeatherRepository weatherRepository;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
//  private final MutableLiveData<SkiResort> skiResort;
//  private final MutableLiveData<List<SkiResort>> skiResorts;
  private final SkiResort skiResort;

  public WeatherViewModel(@NonNull Application application) {
    super(application);
    weatherRepository = new WeatherRepository(application);
    weather = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    skiResort = new SkiResort();
//    skiResort = new MutableLiveData<>();
//    skiResorts = new MutableLiveData<>();
    requestWeather("metric", (float) skiResort.getLatitude(), (float) skiResort.getLongitude());
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
   *
   * @param latitude  Latitude of location
   * @param longitude Longitude of location
   */
  public void requestWeather(String metric, float latitude, float longitude) {
    throwable.setValue(null);
    pending.add(
        weatherRepository.get(metric, latitude, longitude)
            .subscribe(
                weather::postValue,
                throwable::postValue
            )
    );
  }

//  public void loadSkiResorts() {
//    throwable.setValue(null);
//    pending.add(
//        weatherRepository.getAll()
//            .subscribe(
//                skiResorts::postValue,
//                throwable::postValue
//            )
//    );
//  }

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }
}

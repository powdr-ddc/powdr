package edu.cnm.deepdive.powdr.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import edu.cnm.deepdive.powdr.model.dto.FavoriteSkiResort;
import edu.cnm.deepdive.powdr.model.dto.SkiResort;
import edu.cnm.deepdive.powdr.model.dto.WeatherResponse;
import edu.cnm.deepdive.powdr.service.SkiResortRepository;
import edu.cnm.deepdive.powdr.service.WeatherRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;
import java.util.UUID;

/**
 * A class for controlling communication between the view and the model.
 */
public class SkiResortViewModel extends AndroidViewModel implements LifecycleObserver {

  private final SkiResortRepository skiResortRepository;
  private final WeatherRepository weatherRepository;
  private final MutableLiveData<SkiResort> skiResort;
  private final MutableLiveData<List<SkiResort>> skiResorts;
  private final MutableLiveData<FavoriteSkiResort> favoriteSkiResort;
  private final MutableLiveData<List<FavoriteSkiResort>> favoriteSkiResorts;
  private final MutableLiveData<WeatherResponse> weather;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  /**
   * A constructor for creating instances of the fields.
   * @param application the context for the application.
   */
  public SkiResortViewModel(@NonNull Application application) {
    super(application);
    skiResortRepository = new SkiResortRepository(application);
    weatherRepository = new WeatherRepository(application);
    skiResort = new MutableLiveData<>();
    skiResorts = new MutableLiveData<>();
    favoriteSkiResort = new MutableLiveData<>();
    favoriteSkiResorts = new MutableLiveData<>();
    weather = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    loadSkiResorts();
    loadFavoriteSkiResorts();
  }

  public LiveData<WeatherResponse> getWeather() {
    return weather;
  }

  /**
   * Gets a {@link SkiResort}
   */
  public LiveData<SkiResort> getSkiResort() {
    return skiResort;
  }

  /**
   * Gets a list of {@link SkiResort}
   */
  public LiveData<List<SkiResort>> getSkiResorts() {
    return skiResorts;
  }

  public LiveData<FavoriteSkiResort> getFavoriteSkiResort() {
    return favoriteSkiResort;
  }

  public LiveData<List<FavoriteSkiResort>> getFavoriteSkiResorts() {
    return favoriteSkiResorts;
  }

  /**
   * Gets a throwable for {@link SkiResort}
   */
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  /**
   * Loads all {@link SkiResort}
   */
  public void loadSkiResorts() {
    throwable.setValue(null);
    pending.add(
        skiResortRepository.getAll()
            .subscribe(
                skiResorts::postValue,
                throwable::postValue
            )
    );
  }

  public void loadSkiResort(UUID id) {
    throwable.setValue(null);
    pending.add(
        skiResortRepository.get(id)
        .subscribe(
            skiResort::postValue,
            throwable::postValue
        )
    );
  }

  public void loadFavoriteSkiResorts() {
    throwable.setValue(null);
    pending.add(
        skiResortRepository.getAllFavorites()
        .subscribe(
            favoriteSkiResorts::postValue,
            throwable::postValue
        )
    );
  }

  /**
   * Requests the weather forecast for the specified latitude and longitude.
   *
   *
   * @param latitude  Latitude of location
   * @param longitude Longitude of location
   */
  public void requestWeather(double latitude, double longitude) {
    throwable.setValue(null);
    pending.add(
        weatherRepository.get(latitude, longitude)
            .subscribe(
                weather::postValue,
                throwable::postValue
            )
    );
  }

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }
}

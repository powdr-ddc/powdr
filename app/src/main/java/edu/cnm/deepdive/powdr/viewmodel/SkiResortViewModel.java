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
import edu.cnm.deepdive.powdr.service.SkiResortRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class SkiResortViewModel extends AndroidViewModel implements LifecycleObserver {

  private final SkiResortRepository skiResortRepository;
  private final MutableLiveData<SkiResort> skiResort;
  private final MutableLiveData<List<SkiResort>> skiResorts;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  public SkiResortViewModel(@NonNull Application application) {
    super(application);
    skiResortRepository = new SkiResortRepository(application);
    skiResort = new MutableLiveData<>();
    skiResorts = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  public LiveData<SkiResort> getSkiResort() {
    return skiResort;
  }

  public LiveData<List<SkiResort>> getSkiResorts() {
    return skiResorts;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void loadPosts() {
    throwable.setValue(null);
    pending.add(
        skiResortRepository.getAll()
            .subscribe(
                skiResorts::postValue,
                throwable::postValue
            )
    );
  }

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }
}

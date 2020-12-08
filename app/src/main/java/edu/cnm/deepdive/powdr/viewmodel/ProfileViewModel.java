package edu.cnm.deepdive.powdr.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.powdr.model.dto.User;
import edu.cnm.deepdive.powdr.service.UserRepository;
import io.reactivex.disposables.CompositeDisposable;

public class ProfileViewModel extends AndroidViewModel implements LifecycleObserver {

  private final UserRepository userRepository;
  private final MutableLiveData<User> user;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  public ProfileViewModel(@NonNull Application application) {
    super(application);
    userRepository = new UserRepository(application);
    user = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  public LiveData<User> getProfile() {
    return user;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }
}
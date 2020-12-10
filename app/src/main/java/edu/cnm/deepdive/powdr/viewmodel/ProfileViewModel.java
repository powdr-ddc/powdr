package edu.cnm.deepdive.powdr.viewmodel;

import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.powdr.model.dto.User;
import edu.cnm.deepdive.powdr.service.UserRepository;
import io.reactivex.disposables.CompositeDisposable;

public class ProfileViewModel extends AndroidViewModel implements LifecycleObserver {

  private final UserRepository userRepository;
  private final MutableLiveData<User> user;
  private final MutableLiveData<Bitmap> image;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  public ProfileViewModel(@NonNull Application application) {
    super(application);
    userRepository = new UserRepository(application);
    user = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    image = new MutableLiveData<>();
    loadProfile();
    loadProfilePic();
  }

  public LiveData<User> getProfile() {
    return user;
  }

  public void saveProfilePic(Uri uri) {
    throwable.setValue(null);
    pending.add(
        userRepository.saveProfilePic(uri)
            .subscribe(
                user::postValue,
                throwable::postValue
            )
    );

  }

  public void loadProfile() {
    throwable.setValue(null);
    pending.add(
        userRepository.getProfile()
            .subscribe(
                user::postValue,
                throwable::postValue
            )
    );

  }

  public void loadProfilePic() {
    throwable.setValue(null);
    pending.add(
        userRepository.getProfilePic()
            .subscribe(
                image::postValue,
                throwable::postValue
            )
    );
  }

  public LiveData<Bitmap> getImage() {
    return image;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }
}
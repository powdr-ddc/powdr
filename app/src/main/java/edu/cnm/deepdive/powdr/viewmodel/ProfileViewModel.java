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

/**
 * A class for controlling communication between the view and the model.
 */
public class ProfileViewModel extends AndroidViewModel implements LifecycleObserver {

  private final UserRepository userRepository;
  private final MutableLiveData<User> user;
  private final MutableLiveData<Bitmap> image;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  /**
   * A constructor for creating instances of the fields.
   * @param application
   */
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

  /**
   * Gets the profile of a user.
   */
  public LiveData<User> getProfile() {
    return user;
  }

  /**
   * Saves the profile picture of the user.
   */
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

  /**
   * Loads the profile of {@link User}
   */
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

  /**
   * Loads the profile picture of a {@link User}
   */
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

  /**
   * Gets an image.
   */
  public LiveData<Bitmap> getImage() {
    return image;
  }

  /**
   * Gets a throwable object for errors.
   */
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }
}
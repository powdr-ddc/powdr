package edu.cnm.deepdive.powdr.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import edu.cnm.deepdive.powdr.model.dto.Post;
import edu.cnm.deepdive.powdr.service.PostRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.Date;
import java.util.List;

/**
 * A class for controlling communication between the view and the model.
 */
public class WallViewModel extends AndroidViewModel implements LifecycleObserver {

  private static final int DEFAULT_DAYS = 30;

  private final PostRepository postRepository;
  private final MutableLiveData<Post> post;
  private final MutableLiveData<List<Post>> posts;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  /**
   * A constructor for creating instances of the fields.
   * @param application the context for the application.
   */
  public WallViewModel(@NonNull Application application) {
    super(application);
    postRepository = new PostRepository(application);
    post = new MutableLiveData<>();
    posts = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    loadMostRecent();
  }

  /**
   * Gets a list of {@link Post}
   */
  public LiveData<List<Post>> getPosts() {
    return posts;
  }

  /**
   * Gets a single {@link Post}
   */
  public LiveData<Post> getPost() {
    return post;
  }

  /**
   * Gets a throwable for {@link WallViewModel}
   */
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  /**
   * Loads all {@link Post}
   */
  public void loadPosts() {
    throwable.setValue(null);
    pending.add(
        postRepository.getAll()
        .subscribe(
            posts::postValue,
            throwable::postValue
        )
    );
  }

  /**
   * Loads all Posts within a specified date range.
   */
  public void loadPostsInDateRange(Date start, Date end) {
    throwable.setValue(null);
    pending.add(
        postRepository.getInDateRange(start, end)
            .subscribe(
                posts::postValue,
                throwable::postValue
            )
    );
  }

  /**
   * loads the most recent post.
   */
  public void loadMostRecent() {
    loadMostRecent(DEFAULT_DAYS);
  }

  /**
   * Loads the most recent post by days.
   */
  public void loadMostRecent(int days) {
    throwable.setValue(null);
    pending.add(
        postRepository.getMostRecent(days)
            .subscribe(
                posts::postValue,
                throwable::postValue
            )
    );
  }

  /**
   * Saves a {@link Post}
   */
  public void save(Post post) {
    throwable.setValue(null);
    pending.add(
        postRepository.save(post)
            .subscribe(
                this.post::postValue,
                throwable::postValue
            )
    );
  }

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }
}
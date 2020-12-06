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

public class WallViewModel extends AndroidViewModel implements LifecycleObserver {

  private static final int DEFAULT_DAYS = 30;

  private final PostRepository postRepository;
  private final MutableLiveData<Post> post;
  private final MutableLiveData<List<Post>> posts;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;


  public WallViewModel(@NonNull Application application) {
    super(application);
    postRepository = new PostRepository(application);
    post = new MutableLiveData<>();
    posts = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    loadMostRecent();
  }

  public LiveData<List<Post>> getPosts() {
    return posts;
  }

  public LiveData<Post> getPost() {
    return post;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

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

  public void loadMostRecent() {
    loadMostRecent(DEFAULT_DAYS);
  }

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
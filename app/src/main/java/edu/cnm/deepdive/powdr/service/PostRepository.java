package edu.cnm.deepdive.powdr.service;

import android.content.Context;
import edu.cnm.deepdive.powdr.model.dto.Post;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import java.util.List;

public class PostRepository {


  private final Context context;
  private final GoogleSignInService signInService;
  private final PowdrWebService webService;

  public PostRepository(Context context) {
    this.context = context;
    signInService = GoogleSignInService.getInstance();
    webService = PowdrWebService.getInstance();
  }

  public Single<List<Post>> getAll() {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap(webService::getPosts);
  }

  public Single<List<Post>> getInDateRange(Date start, Date end) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((token) -> webService.getPosts(token, start, end));
  }

  public Single<List<Post>> getMostRecent(int days) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((token) -> webService.getPosts(token, days));
  }


  public Single<Post> save(Post post) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((token) -> webService.post(token, post));
  }
}

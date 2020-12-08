package edu.cnm.deepdive.powdr.service;

import android.content.Context;
import edu.cnm.deepdive.powdr.model.dto.User;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class UserRepository {

  private final Context context;
  private final PowdrWebService webService;
  private final GoogleSignInService signInService;
  // TODO Add fields as appropriate for access to DAO's etc.

  public UserRepository(Context context) {
    this.context = context;
    webService = PowdrWebService.getInstance();
    signInService = GoogleSignInService.getInstance();
  }

  public Single<User> getProfile() {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap(webService::getProfile);
  }

//  public Single<List<User>> getFriends()

}

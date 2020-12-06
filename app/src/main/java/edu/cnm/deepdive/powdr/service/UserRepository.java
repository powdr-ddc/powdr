package edu.cnm.deepdive.powdr.service;

import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.powdr.model.User;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

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

  public Single<User> getProfileFromServer() {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap(webService::getProfile);
  }

}

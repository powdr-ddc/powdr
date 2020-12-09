package edu.cnm.deepdive.powdr.service;

import android.content.Context;
import edu.cnm.deepdive.powdr.model.dto.Message;
import edu.cnm.deepdive.powdr.model.dto.SkiResort;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class MessageRepository {

  private final Context context;
  private final GoogleSignInService signInService;
  private final PowdrWebService webService;

  public MessageRepository(Context context) {
    this.context = context;
    signInService = GoogleSignInService.getInstance();
    webService = PowdrWebService.getInstance();
  }

  public Single<List<Message>> getAll() {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap(webService::getMessages);
  }


}

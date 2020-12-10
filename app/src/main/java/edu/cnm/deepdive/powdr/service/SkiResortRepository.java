package edu.cnm.deepdive.powdr.service;

import android.content.Context;
import edu.cnm.deepdive.powdr.model.dto.FavoriteSkiResort;
import edu.cnm.deepdive.powdr.model.dto.SkiResort;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.UUID;

/**
 * Repository for the {@link SkiResort} dto.
 */
public class SkiResortRepository {

  private final Context context;
  private final GoogleSignInService signInService;
  private final PowdrWebService webService;

  /**
   * Creates a context of {@link SkiResortRepository}
   */
  public SkiResortRepository(Context context) {
    this.context = context;
    signInService = GoogleSignInService.getInstance();
    webService = PowdrWebService.getInstance();
  }

  public Single<SkiResort> get(UUID id) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((token) -> webService.getSkiResort(token, id));
  }

  /**
   * Gets a list of {@link SkiResort}
   */
  public Single<List<SkiResort>> getAll() {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap(webService::getSkiResorts);
  }

  public Single<List<FavoriteSkiResort>> getAllFavorites() {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap(webService::getFavorites);
  }

}

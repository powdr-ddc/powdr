package edu.cnm.deepdive.powdr.service;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import edu.cnm.deepdive.powdr.BuildConfig;
import io.reactivex.Single;

/**
 * Creates the Google Sign In for the application. Allowing the user to
 * sign-in to the app using their Google credentials.
 */
public class GoogleSignInService {

  private static Application context;

  private final GoogleSignInClient client;

  private GoogleSignInAccount account;

  private GoogleSignInService() {
    GoogleSignInOptions options = new GoogleSignInOptions.Builder()
        .requestEmail()
        .requestId()
        .requestProfile()
        .requestIdToken(BuildConfig.CLIENT_ID)
        .build();
    client = GoogleSignIn.getClient(context, options);
  }

  /**
   * Sets the context of {@link GoogleSignInService}
   */
  public static void setContext(Application context) {
    GoogleSignInService.context = context;
  }

  /**
   * Gets an instance of {@link GoogleSignInService}
   */
  public static GoogleSignInService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Gets the account used for {@link GoogleSignInService}
   */
  public GoogleSignInAccount getAccount() {
    return account;
  }

  /**
   * Refreshes the Google sign-in account.
   */
  public Single<GoogleSignInAccount> refresh() {
    return Single.create((emitter) ->
        client.silentSignIn()
            .addOnSuccessListener(this::setAccount)
            .addOnSuccessListener(emitter::onSuccess)
            .addOnFailureListener(emitter::onError)
    );
  }

  /**
   * Refreshes the bearer token.
   * @return Returns refreshed bearer token.
   */
  public Single<String> refreshBearerToken() {
    //noinspection ReactiveStreamsNullableInLambdaInTransform
    return refresh()
        .map(GoogleSignInAccount::getIdToken)
        .map((token) -> String.format("Bearer %s", token));
  }

  /**
   * Starts Google sign-in activity.
   * @param activity for google sign-in.
   * @param requestCode Requests
   */
  public void startSignIn(Activity activity, int requestCode) {
    account = null;
    Intent intent = client.getSignInIntent();
    activity.startActivityForResult(intent, requestCode);
  }

  /**
   * Completes the sign-in.
   * @param data data intent data for the task
   * @return A task for Google sign-in
   */
  public Task<GoogleSignInAccount> completeSignIn(Intent data) {
    Task<GoogleSignInAccount> task = null;
    try {
      task = GoogleSignIn.getSignedInAccountFromIntent(data);
      setAccount(task.getResult(ApiException.class));
    } catch (ApiException e) {
      // Exception will be passed automatically to onFailureListener.
    }
    return task;
  }

  /**
   * Signs out of Google in the application.
   */
  public Task<Void> signOut() {
    return client.signOut()
        .addOnCompleteListener((ignored) -> setAccount(null));
  }

  private void setAccount(GoogleSignInAccount account) {
    this.account = account;
    if (account != null) {
      //noinspection ConstantConditions
      Log.d(getClass().getSimpleName(), account.getIdToken());
    }
  }

  private static class InstanceHolder {

    private static final GoogleSignInService INSTANCE = new GoogleSignInService();

  }

}

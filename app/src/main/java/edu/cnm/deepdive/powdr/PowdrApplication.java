package edu.cnm.deepdive.powdr;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.powdr.service.GoogleSignInService;

/**
 * Instantiates Powdr as an application that extends the {@link Application} class.
 */
public class PowdrApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    GoogleSignInService.setContext(this);
    Picasso.setSingletonInstance(
        new Picasso.Builder(this)
        .loggingEnabled(BuildConfig.DEBUG)
        .build()
    );
    Stetho.initializeWithDefaults(this);
  }
}

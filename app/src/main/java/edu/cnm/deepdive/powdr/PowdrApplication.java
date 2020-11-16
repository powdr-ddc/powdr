package edu.cnm.deepdive.powdr;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.powdr.service.GoogleSignInService;

public class PowdrApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    GoogleSignInService.setContext(this);

  }
}
